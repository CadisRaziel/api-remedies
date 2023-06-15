package com.remedios.vitu.Remedios.service;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.remedios.vitu.Remedios.usuarios.EntidadeUsuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;


@Service
public class TokenService {
    //vamos usar essa classe la no authenticacaoController

    @Value("${api.security.token.secret}") //atribuindo o secret a variavel de ambiente que esta no properties
    private String secret;

    //geração do token
    public String gerarToken(EntidadeUsuario usuario){
        //vamos usar a biblioteca do jwt
        try {
            var algorithm = Algorithm.HMAC256(secret);
            //só vai gerar o token abaixo se a senha do parametro a cima for correta
            return JWT.create()
                    .withIssuer("Remedios_api") // -> quem esta disponibilizando o token (podemos por o nome da nossa api)
                    .withSubject(usuario.getLogin()) // -> quem é o usuario que esta recebendo o token
                    //.withClaim("id", usuario.getId()) // -> o id do usuario que esta recebendo o token
                    .withExpiresAt(dataExpiracao()) // -> tempo de expiração do token (normalmente é 3600 segundos)
                    .sign(algorithm); // -> faz a assinatura do token
        } catch (JWTCreationException exception){
            throw new RuntimeException("Erro ao criar token", exception);
        }
    }

    private Instant dataExpiracao() {
        //plusHours -> o valor que queremos no token (no caso 2 horas), quando o token passar 2 horas ele expira
        //toInstant -> o horario do brasil que é -3
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }

}
