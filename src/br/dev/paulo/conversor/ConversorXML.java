package br.dev.paulo.conversor;

import java.lang.reflect.Field;
import java.util.Collection;

import br.dev.paulo.conversor.anotacao.NomeTagXml;

public class ConversorXML {
	
	public String converter(Object objeto) {
		
		try {
			Class<?> classeObjeto = objeto.getClass();
			StringBuilder xmlBuilder = new StringBuilder();
			
			if (objeto instanceof Collection) {
				Collection<?> colecao = (Collection<?>) objeto;
				xmlBuilder.append("<lista>");
				
				for (Object o : colecao) {
					String xml = converter(o);
					xmlBuilder.append(xml);
				}
				
				xmlBuilder.append("</lista>");
			} else {
				String nomeClasse;
				
				if (classeObjeto.isAnnotationPresent(NomeTagXml.class)) {
					nomeClasse = classeObjeto.getDeclaredAnnotation(NomeTagXml.class).value();
				} else {
					nomeClasse = classeObjeto.getName();
				}				
				
				xmlBuilder.append("<" + nomeClasse + ">");
				
				for (Field atributo : classeObjeto.getDeclaredFields()) {
					atributo.setAccessible(true);
					
					NomeTagXml anotacaoAtributo = atributo.getDeclaredAnnotation(NomeTagXml.class);
					
					String nomeAtributo =
							anotacaoAtributo == null ? atributo.getName() : anotacaoAtributo.value();
					Object valorAtributo = atributo.get(objeto);
					
					xmlBuilder.append("<" + nomeAtributo + ">");
					xmlBuilder.append(valorAtributo);
					xmlBuilder.append("</" + nomeAtributo + ">");
				}
				
				xmlBuilder.append("</" + nomeClasse + ">");
			}
			
			return xmlBuilder.toString();
			
		} catch (IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
			throw new RuntimeException("Erro na geração do XML");
		}
	}
	
}
