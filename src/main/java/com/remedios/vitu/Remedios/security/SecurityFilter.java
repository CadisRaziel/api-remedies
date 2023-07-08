package com.remedios.vitu.Remedios.security;

import com.remedios.vitu.Remedios.service.TokenService;
import com.remedios.vitu.Remedios.usuarios.UsuarioRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {
    //OncePerRequestFilter -> é um filtro que vai ser executado uma vez a cada requisição (facilita na nossa criação de filtro)


    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        var tokenJWT = recuperarToken(request);

        if(tokenJWT != null){
            //validar o token
            var subject = tokenService.getSubject(tokenJWT);
            var usuario = usuarioRepository.findByLogin(subject); //achando o usuario para persistir o token ao logar


            var authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

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
        if(tokenHeader != null){
            return tokenHeader.replace("Bearer ",""); //para pegar só o token e tirar a necessidade de fica passando a palavra bearer
        }
        return null;
    }

}
