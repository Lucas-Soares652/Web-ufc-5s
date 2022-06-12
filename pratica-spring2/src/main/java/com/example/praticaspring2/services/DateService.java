package com.example.praticaspring2.services;

import com.example.praticaspring2.entities.Date;
import com.example.praticaspring2.repositories.DateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
public class DateService {
    @Autowired
    private DateRepository repository;

    @Transactional(readOnly = true)
    public Set<Date> isExistsDate(Set<Date> newDates){
        Set<Date> dates = new HashSet<>();
        Date exists;
        for (Date date : newDates){
            exists = repository.findByHorario(date.getHorario());
            if (exists != null){
                dates.add(exists);
                continue;
            }
            dates.add(date);
        }

        return dates;
    }
}
