package br.dev.paulo.request.reflexao;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ManipuladorMetodo {

	private Method metodo;
	private Object instancia;

	public ManipuladorMetodo(Object instancia, Method metodo) {
		this.instancia = instancia;
		this.metodo = metodo;
	}
	
	public Object invocar() {
		try {
			return metodo.invoke(instancia);
		} catch (IllegalAccessException | IllegalArgumentException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} catch (InvocationTargetException e) {
			e.printStackTrace();
			throw new RuntimeException("Erro dentro do método", e);			
		}
	}

}
