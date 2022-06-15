package com.example.praticaspring2.exceptionHandler;

import com.example.praticaspring2.entities.Horario;

import java.util.Date;

public class HorarioMessageExceptionHandler {
    private Date timestamp;
    private Integer status;
    private String message;
    private Horario horario;

    public HorarioMessageExceptionHandler(Date timestamp, Integer status, String message, Horario horario) {
        this.timestamp = timestamp;
        this.status = status;
        this.message = message;
        this.horario = horario;
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

    public Horario getHorario() {
        return horario;
    }

    public void setHorario(Horario horario) {
        this.horario = horario;
    }
}
