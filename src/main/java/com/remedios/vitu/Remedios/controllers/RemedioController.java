package com.remedios.vitu.Remedios.controllers;


import com.remedios.vitu.Remedios.remedio.DadosCadastroRemedio;
import com.remedios.vitu.Remedios.remedio.DadosListagemRemedio;
import com.remedios.vitu.Remedios.remedio.EntidadeRemedio;
import com.remedios.vitu.Remedios.remedio.RemedioRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/remedios")
public class RemedioController {

    /*
    @PostMapping
    public void cadastrarTest01(@RequestBody String json){
        //eu coloquei um body na requisição do get la no insomnia
        //ao fazer essa função eu tento recuperar os dados que tem la aqui no terminal
        //para isso usamos o requestBody
        System.out.println(json);
        }


        O objeto abaixo são os dados que eu estou enviando no body do insomina para receber no print aqui !!
        {
	        "nome": "Dipirona",
	        "via": "oral",
	        "lote": "2",
	        "quantidade": 10,
	        "validade": "2023",
	        "laboratorio": "medley"

}
    */

    /*
    //agora vamos criar uma entidade e um dto
    @PostMapping
    public void cadastrarTest02(@RequestBody DadosCadastroRemedio dados) {
        //eu coloquei um body na requisição do get la no insomnia
        //ao fazer essa função eu tento recuperar os dados que tem la aqui no terminal
        //para isso usamos o requestBody

        //Repare que na classe report DadosCadastroRemedio eu tenho 'enum' ou seja eu preciso passar em letra MAIUSCULA la no body do insomina
        //Caso eu nao envie algum dado la no json, ele nao vai dar erro, ele vai vir como 'NULL'
        System.out.println(dados);
    }
*/
    //==============================================================================================================\\

    @Autowired // -> injeção de dependencia do spring booot
    private RemedioRepository remedioRepository;

    @PostMapping
    @Transactional //-> quando nossa requisição der problema o transactional vai fazer um rollback, vai fazer com que a requisição seja repetida se houver algum problema
    //Valid -> para validar as validation do DTO
    //void -> não vai ter resposta (nao retorna nada)
    public void cadastrar(@RequestBody @Valid DadosCadastroRemedio dados){
        remedioRepository.save(new EntidadeRemedio(dados));
    }

    @GetMapping
    //List<DadosListagemRemedio> -> vai ter uma resposta de lista de objeto (vai retornar uma lista de objeto)
    public List<DadosListagemRemedio> listar(){
        //meu retorno é uma lista de objeto DTO DadosListagemRemedio
        //porém o findAll está esperando uma lista de EntidadeRemedio
        //com isso precisamos converter a lista de DadosListagemRemedio para EntidadeRemedio
        //aqui usaremos stream e map para realizar essa conversão
        //::new -> para chamar o construtor da classe DadosListagemRemedio
        return remedioRepository.findAll().stream().map(DadosListagemRemedio::new).toList();
    }

}