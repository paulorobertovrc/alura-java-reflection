package br.dev.paulo.request.reflexao;

public class Reflexao {

	public ManipuladorClasse refletirClasse(String fullyQualifiedName) {
		try {
			Class<?> classe = Class.forName(fullyQualifiedName);
			
			return new ManipuladorClasse(classe);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}		
	}

}
