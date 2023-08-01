package br.dev.paulo.estoque.controle;

import java.util.List;

import br.dev.paulo.estoque.dao.ProdutoDaoMock;
import br.dev.paulo.estoque.model.Produto;

public class ProdutoController {
	
	private ProdutoDaoMock produtoDao;
	
	public ProdutoController() {
		produtoDao = new ProdutoDaoMock();
	}
	
	public List<Produto> lista() {
		return produtoDao.lista();
	}
	
	public Produto obtem(Integer id) {
		return produtoDao.getProduto(id);
	}
	
}
