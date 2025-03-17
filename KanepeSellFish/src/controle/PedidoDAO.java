package controle;

import java.util.ArrayList;
import modelo.IPedidoDAO;
import modelo.Pedido;

public class PedidoDAO implements IPedidoDAO {

	private static PedidoDAO instancia;
	private static ArrayList<Pedido> listaPedidos;

	public PedidoDAO() {}

	public PedidoDAO instancia() {

		if (instancia == null) {
			instancia = new PedidoDAO();
			listaPedidos = new ArrayList<Pedido>();
		}

		return instancia;
	}

	public boolean inserirPedido(Pedido pedido) {

		listaPedidos.add(pedido);

		return false;
	}

	public boolean alterarPedido(Pedido pedido) {

		return false;
	}

	public boolean removerPedido(long id) {

		for (Pedido pedido : listaPedidos) {
			if(pedido.getId().equals(id));
			listaPedidos.remove(pedido);
		}
		return false;
	}
}
