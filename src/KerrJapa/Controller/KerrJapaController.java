package KerrJapa.Controller;

import java.util.ArrayList;
import KerrJapa.Repository.KerrJapaRepository;
import KerrJapa.model.Pedido;

public class KerrJapaController implements KerrJapaRepository{

	private ArrayList<Pedido> listaPedido = new ArrayList<Pedido>()
;
	int numero = 0;
	
	@Override
	public void adicionarPedido(Pedido pedido) {
		listaPedido.add(pedido);
		System.out.println("\nO pedido número " + pedido.getNumero() + " foi adicionado!");
	}

	@Override
	public void listarPedido() {
		for (var pedido: listaPedido) {
			pedido.visualizar();
		}
	}

	@Override
	public void buscarPedido(int numero) {
		var pedido = buscarNaCollection(numero);
		
		if (pedido != null) {
			pedido.visualizar();
		} else {
			System.out.println("O pedido número " + numero + " não foi encontrado!");
		}
	}

	@Override
	public void atualizarPedido(Pedido pedido) {
		var buscaPedido = buscarNaCollection(pedido.getNumero());
		
		if (buscaPedido != null) {
			listaPedido.set(listaPedido.indexOf(buscaPedido), pedido);
			System.out.println("O pedido número " + pedido.getNumero() + " foi atualizado!");
		} else {
			System.out.println("O pedido número " + pedido.getNumero() + " não foi encontrado!");
		}
		
	}

	@Override
	public void deletarPedido(int numero) {
		var pedido = buscarNaCollection(numero);
		
		if (pedido != null) {
			if (listaPedido.remove(pedido) == true) {
			System.out.println("O pedido número " + numero + " foi deletado!");
			} else {
			System.out.println("O pedido número " + numero + " não foi encontrado!");
			}
		}
		
	}
	
	public int gerarNumero() {
		return ++ numero;
	}
	
	public Pedido buscarNaCollection (int numero) {
		for (var pedido : listaPedido) {
			if (pedido.getNumero() == numero) {
				return pedido;
			}
		}
		return null;
	}

	

}
