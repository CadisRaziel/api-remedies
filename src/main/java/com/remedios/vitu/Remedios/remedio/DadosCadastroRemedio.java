package com.remedios.vitu.Remedios.remedio;

import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

//record -> torna uma classe imutavel, sem a necessidade de getter - setter - construtor - hashcode - equals
public record DadosCadastroRemedio(
        //repare que na records não usamos 'private'


        @NotBlank // -> porém o nome é String e o usuario pode enviar "" ou " " e isso não é nulo
        //-> Com isso podemos tirar o @NotNull pois o @NotBlank já faz essa verificação do @NotNull junto
        String nome,
        @Enumerated
        Via via,
        @NotBlank
        String lote,

        int quantidade,

        @Future //-> Não pode ser uma data de ontem, tem que ser de hoje em diante
        LocalDate validade,
        @Enumerated
        Laboratorio laboratorio) {

}
