package com.remedios.vitu.Remedios.usuarios;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity(name = "Usuario")// -> posso nomear a entidade (vai se representada na tabela com o nome "Remedio"
@Table(name = "tb_usuario")
@Getter //-> biblioteca do lombok para gerar automatico sem eu precisar fazer ali em baixo
@Setter //-> biblioteca do lombok para gerar automatico sem eu precisar fazer ali em baixo
@AllArgsConstructor //-> biblioteca do lombok para gerar automatico sem eu precisar fazer ali em baixo
@NoArgsConstructor //-> biblioteca do lombok para gerar automatico sem eu precisar fazer ali em baixo (construtor vazio)
@EqualsAndHashCode(of = "id")
public class EntidadeUsuario implements UserDetails {
    //UserDetails -> para o spring identifica que quando o usuario por o login e senha ele pegue os campos abaixo !!

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)// -> auto incremento (gera automaticamente o id)
    private Long id;
    private String login;
    private String senha;


    //As configurações abaixo são do UserDetails
    //elas dao metodos para expirar o token, bloquear o usuario, etc...

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //metodo responsavel pelo controle de acesso, exemplo: o usuario não tera acesso ao painel de admin
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
