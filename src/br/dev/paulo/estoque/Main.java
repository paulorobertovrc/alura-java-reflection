package br.dev.paulo.estoque;

import java.util.Scanner;

import br.dev.paulo.request.RequestController;

public class Main {

	/**
	 * SIMULA O NAVEGADOR
	 */

	public static void main(String[] args) {
		
		/**
		 * /controlador/metodo
		 * /controlador/metodo?param1=valor1&param2=valor2
		 */
		
		try (Scanner s = new Scanner(System.in)) {
			System.out.print(">>> ");
			String url = s.nextLine();
			
			RequestController request = new RequestController("br.dev.paulo.estoque.controle.");
			
			while(!url.equals("exit") && !url.isBlank()) {
				Object response = request.executar(url);
				
				System.out.println("Response: " + response);
				
				System.out.print(">>> ");
				url = s.nextLine();
			}
			
		}		
	}

}
