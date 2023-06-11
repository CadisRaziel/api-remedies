package com.remedios.vitu.Remedios.controllers;


import com.remedios.vitu.Remedios.remedio.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

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
    public ResponseEntity<DadosDetalhamentoRemedio> cadastrar(@RequestBody @Valid DadosCadastroRemedio dados, UriComponentsBuilder uriBuilder){
        //ResponseEntity -> o padrao quando vamos cadastrar um novo registro é o 201
        //devemos devolver o que foi cadastrado
        //devemos devolver o caminho para acessar o que foi cadastrado (URL e o id)

        var remedio = new EntidadeRemedio(dados); //-> nossa entidade recebe como parametro o dto
        remedioRepository.save(remedio);

        //apos salvar o remedio, devemos devolver o caminho para acessar o que foi cadastrado (URL e o id)
        //UriComponentsBuilder - >essa classe cria o endereço de registro automatico
        //buildAndExpand -> devolve o id que foi criado
        var uri = uriBuilder.path("/remedios/{id}").buildAndExpand(remedio.getId()).toUri();

        //created -> retorna a url
        //body -> retorna o dto
        return ResponseEntity.created(uri).body(new DadosDetalhamentoRemedio(remedio));
    }

    @GetMapping
    //List<DadosListagemRemedio> -> vai ter uma resposta de lista de objeto (vai retornar uma lista de objeto)
    public ResponseEntity<List<DadosListagemRemedio>> listar(){
        //meu retorno é uma lista de objeto DTO DadosListagemRemedio
        //porém o findAll está esperando uma lista de EntidadeRemedio
        //com isso precisamos converter a lista de DadosListagemRemedio para EntidadeRemedio
        //aqui usaremos stream e map para realizar essa conversão
        //::new -> para chamar o construtor da classe DadosListagemRemedio
        var list = remedioRepository.findAll().stream().map(DadosListagemRemedio::new).toList(); //-> Apresenta a lista de objetos
        return ResponseEntity.ok(list); //ok -> 200
    }

    @GetMapping("/ativos")
    //List<DadosListagemRemedio> -> vai ter uma resposta de lista de objeto (vai retornar uma lista de objeto)
    public ResponseEntity<List<DadosListagemRemedio>>  listarAtivos(){
        //meu retorno é uma lista de objeto DTO DadosListagemRemedio
        //porém o findAll está esperando uma lista de EntidadeRemedio
        //com isso precisamos converter a lista de DadosListagemRemedio para EntidadeRemedio
        //aqui usaremos stream e map para realizar essa conversão
        //::new -> para chamar o construtor da classe DadosListagemRemedio
        var list = remedioRepository.findAllByAtivoTrue().stream().map(DadosListagemRemedio::new).toList(); //-> Apresenta a lista de objetos somente com os ativos
        return ResponseEntity.ok(list); //ok -> 200
    }



    @PutMapping //-> Repare que não colocamos o ID aqui, porque nós vamos enviar o ID pelo body !!!
    @Transactional
    public ResponseEntity<DadosDetalhamentoRemedio> atualizarRegistroDoBanco(@RequestBody @Valid DadosAtualizarRemedio dados){
        //aqui eu preciso pegar o id do remedio que eu quero atualizar
        //getReferenceById -> pega a referencia do remedio pelo id
        var remedio = remedioRepository.getReferenceById(dados.id());

        //atualizarInformacoes é um metodo que eu posso fazer dentro da propria entidade para atualizar o dado
        remedio.atualizarInformacoes(dados);

        //quando vamos devolver uma resposta não podemos utilizar diretamente a entidade
        return ResponseEntity.ok(new DadosDetalhamentoRemedio(remedio));
    }


    @DeleteMapping("/{id}")
    @Transactional
    //PathVariable -> vai estar falando pro spring que o id do parametro da função é o mesmo id que está na url e que esta no DeleteMapping acima
    //aqui será feita a exclusão total
    public ResponseEntity<Void> excluirRemedio(@PathVariable Long id){
        remedioRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    //Criando o delete lógico (quando temos relações de tabelas se excluirmos tudo da problema)
    //com isso criamos o delete lógico, que vai ser um boolean que vai ativar e desativar o atributo (dando a imrpessão de deletado)
    @DeleteMapping("inativar/{id}")
    @Transactional
    //ResponseEntity -> dar as resposta corretas (200 - 204 - 201 - 400 - 404)
    public ResponseEntity<Void> inativarRemedio(@PathVariable Long id){
        var remedio = remedioRepository.getReferenceById(id);
        remedio.inativar();
        return ResponseEntity.noContent().build();
    }

    //Metodo para ativar novamente o remedio
    @PutMapping("ativar/{id}")
    @Transactional
    public ResponseEntity<Void> ativarRemedio(@PathVariable Long id){
        var remedio = remedioRepository.getReferenceById(id);
        remedio.ativar();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoRemedio> pegarPeloId(@PathVariable Long id){
        var remedio = remedioRepository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoRemedio(remedio));
    }


}