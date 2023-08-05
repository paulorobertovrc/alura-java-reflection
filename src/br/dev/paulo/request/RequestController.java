package br.dev.paulo.request;

import java.util.Map;

import br.dev.paulo.conversor.ConversorXML;
import br.dev.paulo.request.ioc.ContainerIoC;
import br.dev.paulo.request.protocolo.Request;
import br.dev.paulo.request.reflexao.ManipuladorObjeto;
import br.dev.paulo.request.reflexao.Reflexao;

public class RequestController {
	
	private String pacoteBase;
	private ContainerIoC container;
	
	public RequestController(String pacoteBase) {
		this.pacoteBase = pacoteBase;
		this.container = new ContainerIoC();
	}
	
	public Object executar(String url) {
		Request request = new Request(url);
		
		String nomeControlador = request.getNomeControlador();
		String nomeMetodo = request.getNomeMetodo();
		Map<String,Object> params = request.getQueryParams();
		
		Class<?> classeControlador = new Reflexao().getClasse(pacoteBase + nomeControlador);
		Object instanciaControlador = container.getInstancia(classeControlador);		
		Object retorno = new ManipuladorObjeto(instanciaControlador)
				.getMetodo(nomeMetodo, params)
				.comTratamentoExcecao((metodo, ex) -> {
					System.out.println("Erro no método " + metodo.getName() + " da classe " + 
							metodo.getDeclaringClass().getName() + ".\n\n");
					throw new RuntimeException("Erro no método!");
				})
				.invocar();
		
		System.out.println(retorno);
		
		retorno = new ConversorXML().converter(retorno);
		
		return retorno;
	}

	public <T, K extends T> void registrar(Class<T> tipoFonte, Class<K> tipoDestino) {
		container.registrar(tipoFonte, tipoDestino);
	}
	
}
