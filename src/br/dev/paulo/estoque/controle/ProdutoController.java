package br.dev.paulo.estoque.controle;

import java.util.List;
import java.util.stream.Collectors;

import br.dev.paulo.estoque.dao.ProdutoDao;
import br.dev.paulo.estoque.model.Produto;

public class ProdutoController {
	
	private ProdutoDao produtoDao;
	
	public ProdutoController(ProdutoDao produtoDao) {
		this.produtoDao = produtoDao;
	}
	
	public List<Produto> lista() {
		return produtoDao.lista();
	}
	
	public Produto obtem(Integer id) {
		return produtoDao.getProduto(id);
	}
	
	public List<Produto> filtra(String nome) {
		return produtoDao.lista().stream()
				.filter(produto -> produto.getNome().toLowerCase().startsWith(nome.toLowerCase()))
				.collect(Collectors.toList());
	}
	
	public List<Produto> filtra(String nome, String marca) {
		return produtoDao.lista().stream()
				.filter(produto -> produto.getNome().toLowerCase().startsWith(nome.toLowerCase())
						&& produto.getMarca().equalsIgnoreCase(marca))
				.collect(Collectors.toList());
	}
	
}
