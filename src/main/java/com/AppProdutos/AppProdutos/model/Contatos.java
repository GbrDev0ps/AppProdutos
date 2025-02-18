package com.AppProdutos.AppProdutos.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

@Entity
@Table(name = "tb_contatos")
public class Contatos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//AutoIncrement
    private Long contatosId;

    @Column(nullable = false)
    private String tipoContato;

    @Column(nullable = false)
    private String contato;

    @ManyToOne
    @JoinColumn(name = "pessoaId", nullable = false)
    @JsonIgnoreProperties({"contatos", "endereco", "cep", "cidade", "uf"})
    private Pessoa pessoa;



    public Contatos(Integer tipoContato, String contato, Pessoa pessoa) {}

    public Contatos() {

    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public String getTipoContato() {
        return tipoContato;
    }

    public void setTipoContato(String tipoContato) {
        this.tipoContato = tipoContato;
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }


}


