package br.dev.paulo.estoque.dao;

import java.util.List;

import br.dev.paulo.estoque.model.Produto;

public interface ProdutoDao {
	
	public List<Produto> lista();
	public Produto getProduto(Integer id);
	
}
