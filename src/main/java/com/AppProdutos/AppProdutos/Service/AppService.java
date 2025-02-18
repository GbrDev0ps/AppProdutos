package com.AppProdutos.AppProdutos.Service;

import com.AppProdutos.AppProdutos.model.Contatos;
import com.AppProdutos.AppProdutos.model.Pessoa;
import com.AppProdutos.AppProdutos.repository.ContatosRepository;
import com.AppProdutos.AppProdutos.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AppService {
    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private ContatosRepository contatosRepository;


    public Pessoa createPessoa(Pessoa pessoa){
        return pessoaRepository.save(pessoa);
}

    public Contatos criarContato(Long pessoaId, Contatos contatos) {
        // Buscar a pessoa pelo ID
        Pessoa pessoa = pessoaRepository.findById(pessoaId)
                .orElseThrow(() -> new RuntimeException("Pessoa com ID " + pessoaId + " não encontrada"));

        // Associar a pessoa ao novo contato
        contatos.setPessoa(pessoa);

        // Adicionar o contato à lista de contatos da pessoa
        pessoa.getContatos().add(contatos);

        // Salva o contato no banco e retorna o contato criado
        return contatosRepository.save(contatos);
    }


    public List<Contatos> listarContatos(){
        return contatosRepository.findAll();
    }

    public List<Pessoa> listarPessoas(){
        return pessoaRepository.findAll();
    }

    public Object buscarPessoaPorId(Long id) {
        return pessoaRepository.findById(id);
    }

    public Pessoa atualizarPessoa(Long id, Pessoa pessoa) {
        // Verifica se a pessoa com o ID existe no banco
        Pessoa pessoaExistente = pessoaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pessoa com ID " + id + " não encontrada"));

        // Atualiza apenas os campos desejados
        pessoaExistente.setNome(pessoa.getNome()); // Exemplo: Atualiza o nome
        pessoaExistente.setCep(pessoa.getCep()); // Exemplo: Atualiza cep
        pessoaExistente.setEndereco(pessoa.getEndereco()); // Exemplo: Atualiza o endereço
        // Adicione outros campos que precisam ser atualizados

        // Salva a pessoa atualizada no banco de dados
        return pessoaRepository.save(pessoaExistente);
    }

    public Pessoa deletarPessoa(Long id) {
        // Verifica se a pessoa com o ID existe no banco
        Pessoa pessoaExistente = pessoaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pessoa com ID " + id + " não encontrada"));

        // Remove a pessoa, contatos são removidos automaticamente devido ao orphanRemoval
        pessoaRepository.delete(pessoaExistente);

        // Retorna a pessoa deletada (caso necessário) ou null
        return pessoaExistente;
    }

    public record PessoaMalaDiretaDTO(String informacoesConcatenadas) {
    }


    public List<PessoaMalaDiretaDTO> listarDadosMalaDireta() {
        // Recupera todas as pessoas no banco
        List<Pessoa> pessoas = pessoaRepository.findAll();

        // Converte cada Pessoa em um DTO
        return pessoas.stream()
                .map(pessoa -> new PessoaMalaDiretaDTO(
                        String.format("%d - %s - Endereço completo: %s, %s, %s, %s",
                                pessoa.getId(), // ID
                                pessoa.getNome(), // Nome
                                pessoa.getEndereco(), // Rua/Endereço
                                pessoa.getCep(), // CEP
                                pessoa.getCidade(), // Cidade
                                pessoa.getUf() // UF
                        )
                        ))
                .collect(Collectors.toList());




    }
    public List<Contatos> listarContatosPorPessoa(Long pessoaId) {
        // Buscar a pessoa no banco de dados pelo ID
        Pessoa pessoa = pessoaRepository.findById(pessoaId)
                .orElseThrow(() -> new RuntimeException("Pessoa com ID " + pessoaId + " não encontrada"));

        // Retornar a lista de contatos associados à pessoa
        return pessoa.getContatos();
    }

    public Pessoa retornarDadosPorContato(Long contatoId) {
        Contatos contatos = contatosRepository.findById(contatoId)
                .orElseThrow(() -> new RuntimeException("Contato com ID " + contatoId + " não encontrado"));

        return contatos.getPessoa();
    }

    public Contatos atualizarContato(Long contatoId, Contatos contatos) {
        // Busca o contato existente no banco de dados
        Contatos contatoExistente = contatosRepository.findById(contatoId)
                .orElseThrow(() -> new RuntimeException("Contato com ID " + contatoId + " não encontrado"));

        // Atualiza os campos necessários do contato
        contatoExistente.setTipoContato(contatos.getTipoContato());
        contatoExistente.setContato(contatos.getContato());

        // Salva o contato atualizado no banco de dados
        return contatosRepository.save(contatoExistente);
    }

    public Contatos deletarContato(Long contatoId) {
        // Busca o contato existente no banco de dados
        Contatos contatoExistente = contatosRepository.findById(contatoId)
                .orElseThrow(() -> new RuntimeException("Contato com ID " + contatoId + " não encontrado"));

        // Remove o contato do banco
        contatosRepository.delete(contatoExistente);

        // Retorna o contato deletado
        return contatoExistente;
    }
}