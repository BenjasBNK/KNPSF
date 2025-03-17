package modelo;

import java.time.LocalDate;

public class ItemEstoque {

	private String idItemEstoque;
	private Integer quantidadeItemEstoque;
	private Estoque estoqueItemEstoque; 
	private Produto produtoItemEstoque;
	private LocalDate validade; 
	private LocalDate dataEntrada;
	
	public ItemEstoque() {}
	
	public ItemEstoque(String idEstoque, Integer quantidadeEstoque, Estoque estoqueItemEstoque,  Produto produtoItemEstoque, LocalDate validade, LocalDate dataEntrada ) {
		this.idItemEstoque = idEstoque;
		this.quantidadeItemEstoque = quantidadeEstoque;
		this.estoqueItemEstoque = estoqueItemEstoque;
		this.produtoItemEstoque = produtoItemEstoque;
		this.validade = validade;
		this.dataEntrada = dataEntrada;
	}

	public String getIdEstoque() {
		return idItemEstoque;
	}

	public void setIdEstoque(String idEstoque) {
		this.idItemEstoque = idEstoque;
	}

	public Integer getQuantidadeEstoque() {
		return quantidadeItemEstoque;
	}

	public void setQuantidadeEstoque(Integer quantidadeEstoque) {
		this.quantidadeItemEstoque = quantidadeEstoque;
	}

	public Estoque getEstoqueItemEstoque() {
		return estoqueItemEstoque;
	}

	public void setEstoqueItemEstoque(Estoque estoqueItemEstoque) {
		this.estoqueItemEstoque = estoqueItemEstoque;
	}

	public Produto getProdutoItemEstoque() {
		return produtoItemEstoque;
	}

	public void setProdutoItemEstoque(Produto produtoItemEstoque) {
		this.produtoItemEstoque = produtoItemEstoque;
	}

	public LocalDate getValidade() {
		return validade;
	}

	public void setValidade(LocalDate validade) {
		this.validade = validade;
	}

	public LocalDate getDataEntrada() {
		return dataEntrada;
	}

	public void setDataEntrada(LocalDate dataEntrada) {
		this.dataEntrada = dataEntrada;
	}
	
}
