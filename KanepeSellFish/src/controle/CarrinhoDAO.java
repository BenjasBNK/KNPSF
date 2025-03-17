package controle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import modelo.CarrinhoCompras;
import modelo.ICarrinhoDAO;
import modelo.ItemCarrinho;
import modelo.Pedido;
import modelo.Produto;
import modelo.Usuario;

public class CarrinhoDAO implements ICarrinhoDAO {

	private static CarrinhoDAO instancia;
	private static ArrayList<Produto> listaProdutos;
	ProdutoDAO pDAO = new ProdutoDAO();

	public CarrinhoDAO() {
	}

	public static CarrinhoDAO getInstancia() {

		if (instancia == null) {
			instancia = new CarrinhoDAO();
			listaProdutos = new ArrayList<Produto>();
		}

		return instancia;
	}

	public boolean inserirProduto(Produto produto, int quantidade, Float preco, CarrinhoCompras c) {
		String sql = "INSERT INTO ItensCarrinho (Carrinho_idCarrinho, Produtos_idProdutos, quantidade, preco) VALUES (?, ?, ?, ?)";
		try (Connection conn = ConexaoBD.getConexaoMySQL(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setString(1, c.getCodigoCarrinho());
			pstmt.setInt(2, Integer.parseInt(pDAO.pegarIdProduto(produto)));
			pstmt.setInt(3, quantidade);

			// Converter o preco para double e arredondá-lo para 2 casas decimais
			pstmt.setDouble(4, preco);

			setProdutorCarrinho(produto.getIdProdutor(), c);

			int rowsAffected = pstmt.executeUpdate();
			return rowsAffected > 0;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public CarrinhoCompras verificarSeExisteCarrinho(Usuario u, Integer carrinhoProdutor, Boolean criar) {
		String sql;
		boolean filtrarPorProdutor = (carrinhoProdutor != null && carrinhoProdutor > 0);

		if (filtrarPorProdutor) {
			sql = "SELECT * FROM kanepe.carrinho WHERE Usuarios_idUsuarios = ? AND Produtores_idProdutores = ?";
		} else {
			sql = "SELECT * FROM kanepe.carrinho WHERE Usuarios_idUsuarios = ?";
		}

		try (Connection conn = ConexaoBD.getConexaoMySQL(); PreparedStatement stmt1 = conn.prepareStatement(sql)) {

			stmt1.setInt(1, u.getIdUsuario());

			if (filtrarPorProdutor) {
				stmt1.setInt(2, carrinhoProdutor);
			}

			try (ResultSet res1 = stmt1.executeQuery()) {
				if (res1.next()) { // Se há um carrinho existente
					CarrinhoCompras c = new CarrinhoCompras();
					c.setProdutorCarrinho(res1.getInt("Produtores_idProdutores"));
					c.setCodigoCarrinho(res1.getString("idCarrinho"));
					return c;
				} else if (criar) { // Se não há carrinho e criar == true, cria um novo carrinho
					return criarCarrinho(u, (carrinhoProdutor != null ? carrinhoProdutor : 0));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null; // Retorna null se não houver carrinho e criar == false
	}

	public CarrinhoCompras criarCarrinho(Usuario u, int carrinhoProdutor) {
		PreparedStatement stmt1 = null;
		Connection conn = ConexaoBD.getConexaoMySQL();

		try {
			// Usando o INSERT para criar um novo carrinho
			stmt1 = conn.prepareStatement(
					"INSERT INTO carrinho (Usuarios_idUsuarios, Produtores_idProdutores) VALUES (?, ?)",
					PreparedStatement.RETURN_GENERATED_KEYS // Habilita a recuperação do ID gerado
			);

			// Definindo o ID do usuário
			stmt1.setInt(1, u.getIdUsuario());
			stmt1.setInt(2, carrinhoProdutor);

			// Executando o INSERT, mas agora usando executeUpdate()
			int affectedRows = stmt1.executeUpdate(); // Retorna o número de linhas afetadas

			if (affectedRows > 0) {
				// Recuperando a chave gerada (ID do novo carrinho)
				ResultSet generatedKeys = stmt1.getGeneratedKeys();
				if (generatedKeys.next()) {
					String idCarrinho = generatedKeys.getString(1); // ID gerado pelo banco
					CarrinhoCompras c = new CarrinhoCompras();
					c.setCodigoCarrinho(idCarrinho); // Definindo o ID do carrinho
					return c;
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			// Fechar os recursos
			try {
				if (stmt1 != null)
					stmt1.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return null; // Se não for possível criar o carrinho, retorna null
	}

	@Override
	public boolean removerProduto(ItemCarrinho item) {

		String sql = "DELETE FROM itenscarrinho WHERE idItensCarrinho = ?";

		try (Connection conn = ConexaoBD.getConexaoMySQL(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setInt(1, item.getidItemCarrinho());
//			pstmt.setInt(2, item.getProduto().getIdProduto());

			int rowsAffected = pstmt.executeUpdate();
			if (rowsAffected > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean alterarQuantidade(Pedido pedido) {
		// TODO Auto-generated method stub
		return false;
	}

	public ArrayList<ItemCarrinho> addProdCarrinho(CarrinhoCompras c) {
		ArrayList<ItemCarrinho> listaDeProdutos = new ArrayList<ItemCarrinho>();

		PreparedStatement stmt1 = null;

		Connection conn = ConexaoBD.getConexaoMySQL();

		try {
			stmt1 = conn.prepareStatement("SELECT * FROM kanepe.itenscarrinho where Carrinho_idCarrinho = ?;");
			ResultSet res1 = null;

			stmt1.setString(1, c.getCodigoCarrinho());

			res1 = stmt1.executeQuery();

//			listaProdutos = null;

			while (res1.next()) {

//				pDAO.pegarIdProduto(null);

				ItemCarrinho Iprod = new ItemCarrinho();

				Iprod.setidItemCarrinho(Integer.parseInt(res1.getString("idItensCarrinho")));
				Iprod.setPrecoTotal(Float.parseFloat(res1.getString("preco")));
				Iprod.setQuantidade(Integer.parseInt(res1.getString("quantidade")));
				Iprod.setProduto(pDAO.pegarProdutoPorId(res1.getInt("Produtos_idProdutos")));
				Iprod.setCodigoCarrinho(res1.getString("Carrinho_idCarrinho"));
				listaDeProdutos.add(Iprod);
			}

			res1.close();
			stmt1.close();
			conn.close();
			return listaDeProdutos;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}

	}

	public boolean verificarProdutoNoCarrinho(CarrinhoCompras carrinho, Produto produto) {
		String sql = "SELECT * FROM itenscarrinho WHERE Produtos_idProdutos = ? AND Carrinho_idCarrinho = ?";

		try (Connection conn = ConexaoBD.getConexaoMySQL(); PreparedStatement stmt1 = conn.prepareStatement(sql)) {

			stmt1.setInt(1, produto.getIdProduto());
			stmt1.setString(2, carrinho.getCodigoCarrinho());

			try (ResultSet res1 = stmt1.executeQuery()) {
				return res1.next(); // Retorna true se encontrou o produto no carrinho
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false; // Retorna false se o produto não estiver no carrinho ou se ocorrer erro
	}

	public void atualizarQuantidade(CarrinhoCompras carrinho, Produto produto, int quantidade) {
		String sql = "UPDATE itenscarrinho SET quantidade = ? WHERE Produtos_idProdutos = ? AND Carrinho_idCarrinho = ?";

		try (Connection conn = ConexaoBD.getConexaoMySQL(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, quantidade);
			stmt.setInt(2, produto.getIdProduto());
			stmt.setString(3, carrinho.getCodigoCarrinho());

			int rowsUpdated = stmt.executeUpdate();
			if (rowsUpdated > 0) {
				System.out.println("Quantidade atualizada com sucesso.");
			} else {
				System.out.println("Produto não encontrado no carrinho.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void setProdutorCarrinho(int idProdutor, CarrinhoCompras carrinho) {

		String sql = "UPDATE Carrinho SET Produtores_idProdutores = ? WHERE idCarrinho = ?";

		try (Connection conn = ConexaoBD.getConexaoMySQL(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, idProdutor);
			stmt.setString(2, carrinho.getCodigoCarrinho());

			int rowsUpdated = stmt.executeUpdate();
			if (rowsUpdated > 0) {
				System.out.println("idProdutor atualizada com sucesso.");
			} else {
				System.out.println("Erro ao inserir idProdutor");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	public void deletarCarrinhosVazios(Usuario u, int idProdutor) {
	    String sql = "DELETE FROM carrinho WHERE Usuarios_idUsuarios = ? AND Produtores_idProdutores != ? AND NOT EXISTS (SELECT 1 FROM itenscarrinho WHERE Carrinho_idCarrinho = carrinho.idCarrinho)";
	    
	    try (Connection conn = ConexaoBD.getConexaoMySQL(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        pstmt.setInt(1, u.getIdUsuario());
	        pstmt.setInt(2, idProdutor);
	        
	        int rowsAffected = pstmt.executeUpdate();
	        if (rowsAffected > 0) {
	            System.out.println("Carrinho vazio de outro produtor excluído.");
	        } else {
	            System.out.println("Nenhum carrinho vazio encontrado para exclusão.");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	public CarrinhoCompras calcularCarrinho(ArrayList<ItemCarrinho> lista, CarrinhoCompras carrinho) {
	    // Recupera todos os itens do carrinho

	    // Variáveis para armazenar o valor total e a quantidade de itens
	    float valorTotal = 0;
	    int quantidadeTotal = 0;

	    // Soma o valor total e a quantidade dos itens
	    for (ItemCarrinho item : lista) {
	        valorTotal += item.getPrecoTotal();  // Soma o preço total de cada item
	        quantidadeTotal += item.getQuantidade();  // Soma a quantidade de cada item
	    }

	    // Atualiza os valores no objeto CarrinhoCompras
	    carrinho.setValorTotal(valorTotal);
	    carrinho.setQuantidadeProdutos(quantidadeTotal);

	    return carrinho;  // Retorna o carrinho atualizado
	}

	
	


}