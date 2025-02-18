package com.AppProdutos.AppProdutos.resource;

import com.AppProdutos.AppProdutos.Service.AppService;
import com.AppProdutos.AppProdutos.model.Contatos;
import com.AppProdutos.AppProdutos.model.Pessoa;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@Tag(name = "Pessoas e Contatos", description = "Gerencimento de pessoas e seus meios de contatos")
public class AppProdutosResource {

    @Autowired
    private AppService appService;

    //criar pessoa
    @PostMapping("/pessoas")//http://localhost:8080/api/pessoas
    @Operation(summary = "Criar uma nova pessoa", description = "Cria uma nova pessoa no sistema baseando no esquema" +
            "criacao proposto com itens ja pre-setados")
    public Pessoa criarPessoa(@RequestBody Pessoa pessoa){
        return appService.createPessoa(pessoa);
    }

    //criar contato
    @PostMapping("/contatos/{pessoaId}")
    @Operation(summary = "Criar um novo contato", description = "Criar um novo contato para uma pessoa ja existente")
    public ResponseEntity<Contatos> criarContato(@PathVariable Long pessoaId, @RequestBody Contatos contato) {

        Contatos contatoCriado = appService.criarContato(pessoaId, contato);

        return ResponseEntity.ok(contatoCriado);
    }


    @GetMapping("/contatos")
    @Operation(summary = "Listar todos contatos", description = "Retorna uma lista de todos os contatos")
    public List<Contatos> listarContatos(){
        return appService.listarContatos();
    }

    @GetMapping("/pessoas")
    @Operation(summary = "Listar todas as pessoas", description = "Retorna uma lista de todas as pessoas")
    public List<Pessoa> listarPessoas(){
        return appService.listarPessoas();
    }

    @GetMapping("/pessoas/{id}")//http://localhost:8080/api/pessoas/
    @Operation(summary = "Buscar pessoa pelo ID", description = "Retorna uma pessoa pelo ID")
    public Object buscarPessoaPorId(@PathVariable Long id){
        return appService.buscarPessoaPorId(id);
    }

    @GetMapping("/pessoas/maladireta/{id}")//http://localhost:8080/api/pessoas/
    @Operation(summary = "Listar dados mala direta", description = "Retorna uma lista de dados mala direta")
    public List<AppService.PessoaMalaDiretaDTO> listarDadosMalaDireta(@PathVariable Long id){
        return appService.listarDadosMalaDireta();
    }

    @PutMapping("/pessoas/{id}") //http://localhost:8080/api/pessoas/
    @Operation(summary = "Atualizar os dados de uma pessoa", description = "Atualiza os dados de uma pessoa" +
            "usando seu id como parametro de mudanca")
    public Pessoa atualizarPessoa(@PathVariable Long id, @RequestBody Pessoa pessoa){
        return appService.atualizarPessoa(id, pessoa);
    }

    @DeleteMapping("/pessoas/{id}")//http://localhost:8080/api/pessoas/
    @Operation(summary = "Deletar uma pessoa", description = "Deletar uma pessoa pelo seu id")
    public String deletarPessoa(@PathVariable Long id){
        appService.deletarPessoa(id);
        return("Usuario deletado");
    }
    @GetMapping("/contatos/{pessoaId}")//http://localhost:8080/api/contatos/
    @Operation(summary = "Listar contatos de uma pessoa", description = "Retorna uma lista de contatos de uma pessoa")
    public ResponseEntity<List<Contatos>> listarContatosPorPessoa(@PathVariable Long pessoaId) {
        List<Contatos> contatos = appService.listarContatosPorPessoa(pessoaId); // Chama o método do service
        return ResponseEntity.ok(contatos); // Retorna a lista de contatos no ResponseEntity
    }

    @GetMapping("/contatos/{pessoaId}/dados")//http://localhost:8080/api/contatos/{pessoaId}/dados
    @Operation(summary = "Retornar dados de Pessoa pelo id Contato", description = "Retorna os dados da pessoa" +
            " que esta vinculada ao id contato")
    public ResponseEntity<Object> retornarDadosPorContato(@PathVariable Long pessoaId) {
        Object dadosContato = appService.retornarDadosPorContato(pessoaId);
        return ResponseEntity.ok(dadosContato);
    }

    @PutMapping("/contatos/{pessoaId}/dados/atualizar")//http://localhost:8080/api/contatos/{pessoaId}/dados/atualizar
    @Operation(summary = "Atualizar contato", description = "Atualizar contato de uma pessoa pelo Id")
    public ResponseEntity<Contatos> atualizarContato(@PathVariable Long pessoaId, @RequestBody Contatos contatos) {
        Contatos contatoAtualizado = appService.atualizarContato(pessoaId, contatos);
        return ResponseEntity.ok(contatoAtualizado);
    }

    @DeleteMapping("/contatos/{pessoaId}/dados/deletar")//http://localhost:8080/api/contatos/{pessoaId}/dados/deletar
    @Operation(summary = "Deletar contato", description = "Deletar contato de uma pessoa pelo Id")
    public ResponseEntity<Contatos> deletarContato(@PathVariable Long pessoaId) {
        Contatos contatoDeletado = appService.deletarContato(pessoaId);
        return ResponseEntity.ok(contatoDeletado);
    }
}
