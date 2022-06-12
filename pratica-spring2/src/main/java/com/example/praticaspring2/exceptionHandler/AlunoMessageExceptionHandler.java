package com.example.praticaspring2.exceptionHandler;

import com.example.praticaspring2.entities.Aluno;

import java.util.Date;

public class AlunoMessageExceptionHandler {
    private Date timestamp;
    private Integer status;
    private String message;
    private Aluno aluno;

    public AlunoMessageExceptionHandler(Date timestamp, Integer status, String message, Aluno aluno) {
        this.timestamp = timestamp;
        this.status = status;
        this.message = message;
        this.aluno = aluno;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }
}
