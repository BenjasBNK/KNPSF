package modelo;

import java.util.ArrayList;

public class Estoque {

	private String idEstoque;
	private Integer quantidadeEstoque;
	private DisponibilidadeItens disponibilidadeProduto; 
	private ArrayList<Pedido> produtoEstoque;
	
	public Estoque() {}
	
	public Estoque( String idEstoque, Integer quantidadeEstoque, DisponibilidadeItens disponibilidadeProduto,  ArrayList<Pedido> produtoEstoque) {
		this.idEstoque = idEstoque;
		this.quantidadeEstoque = quantidadeEstoque;
		this.disponibilidadeProduto = disponibilidadeProduto;
		this.produtoEstoque = produtoEstoque;
	}

	public String getIdEstoque() {
		return idEstoque;
	}

	public void setIdEstoque(String idEstoque) {
		this.idEstoque = idEstoque;
	}

	public Integer getQuantidadeEstoque() {
		return quantidadeEstoque;
	}

	public void setQuantidadeEstoque(Integer quantidadeEstoque) {
		this.quantidadeEstoque = quantidadeEstoque;
	}

	public DisponibilidadeItens getDisponibilidadeProduto() {
		return disponibilidadeProduto;
	}

	public void setDisponibilidadeProduto(DisponibilidadeItens disponibilidadeProduto) {
		this.disponibilidadeProduto = disponibilidadeProduto;
	}

	public ArrayList<Pedido> getProdutoEstoque() {
		return produtoEstoque;
	}

	public void setProdutoEstoque(ArrayList<Pedido> produtoEstoque) {
		this.produtoEstoque = produtoEstoque;
	}

}
