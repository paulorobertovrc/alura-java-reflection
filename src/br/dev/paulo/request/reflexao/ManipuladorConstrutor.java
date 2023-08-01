package br.dev.paulo.request.reflexao;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class ManipuladorConstrutor {

	private Constructor<?> construtor;

	public ManipuladorConstrutor(Constructor<?> construtor) {
		this.construtor = construtor;
	}

	public Object invocar() {
		try {
			return construtor.newInstance();
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} catch(InvocationTargetException e) {
			e.printStackTrace();
			throw new RuntimeException("Erro no construtor!", e.getTargetException());
		}
	}

}
