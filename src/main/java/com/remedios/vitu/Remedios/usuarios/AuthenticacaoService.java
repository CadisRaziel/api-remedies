package com.remedios.vitu.Remedios.usuarios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service // -> vai ser lida como um serviço
public class AuthenticacaoService implements UserDetailsService {
    //UserDetailsService -> implementando essa interface o spring vai entender que essa classe "@Service" vai ser um serviço de authenticação


    @Autowired
    private UsuarioRepository usuarioRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //verifica se o login vai estar correto
        return usuarioRepository.findByLogin(username);
    }
}
