package com.example.praticaweb.Service;

import com.example.praticaweb.entities.Aluno;
import com.example.praticaweb.entities.Turma;
import com.example.praticaweb.repositories.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

public class AlunoService {
    @Autowired
    AlunoRepository alunoRepository;


}
