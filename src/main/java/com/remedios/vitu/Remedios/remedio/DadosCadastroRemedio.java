package com.remedios.vitu.Remedios.remedio;

//record -> torna uma classe imutavel, sem a necessidade de getter - setter - construtor - hashcode - equals
public record DadosCadastroRemedio(
        //repare que na records não usamos 'private'
        String nome,
        Via via,
        String lote,
        int quantidade,
        String validade,
        Laboratorio laboratorio) {

}
