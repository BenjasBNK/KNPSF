package modelo;

import java.util.ArrayList;

public class CarrinhoCompras {

	private String codigoCarrinho;
	private int produtorCarrinho;
	private ArrayList<Double> precoItem;
	private ArrayList<Produto> produtos;
	private float valorTotal;
	private int quantidadeProdutos;
	private Cliente cliente;
	
	public CarrinhoCompras() {}
	
	public CarrinhoCompras(String codigoCarrinho, Cliente cliente, int quantidadeProdutos, ArrayList<Produto> produtos, ArrayList<Double> precoItem, float valorTotal) {
		this.codigoCarrinho = codigoCarrinho;
		this.cliente = cliente;
		this.quantidadeProdutos = quantidadeProdutos;
		this.produtos = produtos;
		this.precoItem = precoItem;
		this.valorTotal = valorTotal;
	}

	public String getCodigoCarrinho() {
		return codigoCarrinho;
	}

	public void setCodigoCarrinho(String codigoCarrinho) {
		this.codigoCarrinho = codigoCarrinho;
	}

	public ArrayList<Double> getPrecoItem() {
		return precoItem;
	}

	public void setPrecoItem(ArrayList<Double> precoItem) {
		this.precoItem = precoItem;
	}

	public ArrayList<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(ArrayList<Produto> produtos) {
		this.produtos = produtos;
	}

	public float getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(float valorTotal) {
		this.valorTotal = valorTotal;
	}

	public int getQuantidadeProdutos() {
		return quantidadeProdutos;
	}

	public void setQuantidadeProdutos(int quantidadeProdutos) {
		this.quantidadeProdutos = quantidadeProdutos;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public int getProdutorCarrinho() {
		return produtorCarrinho;
	}

	public void setProdutorCarrinho(int produtorCarrinho) {
		this.produtorCarrinho = produtorCarrinho;
	}
	
}
