package br.dev.paulo.request.reflexao;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.stream.Stream;

public class ManipuladorMetodo {

	private Method metodo;
	private Object instancia;
	private Map<String, Object> params;
	private BiFunction<Method, InvocationTargetException, Object> tratamentoExcecao;

	public ManipuladorMetodo(Object instancia, Method metodo, Map<String, Object> params) {
		this.instancia = instancia;
		this.metodo = metodo;
		this.params = params;
	}
	
	public ManipuladorMetodo comTratamentoExcecao(BiFunction<Method, InvocationTargetException,
			Object> tratamentoExcecao) {
		this.tratamentoExcecao = tratamentoExcecao;
		
		return this;
	}
	
	public Object invocar() {
		try {
			List<Object> parametros = new ArrayList<>();
			Stream.of(metodo.getParameters())
				.forEach(p -> parametros.add(params.get(p.getName())));
			
			return metodo.invoke(instancia, parametros.toArray());
		} catch (IllegalAccessException | IllegalArgumentException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} catch (InvocationTargetException e) {
			if (tratamentoExcecao != null) {
				return tratamentoExcecao.apply(metodo, e);
			}
			
			e.printStackTrace();
			throw new RuntimeException("Erro dentro do método", e);			
		}
	}

}
