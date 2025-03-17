package modelo;

public class ItemCarrinho extends CarrinhoCompras{
	private int idItemCarrinho;
	private Integer quantidade;
	private Float precoTotal;
	private Produto produto;
	
	public ItemCarrinho(){}
	
	public ItemCarrinho(int produtoItemCarrinho, Integer quantidade,  Float precoTotal ) {
		this.idItemCarrinho = produtoItemCarrinho;
		this.quantidade = quantidade;
		this.precoTotal = precoTotal;
	}

	public int getidItemCarrinho() {
		return idItemCarrinho;
	}

	public void setidItemCarrinho(int produtoItemCarrinho) {
		this.idItemCarrinho = produtoItemCarrinho;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Float getPrecoTotal() {
		return precoTotal;
	}

	public void setPrecoTotal(Float precoTotal) {
		this.precoTotal = precoTotal;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}


}
