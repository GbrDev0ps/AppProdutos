package com.AppProdutos.AppProdutos.resource;

import com.AppProdutos.AppProdutos.Service.AppService;
import com.AppProdutos.AppProdutos.model.Contatos;
import com.AppProdutos.AppProdutos.model.Pessoa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class AppProdutosResource {

    @Autowired
    private AppService appService;

    //criar pessoa
    @PostMapping("/pessoas")//http://localhost:8080/api/pessoas
    public Pessoa criarPessoa(@RequestBody Pessoa pessoa){
        return appService.createPessoa(pessoa);
    }

    //criar contato
    @PostMapping("/contatos/{pessoaId}")
    public ResponseEntity<Contatos> criarContato(@PathVariable Long pessoaId, @RequestBody Contatos contato) {

        Contatos contatoCriado = appService.criarContato(pessoaId, contato);

        return ResponseEntity.ok(contatoCriado);
    }


    @GetMapping("/contatos")
    public List<Contatos> listarContatos(){
        return appService.listarContatos();
    }

    @GetMapping("/pessoas")
    public List<Pessoa> listarPessoas(){
        return appService.listarPessoas();
    }

    @GetMapping("/pessoas/{id}")//http://localhost:8080/api/pessoas/
    public Object buscarPessoaPorId(@PathVariable Long id){
        return appService.buscarPessoaPorId(id);
    }

    @GetMapping("/pessoas/maladireta/{id}")//http://localhost:8080/api/pessoas/
    public List<AppService.PessoaMalaDiretaDTO> listarDadosMalaDireta(@PathVariable Long id){
        return appService.listarDadosMalaDireta();
    }

    @PutMapping("/pessoas/{id}") //http://localhost:8080/api/pessoas/
    public Pessoa atualizarPessoa(@PathVariable Long id, @RequestBody Pessoa pessoa){
        return appService.atualizarPessoa(id, pessoa);
    }

    @DeleteMapping("/pessoas/{id}")//http://localhost:8080/api/pessoas/
    public String deletarPessoa(@PathVariable Long id){
        appService.deletarPessoa(id);
        return("Usuario deletado");
    }
    @GetMapping("/contatos/{pessoaId}")//http://localhost:8080/api/contatos/
    public ResponseEntity<List<Contatos>> listarContatosPorPessoa(@PathVariable Long pessoaId) {
        List<Contatos> contatos = appService.listarContatosPorPessoa(pessoaId); // Chama o método do service
        return ResponseEntity.ok(contatos); // Retorna a lista de contatos no ResponseEntity
    }

    @GetMapping("/contatos/{pessoaId}/dados")//http://localhost:8080/api/contatos/{pessoaId}/dados
    public ResponseEntity<Object> retornarDadosPorContato(@PathVariable Long pessoaId) {
        Object dadosContato = appService.retornarDadosPorContato(pessoaId);
        return ResponseEntity.ok(dadosContato);
    }

    @PutMapping("/contatos/{pessoaId}/dados/atualizar")//http://localhost:8080/api/contatos/{pessoaId}/dados/atualizar
    public ResponseEntity<Contatos> atualizarContato(@PathVariable Long pessoaId, @RequestBody Contatos contatos) {
        Contatos contatoAtualizado = appService.atualizarContato(pessoaId, contatos);
        return ResponseEntity.ok(contatoAtualizado);
    }

    @DeleteMapping("/contatos/{pessoaId}/dados/deletar")//http://localhost:8080/api/contatos/{pessoaId}/dados/deletar
    public ResponseEntity<Contatos> deletarContato(@PathVariable Long pessoaId) {
        Contatos contatoDeletado = appService.deletarContato(pessoaId);
        return ResponseEntity.ok(contatoDeletado);
    }
}
