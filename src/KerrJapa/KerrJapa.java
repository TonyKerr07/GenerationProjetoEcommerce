package KerrJapa;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.InputMismatchException;
import java.util.Scanner;
import KerrJapa.model.Pedido;
import KerrJapa.util.Cores;
import KerrJapa.Controller.KerrJapaController;

public class KerrJapa {

	public static void main(String[] args) {
		// Projeto Generation E-Commerce - KerrJapa
		// Programa de abrir pedidos em um restaurante japonês
		// Criar, visualizar, alterar e deletar pedidos
		
		KerrJapaController pedidos = new KerrJapaController();
		
		Scanner ler = new Scanner(System.in);
		int menu, busca, qtd, roda = 1, itenscardapio, selecao = 0, opcardapio, alteraCardapio, removeCardapio, linhaPedido;
		String cliente, adicionaNomeCardapio;
		float valorTotal = 0f, valorItem, adicionaValorCardapio;
		DecimalFormat decimalFormat = new DecimalFormat("R$ #,##0.00");
		
		Object[][] itens = new Object[99][4], cardapio = new Object[99][4];
		
		cardapio[0][0] = 1;
		cardapio[0][1] = "hot roll";
		cardapio[0][2] = 9.99f;
		cardapio[0][3] = "R$ 9,99";
		cardapio[1][0] = 2;
		cardapio[1][1] = "sashimi";
		cardapio[1][2] = 4.90f;
		cardapio[1][3] = "R$ 4,90";
				
		System.out.println(Cores.TEXT_YELLOW + Cores.ANSI_BLACK_BACKGROUND +
						   "*********************************************");
		System.out.println("  _   _                                      ");
		System.out.println(" | | / / ___  ___  ___    __  ___  ___  ___  ");
		System.out.println(" | |/ / | __|| _ || _ |  |_ || _ || _ || _ | ");
		System.out.println(" |   /  ||__ ||_||||_||    ||||_||||_||||_|| ");
		System.out.println(" |   \\  | __||  _||   |    ||| _ || __|| _ | ");
		System.out.println(" | |\\ \\ ||__ ||\\\\ ||\\\\  ||_|||| ||||   || || ");
		System.out.println(" |_| \\_\\|___||| \\\\|| \\\\ |___||| ||||   || || ");
		System.out.println("                                             ");
		System.out.println("*********************************************" + Cores.TEXT_RESET);
		
		do {
		System.out.println(Cores.TEXT_YELLOW + Cores.ANSI_BLACK_BACKGROUND +
						   "*********************************************");
		System.out.println("                                             ");
		System.out.println("                  KERR JAPA                  ");
		System.out.println("                                             ");
		System.out.println("*********************************************");
		System.out.println("                                             ");
		System.out.println("            1 - Adicionar pedido             ");
		System.out.println("            2 - Listar pedidos               ");
		System.out.println("            3 - Buscar pedido                ");
		System.out.println("            4 - Atualizar pedido             ");
		System.out.println("            5 - Apagar pedido                ");
		System.out.println("            6 - Cardápio                     ");
		System.out.println("            9 - Sair                         ");
		System.out.println("                                             ");
		System.out.println("*********************************************");
		System.out.println("Entre com a opção desejada:                  ");
		System.out.println("                                             " + Cores.TEXT_RESET);
		
		try {
			menu = ler.nextInt();
		}catch(InputMismatchException e){
			System.out.println("\nDigite valores inteiros!");
			ler.nextLine();
			menu=0;
		}
		
		
		switch (menu) {
		case 1 -> {
			System.out.println("Adicionar pedido: ");
			System.out.println("Digite o nome do cliente: ");
			ler.skip("\\R?");
			cliente = ler.nextLine();
			linhaPedido = 0;
			Object[][] itensPedido = new Object[99][4];
			
						
			do {
				
				System.out.println("Cardápio: ");
				
				itenscardapio = 0;
				for (int i = 0; i < cardapio.length; i ++) {
					if(cardapio[i][0] != null) {
						itenscardapio ++;
						cardapio[i][0] = itenscardapio;
					}
				}
				for (int i = 0; i < itenscardapio; i ++) {
					System.out.println(cardapio[i][0] + " - " + cardapio[i][1] + " - " + cardapio[i][3]);
				}
				
				System.out.println("Selecione o item a ser adicionado: ");
				
				do {
					try {
						selecao = ler.nextInt();
					}catch(InputMismatchException e){
						System.out.println("\nDigite valores inteiros!");
						ler.nextLine();
					}
				}while(selecao < 1 && selecao > itenscardapio);
				
				selecao --;
				
				System.out.println("\nDigite a quantidade: ");
				try {
					qtd = ler.nextInt();
				}catch(InputMismatchException e){
					System.out.println("\nDigite valores inteiros!");
					ler.nextLine();
					qtd = 1;
				}
				
				itens[linhaPedido][0] = cardapio[selecao][0];
				itens[linhaPedido][1] = qtd + " x " + cardapio[selecao][1];
				valorItem = qtd * (float)cardapio[selecao][2];
				itens[linhaPedido][2] = valorItem;
				DecimalFormat df = new DecimalFormat("R$ #,##0.00");
		        itens[linhaPedido][3] = df.format(itens[linhaPedido][2]);
				valorTotal = valorTotal + valorItem;
				
				for (int i = 0; i <= 3; i++) {
					itensPedido[linhaPedido][i] = itens[linhaPedido][i];
				}
				
				System.out.println("Selecione 1 para fazer outro pedido ou 0 para sair: ");
				try {
					roda = ler.nextInt();
				}catch(InputMismatchException e){
					System.out.println("\nDigite valores inteiros!");
					ler.nextLine();
					roda = 1;
				}
				linhaPedido ++;
								
			} while (roda!=0);
		
			pedidos.adicionarPedido(new Pedido(pedidos.gerarNumero(), cliente, itensPedido, valorTotal));
			
			valorTotal=0.0f;
			for (int i = 0; i < linhaPedido; i++) {
	            for (int j = 0; j < 4; j++) {
	                itens[i][j] = null;
	            }
	        }
			
			keyPress();
		}
		case 2 -> {
			System.out.println("Listar pedidos");
			pedidos.listarPedido();
			keyPress();
		}
		case 3 -> {
			System.out.println("Buscar pedido");
			System.out.println("Digite o número do pedido: ");
			try {
				busca = ler.nextInt();
			}catch(InputMismatchException e){
				System.out.println("\nDigite valores inteiros!");
				ler.nextLine();
				busca = 1;
			}
			pedidos.buscarPedido(busca);
			
			keyPress();
		}
		case 4 -> {
			System.out.println("Atualizar pedido");
			System.out.println("Digite o número do pedido: ");
			try {
				busca = ler.nextInt();
			}catch(InputMismatchException e){
				System.out.println("\nDigite valores inteiros!");
				ler.nextLine();
				busca = 1;
			}
			System.out.println("Digite o nome do cliente: ");
			ler.skip("\\R?");
			cliente = ler.nextLine();
			linhaPedido = 0;
			Object[][] itensPedido = new Object[99][4];
			
			
			do {
				
				System.out.println("Cardápio: ");
				
				itenscardapio = 0;
				for (int i = 0; i < cardapio.length; i ++) {
					if(cardapio[i][0] != null) {
						itenscardapio ++;
						cardapio[i][0] = itenscardapio;
					}
				}
				for (int i = 0; i < itenscardapio; i ++) {
					System.out.println(cardapio[i][0] + " - " + cardapio[i][1] + " - " + cardapio[i][3]);
				}
				
				System.out.println("Selecione o item a ser adicionado: ");
				
				do {
					try {
						selecao = ler.nextInt();
					}catch(InputMismatchException e){
						System.out.println("\nDigite valores inteiros!");
						ler.nextLine();
					}
				}while(selecao < 1 && selecao > itenscardapio);
				
				selecao --;
				
				System.out.println("\nDigite a quantidade: ");
				try {
					qtd = ler.nextInt();
				}catch(InputMismatchException e){
					System.out.println("\nDigite valores inteiros!");
					ler.nextLine();
					qtd = 1;
				}
				
				itens[linhaPedido][0] = cardapio[selecao][0];
				itens[linhaPedido][1] = qtd + " x " + cardapio[selecao][1];
				valorItem = qtd * (float)cardapio[selecao][2];
				itens[linhaPedido][2] = valorItem;
				DecimalFormat df = new DecimalFormat("R$ #,##0.00");
		        itens[linhaPedido][3] = df.format(itens[linhaPedido][2]);
				valorTotal = valorTotal + valorItem;
				
				for (int i = 0; i <= 3; i++) {
					itensPedido[linhaPedido][i] = itens[linhaPedido][i];
				}
				
				System.out.println("Selecione 1 para fazer outro pedido ou 0 para sair: ");
				try {
					roda = ler.nextInt();
				}catch(InputMismatchException e){
					System.out.println("\nDigite valores inteiros!");
					ler.nextLine();
					roda = 1;
				}
				linhaPedido ++;
								
			} while (roda!=0);
		
			pedidos.atualizarPedido(new Pedido(busca, cliente, itensPedido, valorTotal));
			
			valorTotal=0.0f;
			for (int i = 0; i < linhaPedido; i++) {
	            for (int j = 0; j < 4; j++) {
	                itens[i][j] = null;
	            }
	        }
						
			keyPress();
		}
		case 5 -> {
			System.out.println("Apagar pedido");
			System.out.println("Digite o número do pedido: ");
			try {
				busca = ler.nextInt();
			}catch(InputMismatchException e){
				System.out.println("\nDigite valores inteiros!");
				ler.nextLine();
				busca = 1;
			}
			pedidos.deletarPedido(busca);
			
			keyPress();
		}
		case 6 -> {
			System.out.println("Cardápio: ");
			
			itenscardapio = 0;
			for (int i = 0; i < cardapio.length; i ++) {
				if(cardapio[i][0] != null) {
					itenscardapio ++;
					cardapio[i][0] = itenscardapio;
				}
			}
			
			for (int i = 0; i < itenscardapio; i ++) {
				if (cardapio[i][0] == null) {
					i++;
				}
				System.out.println(cardapio[i][0] + " - " + cardapio[i][1] + " - " + cardapio[i][3]);
			}
			
			System.out.println("Deseja alguma alteração?");
			System.out.println("1 - Adicionar item ao cardápio");
			System.out.println("2 - Alterar item do cardápio");
			System.out.println("3 - Remover item do cardápio");
			System.out.println("0 - Voltar ao menu principal");
			System.out.println("Entre com a opção desejada:");		
			try {
				opcardapio = ler.nextInt();
			}catch(InputMismatchException e){
				System.out.println("\nDigite valores inteiros!");
				ler.nextLine();
				opcardapio = 0;
			}
			
			switch (opcardapio) {
			case 1:
				System.out.println("\nDigite nome do prato: ");
				ler.skip("\\R?");
				adicionaNomeCardapio = ler.nextLine();
				System.out.println("\nDigite preço do prato: ");
				adicionaValorCardapio = ler.nextFloat();
				
				cardapio[itenscardapio][0] = itenscardapio;
				cardapio[itenscardapio][1] = adicionaNomeCardapio;
				cardapio[itenscardapio][2] = adicionaValorCardapio;
				cardapio[itenscardapio][3] = decimalFormat.format(cardapio[itenscardapio][2]);
				
				
				System.out.println("Prato adicionado!");
			break;
			case 2: 
				System.out.println("\nDigite o item a ser alterado: ");
				alteraCardapio = ler.nextInt();
				System.out.println("\nDigite nome do prato: ");
				ler.skip("\\R?");
				adicionaNomeCardapio = ler.nextLine();
				System.out.println("\nDigite preço do prato: ");
				adicionaValorCardapio = ler.nextFloat();
				alteraCardapio --;
				cardapio[alteraCardapio][3] = null;
				cardapio[alteraCardapio][1] = adicionaNomeCardapio;
				cardapio[alteraCardapio][2] = adicionaValorCardapio;
				cardapio[alteraCardapio][3] = decimalFormat.format(cardapio[alteraCardapio][2]);
				
				
				System.out.println("Prato alterado!");
			break;
			case 3:
				System.out.println("\nDigite o item a ser removido: ");
				alteraCardapio = ler.nextInt();
				
			do {
				removeCardapio = alteraCardapio - 1;
				cardapio[removeCardapio][0] = cardapio[alteraCardapio][0];
				cardapio[removeCardapio][1] = cardapio[alteraCardapio][1];
				cardapio[removeCardapio][2] = cardapio[alteraCardapio][2];
				cardapio[removeCardapio][3] = cardapio[alteraCardapio][3];
				
				if (cardapio[alteraCardapio][0] == cardapio[removeCardapio][0]) {
					cardapio[alteraCardapio][0] = null;
					cardapio[alteraCardapio][1] = null;
					cardapio[alteraCardapio][2] = null;
					cardapio[alteraCardapio][3] = null;
				}
				
				alteraCardapio ++;
			}while (alteraCardapio < itenscardapio);
			
				System.out.println("Prato removido!");
			break;
			case 0: 
				System.out.println("Voltando ao menu inicial.");
			break;
			default:
				System.out.println("Opção Inválida!");
			break;
			}
			keyPress();
		}
		case 9 -> {
			System.out.println(Cores.TEXT_YELLOW + Cores.ANSI_BLACK_BACKGROUND +
					   "*********************************************");
			System.out.println("  _   _                                      ");
			System.out.println(" | | / / ___  ___  ___    __  ___  ___  ___  ");
			System.out.println(" | |/ / | __|| _ || _ |  |_ || _ || _ || _ | ");
			System.out.println(" |   /  ||__ ||_||||_||    ||||_||||_||||_|| ");
			System.out.println(" |   \\  | __||  _||   |    ||| _ || __|| _ | ");
			System.out.println(" | |\\ \\ ||__ ||\\\\ ||\\\\  ||_|||| ||||   || || ");
			System.out.println(" |_| \\_\\|___||| \\\\|| \\\\ |___||| ||||   || || ");
			System.out.println("                                             ");
			System.out.println("*********************************************" + Cores.TEXT_RESET);
	
			System.out.println("Obrigado por utilizar nosso serviço!");
			
		}
		default -> {
			System.out.println("Opção Inválida!");
			
			keyPress();
		}
		
		}
		

		} while (menu != 9);
	}
	
	public static void keyPress() {

		try {
			System.out.println(Cores.TEXT_RESET + "\n\nPressione Enter para Continuar...");
			System.in.read();

		} catch (IOException e) {

			System.out.println("Você pressionou uma tecla diferente de enter!");

		}
	}

}
