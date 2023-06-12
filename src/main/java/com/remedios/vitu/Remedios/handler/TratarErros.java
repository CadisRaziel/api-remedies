package com.remedios.vitu.Remedios.handler;


import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//Essa classe vai dar uma resposta ao erro !!

@RestControllerAdvice // -> reponsavel por toda vez que acontecer uma exception no controller, essa classe vai ser chamada para ver se estamos tratando o erro
public class TratarErros {

    @ExceptionHandler(EntityNotFoundException.class) //-> identifica o tipo de exceção correta
    //EntityNotFoundException -> quando der erro de 500, ou seja, quando o id nao for encontrado
    public ResponseEntity<?> tratarErro404(){
        //ResponseEntity<?> -> o <?> esta informando que é é generico igual no dart o T, ou seja qualquer tipo vai poder ser usado
        return ResponseEntity.notFound().build();
        //notFound -> vai retornar 404 not found
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    //MethodArgumentNotValidException -> quando alguem manda uma validação errada
    //a gente tem algumas validações no nosso DTO
    //se vier alguma errada vai cair aqui nessa exception
    public ResponseEntity<?> tratarErro400(MethodArgumentNotValidException ex) {
        //para retornar uma mensagem personalizada com o erro especifico, vamos colocar o MethodArgumentNotValidException como parametro
        //getFieldErrors -> vai ver todos os campos e qual campo deu o erro (ele gera uma lista)
        var erros = ex.getFieldErrors();

        //badRequest -> vai retornar 400 bad request e não vai retornar um 'body' como estava acontecendo
        return ResponseEntity.badRequest().body(erros.stream()
                .map(DadosError::new) //-> para utilizar essa lambda a classe chamada precisa ter um construtor
                .toList());
    }

    public record DadosError(String field, String message) {
        //Esse dto esta sendo criado dentro dessa classe pois ele só será utilizado aqui dentro !!!

        public DadosError(FieldError erro) {
            this(erro.getField(), erro.getDefaultMessage());
        }
    }
}
