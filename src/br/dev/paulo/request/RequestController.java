package br.dev.paulo.request;

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
		
//		Object instanciaControle = new Reflexao()
//				.refletirClasse(pacoteBase + nomeControlador)
//				.getConstrutorPadrao()
//				.invocar();
		
		Object retorno = new Reflexao()
				.refletirClasse(pacoteBase + nomeControlador)
				.criarInstancia()
				.getMetodo(nomeMetodo)
				.invocar();
		
		System.out.println(retorno);
			
//		System.out.println("CONTROLADOR: " + instanciaControle);
//		System.out.println("MÉTODO: " + nomeMetodo);
		
		return retorno;
	}
	
}
