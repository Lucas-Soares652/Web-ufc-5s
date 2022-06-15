package com.example.praticaspring2.exceptionHandler.horario;

import com.example.praticaspring2.exceptionHandler.HorarioMessageExceptionHandler;
import com.example.praticaspring2.exceptionHandler.MessageExceptionHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

@ControllerAdvice(basePackages = "com.example.praticaspring2.controllers")
public class HorarioServiceAdvince {
    @ResponseBody
    @ExceptionHandler(HorarioNotFoundException.class)
    public ResponseEntity<MessageExceptionHandler> horarioNotFound(HorarioNotFoundException exception){
        MessageExceptionHandler error = new MessageExceptionHandler(new Date(), HttpStatus.NOT_FOUND.value(), "Não há registro de Horários");
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ResponseBody
    @ExceptionHandler(HorarioBadRequestException.class)
    public ResponseEntity<MessageExceptionHandler> horarioBadResquest(HorarioBadRequestException exception){
        MessageExceptionHandler error = new MessageExceptionHandler(new Date(), HttpStatus.BAD_REQUEST.value(), "Horário já cadastrado");
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ResponseBody
    @ExceptionHandler(HorarioBadRequestExceptionTwo.class)
    public ResponseEntity<HorarioMessageExceptionHandler> horarioBadResquestTwo(HorarioBadRequestExceptionTwo exception){
        HorarioMessageExceptionHandler error = new HorarioMessageExceptionHandler(new Date(), HttpStatus.BAD_REQUEST.value(), exception.getMessage(), exception.getHorario());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
