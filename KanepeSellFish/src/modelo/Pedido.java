package modelo;

import java.time.LocalDate;
import java.util.ArrayList;

public class Pedido {

	private String id;
	private Produtor produtoPedido;
	private Cliente cliente;
	private LocalDate data;
	private Float valorTotal;
	private CarrinhoCompras carrinhoPedido;
	private ArrayList<Pedido> produtoPedidos;
	
	public Pedido() {}
	
	public Pedido(String id, Produtor produtoPedido, Cliente cliente,  LocalDate data, Float valorTotal, CarrinhoCompras carrinhoPedido, ArrayList<Pedido> produtoPedidos) {
		this.id = id;
		this.produtoPedido = produtoPedido;
		this.cliente = cliente;
		this.data = data;
		this.valorTotal = valorTotal;
		this.carrinhoPedido = carrinhoPedido;
		this.produtoPedidos = produtoPedidos;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Produtor getprodutoPedido() {
		return produtoPedido;
	}

	public void setprodutoPedido(Produtor produtoPedido) {
		this.produtoPedido = produtoPedido;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public Float getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(Float valorTotal) {
		this.valorTotal = valorTotal;
	}

	public CarrinhoCompras getcarrinhoPedido() {
		return carrinhoPedido;
	}

	public void setcarrinhoPedido(CarrinhoCompras carrinhoPedido) {
		this.carrinhoPedido = carrinhoPedido;
	}

	public ArrayList<Pedido> getprodutoPedidos() {
		return produtoPedidos;
	}

	public void setprodutoPedidos(ArrayList<Pedido> produtoPedidos) {
		this.produtoPedidos = produtoPedidos;
	}
}