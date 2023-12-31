package br.dev.paulo.request.reflexao;

public class Reflexao {

	public ManipuladorClasse refletirClasse(String fullyQualifiedName) {		
		Class<?> classe = getClasse(fullyQualifiedName);
		
		return new ManipuladorClasse(classe);
	}

	public Class<?> getClasse(String fullyQualifiedName) {
		
		try {
			Class<?> classe = Class.forName(fullyQualifiedName);
			return classe;
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

}
