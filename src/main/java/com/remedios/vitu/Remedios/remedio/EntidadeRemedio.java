package com.remedios.vitu.Remedios.remedio;


import jakarta.persistence.*;
import lombok.*;

@Entity(name = "Remedio")// -> posso nomear a entidade (vai se representada na tabela com o nome "Remedio"
@Table(name = "tb_remedio")
@Getter //-> biblioteca do lombok para gerar automatico sem eu precisar fazer ali em baixo
@Setter //-> biblioteca do lombok para gerar automatico sem eu precisar fazer ali em baixo
@AllArgsConstructor //-> biblioteca do lombok para gerar automatico sem eu precisar fazer ali em baixo
@NoArgsConstructor //-> biblioteca do lombok para gerar automatico sem eu precisar fazer ali em baixo (construtor vazio)
@EqualsAndHashCode(of = "id")
//-> biblioteca do lombok para gerar automatico sem eu precisar fazer ali em baixo (vai fazer o equals e hashcode do id)
public class EntidadeRemedio {
    //Entidade remedio, criada aqui somente para seguir o curso

    @Id //-> chave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY)// -> auto incremento (gera automaticamente o id)
    private Long id;
    private String nome;
    @Enumerated(EnumType.STRING)// -> para salvar o enum como string (e ser mapeado pelo jpa)
    private Via via;
    private String lote;
    private int quantidade;
    private String validade;

    @Enumerated(EnumType.STRING)// -> para salvar o enum como string (e ser mapeado pelo jpa)
    private Laboratorio laboratorio;


    public EntidadeRemedio(DadosCadastroRemedio dados) {
        //utilizando um construtor para pegar o DTO
        //recebendo dados do DTO para transformar em entidade
        this.nome = dados.nome();
        this.via = dados.via();
        this.lote = dados.lote();
        this.quantidade = dados.quantidade();
        this.validade = dados.validade();
        this.laboratorio = dados.laboratorio();
    }
}
