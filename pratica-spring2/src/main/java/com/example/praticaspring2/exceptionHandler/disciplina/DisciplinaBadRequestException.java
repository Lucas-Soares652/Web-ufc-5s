package com.example.praticaspring2.exceptionHandler.disciplina;

public class DisciplinaBadRequestException extends RuntimeException{
    private String message;

    public DisciplinaBadRequestException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
