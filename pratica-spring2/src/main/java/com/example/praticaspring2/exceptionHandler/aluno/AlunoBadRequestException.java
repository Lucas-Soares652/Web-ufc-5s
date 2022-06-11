package com.example.praticaspring2.exceptionHandler.aluno;

public class AlunoBadRequestException extends RuntimeException{
    private String message;

    public AlunoBadRequestException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
