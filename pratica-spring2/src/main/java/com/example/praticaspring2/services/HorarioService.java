package com.example.praticaspring2.services;

import com.example.praticaspring2.entities.Horario;
import com.example.praticaspring2.exceptionHandler.horario.HorarioBadRequestException;
import com.example.praticaspring2.exceptionHandler.horario.HorarioBadRequestExceptionTwo;
import com.example.praticaspring2.exceptionHandler.horario.HorarioNotFoundException;
import com.example.praticaspring2.repositories.HorarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
public class HorarioService {
    @Autowired
    private HorarioRepository repository;

    @Transactional
    public Horario addHorario(Horario newHorario) throws Exception{
        try {
            Horario horario = repository.findByHorario(newHorario.getHorario());
            if (horario != null)
                throw new HorarioBadRequestException();

            horario = new Horario();
            horario.setHorario(newHorario.getHorario());
            repository.save(horario);

            return horario;
        }
        catch (Exception e){
            throw new Exception(e);
        }
    }

    @Transactional(readOnly = true)
    public Iterable<Horario> getAllHorarios() throws Exception{
        try {
            Iterable<Horario> horarios = repository.findAll();
            if (!horarios.iterator().hasNext())
                throw new HorarioNotFoundException();

            return horarios;
        }
        catch (Exception e){
            throw new Exception(e);
        }
    }

    @Transactional(readOnly = true)
    public Set<Horario> isExistsDate(Set<Horario> newHorarios) throws Exception{
        try {
            Set<Horario> horarios = new HashSet<>();
            Horario exists;
            for (Horario horario : newHorarios) {
                exists = repository.findByHorario(horario.getHorario());
                if (exists == null)
                    throw new HorarioBadRequestExceptionTwo("Horário não existe na base de dados", horario);

                horarios.add(exists);
            }

            return horarios;
        }
        catch (Exception e){
            throw new Exception(e);
        }
    }
}
