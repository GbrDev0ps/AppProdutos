package com.AppProdutos.AppProdutos.repository;

import com.AppProdutos.AppProdutos.model.Contatos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ContatosRepository extends JpaRepository<Contatos, Long> {

    @Query("SELECT c FROM Contatos c WHERE c.pessoa.nome LIKE %:nome%")
    List<Contatos> findContatosByPessoaNome(@Param("nome") String nome);



}
