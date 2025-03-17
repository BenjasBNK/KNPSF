package modelo;

import java.time.LocalDate;

public class ItemPedido {
	private Produto produtoItemPedido;
	private Integer quantidadeItemProduto;
	private Float precoUnitario;
	private Float subTotal;
	private LocalDate dataItemPedido;
	private Float valorTotal;
	private Pedido pedido;
	
	public ItemPedido() {}
	
	public ItemPedido(Produto produtoItemPedido,  Integer quantidadeItemProduto, Float precoUnitario, Float subTotal, LocalDate dataItemPedido, Float valorTotal, Pedido pedido) {
		this.produtoItemPedido = produtoItemPedido;
		this.quantidadeItemProduto = quantidadeItemProduto;
		this.precoUnitario = precoUnitario;
		this.subTotal = subTotal;
		this.dataItemPedido = dataItemPedido;
		this.valorTotal = valorTotal;
		this.pedido = pedido;
		
	}

	public Produto getprodutoItemPedido() {
		return produtoItemPedido;
	}

	public void setprodutoItemPedido(Produto produtoItemPedido) {
		this.produtoItemPedido = produtoItemPedido;
	}

	public Integer getquantidadeItemProduto() {
		return quantidadeItemProduto;
	}

	public void setquantidadeItemProduto(Integer quantidadeItemProduto) {
		this.quantidadeItemProduto = quantidadeItemProduto;
	}

	public Float getPrecoUnitario() {
		return precoUnitario;
	}

	public void setPrecoUnitario(Float precoUnitario) {
		this.precoUnitario = precoUnitario;
	}

	public Float getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(Float subTotal) {
		this.subTotal = subTotal;
	}

	public LocalDate getdataItemPedido() {
		return dataItemPedido;
	}

	public void setdataItemPedido(LocalDate dataItemPedido) {
		this.dataItemPedido = dataItemPedido;
	}

	public Float getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(Float valorTotal) {
		this.valorTotal = valorTotal;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
	
	
}
