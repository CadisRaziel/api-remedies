package com.remedios.vitu.Remedios.handler;


import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice // -> reponsavel por toda vez que acontecer uma exception no controller, essa classe vai ser chamada para ver se estamos tratando o erro
public class TratarErros {

    @ExceptionHandler(EntityNotFoundException.class) //-> identifica o tipo de exceção correta
    //EntityNotFoundException -> quando der erro de 500, ou seja, quando o id nao for encontrado
    public ResponseEntity<?> tratarErro404(){
        //ResponseEntity<?> -> o <?> esta informando que é é generico igual no dart o T, ou seja qualquer tipo vai poder ser usado
        return ResponseEntity.notFound().build();
        //notFound -> vai retornar 404 not found
    }
}
