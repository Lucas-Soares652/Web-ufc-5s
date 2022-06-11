package com.example.praticaspring2.exceptionHandler.aluno;

import com.example.praticaspring2.exceptionHandler.MessageExceptionHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

@ControllerAdvice(basePackages = "com.example.praticaspring2.controllers")
public class AlunoServiceAdvince {
    @ResponseBody
    @ExceptionHandler(AlunosNotFoundException.class)
    public ResponseEntity<MessageExceptionHandler> alunosNotFound(AlunosNotFoundException alunosNotFoundException){
        MessageExceptionHandler error = new MessageExceptionHandler(new Date(), HttpStatus.NOT_FOUND.value(), "Não há registro de alunos");
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ResponseBody
    @ExceptionHandler(AlunoNotFoundException.class)
    public ResponseEntity<MessageExceptionHandler> alunoNotFound(AlunoNotFoundException alunoNotFoundException){
        MessageExceptionHandler error = new MessageExceptionHandler(new Date(), HttpStatus.NOT_FOUND.value(), "Aluno não encontrado");
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ResponseBody
    @ExceptionHandler(AlunoBadRequestException.class)
    public ResponseEntity<MessageExceptionHandler> alunoBadRequest(AlunoBadRequestException alunoBadRequestException){
        MessageExceptionHandler error = new MessageExceptionHandler(new Date(), HttpStatus.BAD_REQUEST.value(), alunoBadRequestException.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
