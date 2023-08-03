package br.dev.paulo.request.reflexao;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.stream.Stream;

public class ManipuladorObjeto {

	private Object instancia;

	public ManipuladorObjeto(Object instancia) {
		this.instancia = instancia;
	}

	public ManipuladorMetodo getMetodo(String nomeMetodo, Map<String, Object> params) {
		
		Stream<Method> metodos = Stream.of(instancia.getClass().getDeclaredMethods());
		Method metodoSelecionado = metodos.filter(metodo -> metodo.getName().equals(nomeMetodo)
					&& metodo.getParameterCount() == params.values().size()
					&& Stream.of(metodo.getParameters())
						.allMatch(argMetodo -> {
							System.out.println(argMetodo.getName());
							return params.keySet().contains(argMetodo.getName())
							&& params.get(argMetodo.getName()).getClass().equals(argMetodo.getType());
							})
						)
				.findFirst()
				.orElseThrow(() -> new RuntimeException("Método não encontrado"));
		
		return new ManipuladorMetodo(instancia, metodoSelecionado, params);
	}	
}
