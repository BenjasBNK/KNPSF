package modelo;

public interface IPedidoDAO {
	
	public boolean inserirPedido(Pedido pedido);
	public boolean alterarPedido(Pedido pedido);
	public boolean removerPedido(long id);

}
