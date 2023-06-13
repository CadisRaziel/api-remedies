package com.remedios.vitu.Remedios.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity //-> diz pro spring que vamos mexer nas configurações de segurança
public class SecurityConfiguration {
    //Aplicação web -> Statefull (que guarda sessão|estado|) (precisa por login e senha !! |LOGADO OU DESLOGADO|)
    //Aplicação Web rest -> stateless (Não guarda sessão|estado|) (nao vamos precisar por login e senha !!!)

    //depois de criar essa classe ao irmos no insominia e fazer uma requisição ele ira deixar, pois nao precisa de login e senha agora
    @Bean //para o spring reconhece que estamos retornando um objeto
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        //csrf().disable() -> desabilitando o ataque csrf pois ele só funciona em aplicação web stateful, e a nossa não é
        //sessionManagement -> configuração que fala que estamos fazendo uma api web rest
        //sessionCreationPolicy -> informando que estamos mudando a politica de criação de sessão
        //SessionCreationPolicy.STATELESS -> informando que nao vai ser statefull(aplicação web) e sim uma stateless(aplicação web rest)

        //precisamos desabilitar o que vem padrão para implementarmos a nossa propria segurança personalizada
        return httpSecurity.csrf()
                .disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .build();
    }
}
