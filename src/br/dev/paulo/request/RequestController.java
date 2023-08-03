package br.dev.paulo.request;

import java.util.Map;

import br.dev.paulo.request.protocolo.Request;
import br.dev.paulo.request.reflexao.Reflexao;

public class RequestController {
	
	private String pacoteBase;
	
	public RequestController(String pacoteBase) {
		this.pacoteBase = pacoteBase;
	}
	
	public Object executar(String url) {
		Request request = new Request(url);
		
		String nomeControlador = request.getNomeControlador();
		String nomeMetodo = request.getNomeMetodo();
		Map<String,Object> params = request.getQueryParams();
		
		Object retorno = new Reflexao()
				.refletirClasse(pacoteBase + nomeControlador)
				.criarInstancia()
				.getMetodo(nomeMetodo, params)
				.invocar();
		
		System.out.println(retorno);
		
		return retorno;
	}
	
}
