package modelo;

import java.util.ArrayList;

public interface ICarrinhoDAO {

	boolean alterarQuantidade(Pedido pedido);
	public boolean inserirProduto(Produto produto, int quantidade, Float preco, CarrinhoCompras c);
	public CarrinhoCompras criarCarrinho(Usuario u, int carrinhoProdutor);
	public ArrayList<ItemCarrinho> addProdCarrinho(CarrinhoCompras c);
	public boolean removerProduto(ItemCarrinho item);
	public CarrinhoCompras verificarSeExisteCarrinho(Usuario u, Integer carrinhoProdutor, Boolean criar);
	public void setProdutorCarrinho(int idProdutor, CarrinhoCompras carrinho);
	public void deletarCarrinhosVazios(Usuario u, int idProdutor);
	public CarrinhoCompras calcularCarrinho(ArrayList<ItemCarrinho> lista, CarrinhoCompras carrinho);


}
