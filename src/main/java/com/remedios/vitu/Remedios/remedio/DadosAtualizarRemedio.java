package com.remedios.vitu.Remedios.remedio;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizarRemedio(
    //aqui no dto vamo escolher os campos que serão atualizados (o id é obrigatorio)
        @NotNull
        Long id,
        String nome,
        Via via,
        Laboratorio laboratorio) {

}
