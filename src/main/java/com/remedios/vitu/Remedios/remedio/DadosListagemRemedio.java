package com.remedios.vitu.Remedios.remedio;

import java.time.LocalDate;

public record DadosListagemRemedio(
        //No dto a gente decide o que vai ser apresentado para o usuario ao pegar da entidade

        Long id,
        String nome,
        Via via,
        String lote,
        LocalDate validade,
        Laboratorio laboratorio) {

    public DadosListagemRemedio (EntidadeRemedio entidadeRemedio){
        //aqui eu pego os parametros da entidade e transformo em DTO
        this(entidadeRemedio.getId(),entidadeRemedio.getNome(), entidadeRemedio.getVia(), entidadeRemedio.getLote(), entidadeRemedio.getValidade(), entidadeRemedio.getLaboratorio());
    }
}
