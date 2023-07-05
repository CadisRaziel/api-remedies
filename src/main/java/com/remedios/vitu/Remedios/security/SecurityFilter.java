package com.remedios.vitu.Remedios.security;

import com.remedios.vitu.Remedios.service.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {
    //OncePerRequestFilter -> é um filtro que vai ser executado uma vez a cada requisição (facilita na nossa criação de filtro)


    @Autowired
    private TokenService tokenService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        var tokenJWT = recuperarToken(request);
        var subject = tokenService.getSubject(tokenJWT);



        filterChain.doFilter(request,response);
    }

    private String recuperarToken(HttpServletRequest request) {
        var tokenHeader = request.getHeader("Authorization");
        /*
        //Jeito correto de implementar
        if(tokenHeader == null || tokenHeader.isEmpty() || !tokenHeader.startsWith("Bearer ")){
            throw new RuntimeException("Token inexistente ou mal formatado!");
        }
        return tokenHeader.substring(7,tokenHeader.length());
         */

        //Jeito rapido do video de implementar
        if(tokenHeader == null){
            throw new RuntimeException("Token não enviado!");
        }
        return tokenHeader;
    }

}
