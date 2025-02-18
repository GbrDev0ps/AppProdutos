package com.AppProdutos.AppProdutos.repository;

import com.AppProdutos.AppProdutos.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
}
