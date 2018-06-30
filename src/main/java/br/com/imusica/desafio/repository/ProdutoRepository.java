package br.com.imusica.desafio.repository;

import java.util.List;

import org.springframework.data.repository.Repository;

import br.com.imusica.desafio.model.Produto;

public interface ProdutoRepository extends Repository<Produto, String> {

	Produto save(Produto produto);
	
	Produto findById(String id);
	
	Produto findByNome(String nome);
	
	List<Produto> findAll();
	
	void deleteById(String id);
	
	void deleteAll();
}
