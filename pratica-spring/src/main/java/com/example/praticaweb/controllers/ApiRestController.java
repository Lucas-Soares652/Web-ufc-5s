package com.example.praticaweb.controllers;

import com.example.praticaweb.services.TurmaService;
import com.example.praticaweb.entities.Aluno;
import com.example.praticaweb.entities.Turma;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping(value = "/api/turma")
public class ApiRestController {
    @Autowired
    private TurmaService turmaService;

    @GetMapping
    public Iterable<Turma> getTurmas()throws Exception{
        try {
            return turmaService.findAll();
        }
        catch (Exception e){
            throw new Exception(e);
        }
    }

    @GetMapping(value = "/{codigo}")
    public Turma getTurmaByCodigo(@PathVariable int codigo) throws Exception{
        try {
            return turmaService.findByCodigo(codigo);
        }
        catch (Exception e){
            throw new Exception(e);
        }
    }

    @PostMapping
    public Turma addTurma(@RequestBody Turma turma) throws Exception {
        return turmaService.addTurma(turma);
    }

    @PutMapping(value = "/{codigo}")
    public Turma editTurmaByCodigo(@PathVariable int codigo, @RequestBody Turma turma) throws Exception{
        try {
            return turmaService.editTurmaByCodigo(codigo, turma);
        }
        catch (Exception e){
            throw new Exception(e);
        }
    }

    @DeleteMapping(value = "/{codigo}")
    public void deleteTurmaByCodigo(@PathVariable int codigo) throws Exception{
        try {
            turmaService.deleteTurmaByCodigo(codigo);
        }
        catch (Exception e){
            throw new Exception(e);
        }
    }

    @GetMapping(value = "/{codigo}/alunos")
    public Set<Aluno> findAllAlunosByCodigoTurma(@PathVariable int codigo) throws Exception{
        try {
            return turmaService.findAllAlunosByCodigoTurma(codigo);
        }
        catch (Exception e){
            throw new Exception(e);
        }
    }

    @PostMapping(value = "/{codigo}/alunos")
    public Turma addAlunoByCodigoTurma(@PathVariable int codigo, @RequestBody Aluno aluno) throws Exception{
        try {
            return turmaService.addAlunoByCodigoTurma(codigo, aluno);
        }
        catch (Exception e){
            throw new Exception(e);
        }
    }

    @DeleteMapping(value = "/{codigo}/alunos/{matricula}")
    public Turma deleteAlunoByMatriculaAndCodigoTurma(@PathVariable int codigo, @PathVariable int matricula) throws Exception{
        try {
            return turmaService.deleteAlunoByMatriculaAndCodigoTurma(codigo, matricula);
        }
        catch (Exception e){
            throw new Exception(e);
        }
    }
}