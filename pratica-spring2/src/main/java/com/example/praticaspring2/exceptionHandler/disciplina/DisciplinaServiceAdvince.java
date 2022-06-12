package com.example.praticaspring2.exceptionHandler.disciplina;

import com.example.praticaspring2.exceptionHandler.MessageExceptionHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

@ControllerAdvice(basePackages = "com.example.praticaspring2.controllers")
public class DisciplinaServiceAdvince {
    @ResponseBody
    @ExceptionHandler(DisciplinasNotFoundException.class)
    public ResponseEntity<MessageExceptionHandler> disciplinaNotFound(DisciplinasNotFoundException disciplinasNotFoundException){
        MessageExceptionHandler error = new MessageExceptionHandler(new Date(), HttpStatus.NOT_FOUND.value(), "Não há registro de disciplinas");
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ResponseBody
    @ExceptionHandler(DisciplinaBadRequestException.class)
    public ResponseEntity<MessageExceptionHandler> disciplinaBadRequest(DisciplinaBadRequestException disciplinaBadRequestException){
        MessageExceptionHandler error = new MessageExceptionHandler(new Date(), HttpStatus.BAD_REQUEST.value(), disciplinaBadRequestException.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
