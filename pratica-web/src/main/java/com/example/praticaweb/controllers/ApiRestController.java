package com.example.praticaweb.controllers;

import com.example.praticaweb.Service.TurmaService;
import com.example.praticaweb.entities.Aluno;
import com.example.praticaweb.entities.Turma;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/turma")
public class ApiRestController {
    @Autowired
    private TurmaService service;

    @GetMapping
    public List<Turma> getTurmas(){
        return service.findAll();
    }

    @GetMapping(value = "/{codigo}")
    public Turma getTurmaByCodigo(@PathVariable int codigo){
        return service.findByCodigo(codigo);
    }

    @PostMapping
    public Turma addTurma(@RequestBody Turma turma){
        return service.addTurma(turma);
    }

    @PutMapping(value = "/{codigo}")
    public Turma editTurmaByCodigo(@PathVariable int codigo, @RequestBody Turma turma){
        return service.editTurmaByCodigo(codigo, turma);
    }

    @DeleteMapping(value = "/{codigo}")
    public List<Turma> deleteTurmaByCodigo(@PathVariable int codigo){
        return service.deleteTurmaByCodigo(codigo);
    }

    @GetMapping(value = "/{codigo}/alunos")
    public List<Aluno> findAllAlunosByCodigoTurma(@PathVariable int codigo){
        return service.findAllAlunosByCodigoTurma(codigo);
    }

    @PostMapping(value = "/{codigo}/alunos")
    public Turma addAlunosByCodigoTurma(@PathVariable int codigo, @RequestBody Aluno aluno){
        return service.addAlunoByCodigoTurma(codigo, aluno);
    }

    @DeleteMapping(value = "/{codigo}/alunos/{matricula}")
    public Turma deleteAlunoByMatriculaAndCodigoTurma(@PathVariable int codigo, @PathVariable int matricula){
        return service.deleteAlunoByMatriculaAndCodigoTurma(codigo, matricula);
    }
}
