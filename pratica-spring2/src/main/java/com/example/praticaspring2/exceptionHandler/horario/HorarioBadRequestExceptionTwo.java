package com.example.praticaspring2.exceptionHandler.horario;

import com.example.praticaspring2.entities.Horario;

public class HorarioBadRequestExceptionTwo extends RuntimeException{
    private String message;
    private Horario horario;

    public HorarioBadRequestExceptionTwo(String message, Horario horario) {
        this.message = message;
        this.horario = horario;
    }

    public Horario getHorario() {
        return horario;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
