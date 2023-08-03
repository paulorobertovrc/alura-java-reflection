package br.dev.paulo.request.protocolo;

import java.util.HashMap;
import java.util.Map;

public class Request {
	
	private String nomeControlador;
	private String nomeMetodo;
	private Map<String,Object> queryParams;

	public Request(String url) {
		String[] partesUrl = url.replaceFirst("/", "")
				.split("[?]");
		
		String[] controladorEMetodo = partesUrl[0].split("/");
		
		nomeControlador = Character.toUpperCase(controladorEMetodo[0].charAt(0)) 
				+ controladorEMetodo[0].substring(1)
				+ "Controller";
		
		nomeMetodo = controladorEMetodo[1];
		
		if (partesUrl.length > 1) {
			queryParams = new QueryParamsBuilder().withParams(partesUrl[1]).build();
		} else {
			queryParams = new HashMap<String,Object>();
		}
	}
	
	public String getNomeControlador() {
		return nomeControlador;
	}
	
	public String getNomeMetodo() {
		return nomeMetodo;
	}
	
	public Map<String,Object> getQueryParams() {
		return queryParams;
	}
	
}
