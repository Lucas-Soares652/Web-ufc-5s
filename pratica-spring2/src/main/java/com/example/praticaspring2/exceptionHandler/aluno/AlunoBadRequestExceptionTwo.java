package com.example.praticaspring2.exceptionHandler.aluno;

import com.example.praticaspring2.entities.Aluno;

public class AlunoBadRequestExceptionTwo extends RuntimeException{
    private String message;
    private Aluno aluno;

    public AlunoBadRequestExceptionTwo(String message, Aluno aluno) {
        this.message = message;
        this.aluno = aluno;
    }

    public Aluno getAluno() {
        return aluno;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
