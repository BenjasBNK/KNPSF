package modelo;

import java.util.ArrayList;
import java.util.List;

public interface IProdutoDAO {

    // Método para inserir um novo produto no banco
    public boolean inserirProduto(Produto produto, Usuario u);

    // Método para alterar um produto existente no banco (ainda não implementado na classe ProdutoDAO)
    public boolean alterarProduto(Produto produto);

    // Método para remover um produto do banco
    public boolean removerProduto(Produto p);

    // Método para ordenar uma lista de produtos por preço (crescente ou decrescente)
    public List<Produto> ordenarPorPreco(boolean crescente, List<Produto> produtos);

    // Método para ordenar uma lista de produtos por nome (crescente ou decrescente)
    public List<Produto> ordenarNome(boolean crescente, List<Produto> produtos);

    // Método para adicionar uma lista de produtos com base no produtor
    public ArrayList<Produto> addListaProd(Usuario u);

    // Método para obter o id do produtor associado ao usuário
    public String pegarIdProdutor(Usuario u);

    // Método para pegar o id do produto dado um produto específico
    public String pegarIdProduto(Produto p);

    // Método para atualizar as informações de um produto
    public boolean atualizarProduto(Produto oprod, Produto produto, Usuario u);

    // Método para adicionar todos os produtos da base de dados
    public ArrayList<Produto> addTodosProd();

    // Método para pegar um produto específico com base no id
    public Produto pegarProdutoPorId(int idProduto);

    // Método para adicionar uma lista de produtos com um filtro de nome
    public ArrayList<Produto> addListaProdFiltro(Usuario u, String filtro);

    // Método para obter a imagem de um produto baseado no id do produto
    public byte[] getImagemProduto(int produtoId);
}
