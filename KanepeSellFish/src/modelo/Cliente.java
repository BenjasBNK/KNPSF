package modelo;

public class Cliente extends Usuario{

	private String idCliente;
	private CarrinhoCompras carrinho;
	
	public Cliente(){}
	
	public Cliente(String idCliente, CarrinhoCompras carrinho) {
		this.idCliente = idCliente;
		this.carrinho = carrinho;	
	}
	
	public String getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(String idCliente) {
		this.idCliente = idCliente;
	}
	
	public CarrinhoCompras getCarrinho() {
		return carrinho;
	}
	public void setCarrinho(CarrinhoCompras carrinho) {
		this.carrinho = carrinho;
	}

}
