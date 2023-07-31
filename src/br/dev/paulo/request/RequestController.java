package br.dev.paulo.request;

public class RequestController {
	
	private String pacoteBase;
	
	public RequestController(String pacoteBase) {
		this.pacoteBase = pacoteBase;
	}
	
	@SuppressWarnings("deprecation")
	public Object executar(String url) {
		String[] partesUrl = url.replaceFirst("/", "")
				.split("/");
		
		String nomeControlador = Character.toUpperCase(partesUrl[0].charAt(0)) 
				+ partesUrl[0].substring(1)
				+ "Controller";
		
		try {
			Class<?> classeControle = Class.forName(pacoteBase + nomeControlador);
			Object instanciaControle = classeControle.newInstance();
			
			System.out.println(instanciaControle);
			
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException  e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
		return null;
	}
	
	
}
