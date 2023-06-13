package com.remedios.vitu.Remedios.controllers;

import com.remedios.vitu.Remedios.usuarios.DadosAutenticacao;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {

    //Aqui usaremos um DTO do spring só que vamos fazer uma conversão nele para podermos utilizar o nosso
    //nos fazemos essa conversão na variavel 'token'

    @Autowired
    private AuthenticationManager authenticationManager;
    @PostMapping
    public ResponseEntity<?> efetuarLogin(@RequestBody @Valid DadosAutenticacao dadosAutenticacao){
         //o service é uma camada para que não precisamos por o conteudo dele dentro do controller
         //ele vai ajuda o controller porque o controller só lida com verbos e endpoints e não metodos igual o service

        //UsernamePasswordAuthenticationToken -> é um token que o spring security usa para autenticar o usuario
        //as variaveis abaixo é para ver se a autenticação esta correta
        var token = new UsernamePasswordAuthenticationToken(dadosAutenticacao.login(), dadosAutenticacao.senha());
        var autenticacao = authenticationManager.authenticate(token); //token -> objeto que será validado

        return ResponseEntity.ok().build();
    }

}
