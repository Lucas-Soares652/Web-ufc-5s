package com.example.praticaweb.exceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

@ControllerAdvice(basePackages = "com.example.praticaweb.controllers")
public class TurmaServiceAdvice {

    @ResponseBody
    @ExceptionHandler(TurmaNotFoundException.class)
    public ResponseEntity<MessageExceptionHandler> turmaNotFound(TurmaNotFoundException turmaNotFoundException){
        MessageExceptionHandler error = new MessageExceptionHandler(new Date(), HttpStatus.NOT_FOUND.value(), "Turma não encontrada");
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ResponseBody
    @ExceptionHandler(TurmasNotFoundException.class)
    public ResponseEntity<MessageExceptionHandler> turmasNotFound(TurmasNotFoundException turmasNotFoundException){
        MessageExceptionHandler error = new MessageExceptionHandler(new Date(), HttpStatus.NOT_FOUND.value(), "Não há registro de turmas");
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

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
    @ExceptionHandler(TurmaBadRequestException.class)
    public ResponseEntity<MessageExceptionHandler> turmaBadRequest(TurmaBadRequestException turmaBadRequestException){
        MessageExceptionHandler error = new MessageExceptionHandler(new Date(), HttpStatus.BAD_REQUEST.value(), "Turma já existe");
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ResponseBody
    @ExceptionHandler(AlunoBadRequestException.class)
    public ResponseEntity<MessageExceptionHandler> alunoBadRequest(AlunoBadRequestException alunoBadRequestException){
        MessageExceptionHandler error = new MessageExceptionHandler(new Date(), HttpStatus.BAD_REQUEST.value(), "Aluno já está matriculado na turma");
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
