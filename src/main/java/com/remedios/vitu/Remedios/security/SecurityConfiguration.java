package com.remedios.vitu.Remedios.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity //-> diz pro spring que vamos mexer nas configurações de segurança
public class SecurityConfiguration {
    //Aplicação web -> Statefull (que guarda sessão|estado|) (precisa por login e senha !! |LOGADO OU DESLOGADO|)
    //Aplicação Web rest -> stateless (Não guarda sessão|estado|) (nao vamos precisar por login e senha !!!)


    @Autowired
    private SecurityFilter securityFilter;

    //depois de criar essa classe ao irmos no insominia e fazer uma requisição ele ira deixar, pois nao precisa de login e senha agora
    @Bean //para o spring reconhecer e injetar o objeto ou que estamos retornando um objeto
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
                .authorizeHttpRequests()
                .requestMatchers(HttpMethod.POST, "/login") //tipo de metodo que trabalha com url, e estamos dizendo que sera um http post(o login é um post) e o endereço o endpoint
                .permitAll() //permitindo que todos os usuarios acessem o endpoint de login
                .anyRequest() //qualquer outra requisição
                .authenticated() //precisa estar autenticado
                .and()
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class) //primeiro colocamos o nosso filtro e depois o do spring
                .build();
    }

    @Bean
    public AuthenticationManager getAuthenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
        //vai ser responsevel por instanciar e criar o objeto do AuthenticacaoController
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        //função para utilizar o Bcrypt e decodificar a senha que utiliza o bcrypt
        return new BCryptPasswordEncoder();
    }
}
