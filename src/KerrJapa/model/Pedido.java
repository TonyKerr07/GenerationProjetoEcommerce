package KerrJapa.model;

import java.text.NumberFormat;

public class Pedido {
	
	private int numero;
	private String cliente;
	private Object[][] itens;
	private float valorFinal;
	public Pedido(int numero, String cliente, Object[][] itens, float valorFinal) {
		super();
		this.numero = numero;
		this.cliente = cliente;
		this.itens = itens;
		this.valorFinal = valorFinal;
	}
	
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public String getCliente() {
		return cliente;
	}
	public void setCliente(String cliente) {
		this.cliente = cliente;
	}
	public Object[][] getItens() {
		return itens;
	}
	public void setItens(Object[][] itens) {
		this.itens = itens;
	}
	public float getValorFinal() {
		return valorFinal;
	}
	public void setValorFinal(float valorFinal) {
		this.valorFinal = valorFinal;
	}
	
	public String formatarMoeda() {
		NumberFormat nf = NumberFormat.getCurrencyInstance(); 	
		nf.setMinimumFractionDigits(2); 						
		String formatoMoeda = nf.format(this.valorFinal);		
		return formatoMoeda;
	}
	
	public void visualizar() {
		int conta = 0;
		for (int i =0; i < this.itens.length; i++) {
			if (this.itens[i][0] != null) {
				conta ++;
			}
		}
		System.out.println("\nNumero do pedido: " + this.numero);
		System.out.println("Cliente: " + this.cliente);
		System.out.println("Itens:");
		for (int i = 0; i < conta; i ++) {
			System.out.println(this.itens[i][0] + " - " + this.itens[i][1] + " - " + this.itens[i][3]);
		}
		System.out.println("Valor final: " + formatarMoeda());
		
	}
	
	
	

}
