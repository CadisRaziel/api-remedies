package com.remedios.vitu.Remedios.remedio;

import java.time.LocalDate;

public record DadosDetalhamentoRemedio(
        Long id,
        String nome,
        Via via,
        String lote,
        int quantidade,
        LocalDate validade,
        Laboratorio laboratorio,
        Boolean ativo
) {
    public DadosDetalhamentoRemedio(EntidadeRemedio entidadeRemedio) {
        this(
                entidadeRemedio.getId(),
                entidadeRemedio.getNome(),
                entidadeRemedio.getVia(),
                entidadeRemedio.getLote(),
                entidadeRemedio.getQuantidade(),
                entidadeRemedio.getValidade(),
                entidadeRemedio.getLaboratorio(),
                entidadeRemedio.getAtivo()
        );
    }
}
