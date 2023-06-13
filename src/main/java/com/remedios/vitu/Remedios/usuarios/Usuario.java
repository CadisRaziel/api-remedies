package com.remedios.vitu.Remedios.usuarios;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "Usuario")// -> posso nomear a entidade (vai se representada na tabela com o nome "Remedio"
@Table(name = "tb_usuario")
@Getter //-> biblioteca do lombok para gerar automatico sem eu precisar fazer ali em baixo
@Setter //-> biblioteca do lombok para gerar automatico sem eu precisar fazer ali em baixo
@AllArgsConstructor //-> biblioteca do lombok para gerar automatico sem eu precisar fazer ali em baixo
@NoArgsConstructor //-> biblioteca do lombok para gerar automatico sem eu precisar fazer ali em baixo (construtor vazio)
@EqualsAndHashCode(of = "id")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)// -> auto incremento (gera automaticamente o id)
    private Long id;
    private String login;
    private String senha;

}
