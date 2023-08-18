package KerrJapa.Repository;

import KerrJapa.model.Pedido;

public interface KerrJapaRepository {
	
	//CRUD
	public void adicionarPedido(Pedido pedido);
	public void listarPedido();
	public void buscarPedido(int numero);
	public void atualizarPedido(Pedido pedido);
	public void deletarPedido(int numero);
		

}
