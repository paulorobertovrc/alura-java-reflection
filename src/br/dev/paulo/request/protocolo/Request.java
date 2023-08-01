package br.dev.paulo.request.protocolo;

public class Request {
	
	private String nomeControlador;
	private String nomeMetodo;

	public Request(String url) {
		String[] partesUrl = url.replaceFirst("/", "")
				.split("/");
		
		nomeControlador = Character.toUpperCase(partesUrl[0].charAt(0)) 
				+ partesUrl[0].substring(1)
				+ "Controller";
		
		nomeMetodo = partesUrl[1];
	}
	
	public String getNomeControlador() {
		return nomeControlador;
	}
	
	public String getNomeMetodo() {
		return nomeMetodo;
	}
	
}
