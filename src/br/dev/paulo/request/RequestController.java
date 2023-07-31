package br.dev.paulo.request;

import java.lang.reflect.InvocationTargetException;

public class RequestController {
	
	private String pacoteBase;
	
	public RequestController(String pacoteBase) {
		this.pacoteBase = pacoteBase;
	}
	
	public Object executar(String url) {
		String[] partesUrl = url.replaceFirst("/", "")
				.split("/");
		
		String nomeControlador = Character.toUpperCase(partesUrl[0].charAt(0)) 
				+ partesUrl[0].substring(1)
				+ "Controller";
		
		try {
			Class<?> classeControle = Class.forName(pacoteBase + nomeControlador);
			Object instanciaControle = classeControle.getDeclaredConstructor().newInstance();
			
			System.out.println(instanciaControle);
			
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException | 
				NoSuchMethodException | SecurityException  e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} catch (InvocationTargetException e) {
			e.printStackTrace();
			throw new RuntimeException("Erro no construtor!", e.getTargetException());
		}
		
		return null;
	}
	
	
}
