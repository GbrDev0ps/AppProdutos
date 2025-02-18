package com.AppProdutos.AppProdutos.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_pessoas")
public class Pessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //AutoIncrement
    private Long pessoaId;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = true)
    private String endereco;

    @Column(nullable = true)
    private String cep;

    @Column(nullable = true)
    private String cidade;

    @Column(nullable = true)
    private String uf;

    @OneToMany(mappedBy = "pessoa", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Contatos> contatos = new ArrayList<>();

    public List<Contatos> getContatos() {
        return contatos;
    }

    public Pessoa(){}
    public Pessoa(String nome, String endereco, String cep, String cidade, String uf) {}

    public void setContatos(List<Contatos> contatos) {
        this.contatos = contatos;
    }

    public void setId(Long id) {
        this.pessoaId = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public Object getId() {
        return pessoaId;
    }
}
