package controle;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import modelo.IProdutoDAO;
import modelo.Produto;
import modelo.Usuario;

public class ProdutoDAO implements IProdutoDAO {

	private static ProdutoDAO instancia;
	public static ArrayList<Produto> listaProdutos;

	public ProdutoDAO() {
	}

	public static ProdutoDAO getInstancia() {

		if (instancia == null) {
			instancia = new ProdutoDAO();
			listaProdutos = new ArrayList<>();
		}

		return instancia;
	}

	public boolean inserirProduto(Produto produto, Usuario u) {
	    String sql = "INSERT INTO produtos (nome_Produto, quantidade, preco, Produtores_idProdutores, validade, salinidade, foto) VALUES (?, ?, ?, ?, ?, ?, ?)";
	    try (Connection conn = ConexaoBD.getConexaoMySQL(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

	        pstmt.setString(1, produto.getNome());
	        pstmt.setInt(2, produto.getQuantidadeEstoque());
	        pstmt.setDouble(3, produto.getPreco());
	        pstmt.setInt(4, u.getIdUsuario());
	        pstmt.setDate(5, java.sql.Date.valueOf(produto.getValidade()));
	        pstmt.setBoolean(6, produto.getSalinidade());
	        
	        // Verificando se a imagem não é nula
	        if (produto.getFoto() != null) {
	            // Convertendo a imagem (Image) para byte[]
	            ByteArrayOutputStream baos = new ByteArrayOutputStream();
	            BufferedImage bufferedImage = (BufferedImage) produto.getFoto();
	            ImageIO.write(bufferedImage, "jpg", baos); // Convertendo para formato JPG (pode alterar o tipo conforme necessário)
	            baos.flush();
	            byte[] imageBytes = baos.toByteArray();
	            pstmt.setBytes(7, imageBytes); // Definindo os bytes da imagem no parâmetro da consulta
	            baos.close();
	            System.out.println("Imagem adicionada ao banco.");
	        } else {
	            pstmt.setNull(7, java.sql.Types.BLOB); // Se a foto for nula, coloca null na coluna
	            System.out.println("Imagem não fornecida.");
	        }

	        int rowsAffected = pstmt.executeUpdate();
	        return rowsAffected > 0;

	    } catch (SQLException | IOException e) {
	        e.printStackTrace();
	        return false;
	    }
	}


	public boolean alterarProduto(Produto produto) {
		return false;
	}

	public boolean removerProduto(Produto p) {
		int id = Integer.parseInt(pegarIdProduto(p));

		String sql = "DELETE FROM produtos WHERE idProdutos = ?";
		try (Connection conn = ConexaoBD.getConexaoMySQL(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setInt(1, id);

			int rowsAffected = pstmt.executeUpdate();
			return rowsAffected > 0;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public List<Produto> ordenarPorPreco(boolean crescente, List<Produto> produtos) {
		Comparator<Produto> comparador = Comparator.comparingDouble(Produto::getPreco);
		if (!crescente) {
			comparador = comparador.reversed();
		}
		produtos.sort(comparador);
		return produtos;
	}

	public List<Produto> ordenarNome(boolean crescente, List<Produto> produtos) {
		Comparator<Produto> comparador = Comparator.comparing(Produto::getNome);
		if (!crescente) {
			comparador = comparador.reversed();
		}
		produtos.sort(comparador);
		return produtos;
	}

	public ArrayList<Produto> addListaProd(Usuario u) {
	    listaProdutos.clear();

	    PreparedStatement stmt1 = null;
	    Connection conn = ConexaoBD.getConexaoMySQL();

	    try {
	        stmt1 = conn.prepareStatement("SELECT * FROM kanepe.produtos WHERE Produtores_idProdutores = ?;");
	        stmt1.setString(1, pegarIdProdutor(u));
	        
	        ResultSet res1 = stmt1.executeQuery();

	        while (res1.next()) {
	            Produto prod = new Produto();

	            prod.setNome(res1.getString("nome_Produto"));
	            prod.setIdProduto(res1.getInt("idProdutos"));
	            prod.setQuantidadeEstoque(res1.getInt("quantidade"));
	            prod.setPreco(res1.getFloat("preco"));
	            prod.setIdProdutor(res1.getInt("Produtores_idProdutores"));
	            prod.setValidade(LocalDate.parse(res1.getString("validade"), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
	            prod.setSalinidade(res1.getBoolean("salinidade"));

	            // Pegando a imagem do banco (LONGBLOB)
	            byte[] imagemBytes = res1.getBytes("foto");

	            if (imagemBytes != null && imagemBytes.length > 0) {
	                // Convertendo byte[] para Image
	                ImageIcon imageIcon = new ImageIcon(imagemBytes);
	                Image imagem = imageIcon.getImage();
	                prod.setFoto(imagem); // Setando no produto
	            } else {
	                prod.setFoto(null); // Se não houver imagem, define como null
	            }

	            listaProdutos.add(prod);
	        }

	        res1.close();
	        stmt1.close();
	        conn.close();
	    } catch (Exception e) {
	        e.printStackTrace(); // Adicionando log para debugar erros
	    }

	    return listaProdutos;
	}



	public String pegarIdProdutor(Usuario u) {

		PreparedStatement stmt1 = null;

		Connection conn = ConexaoBD.getConexaoMySQL();

		try {
			stmt1 = conn.prepareStatement("SELECT * FROM kanepe.produtores where Usuarios_idUsuarios = ?;");
			ResultSet res1 = null;
			stmt1.setString(1, String.valueOf(u.getIdUsuario()));

			res1 = stmt1.executeQuery();

			while (res1.next()) {

				return res1.getString("idProdutores");
			}

			res1.close();
			stmt1.close();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public String pegarIdProduto(Produto p) {

		PreparedStatement stmt1 = null;

		Connection conn = ConexaoBD.getConexaoMySQL();

		try {
			stmt1 = conn.prepareStatement("SELECT * FROM produtos where nome_Produto = ?");
			ResultSet res1 = null;

			stmt1.setString(1, String.valueOf(p.getNome()));

			res1 = stmt1.executeQuery();

			while (res1.next()) {

				return res1.getString("idProdutos");
			}

			res1.close();
			stmt1.close();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean atualizarProduto(Produto oprod, Produto produto, Usuario u) {
		int id = Integer.parseInt(pegarIdProduto(oprod));

		String sql = "UPDATE produtos SET nome_Produto = ?, quantidade = ?, preco = ?, validade = ?, salinidade = ? WHERE idProdutos = ?";
		try (Connection conn = ConexaoBD.getConexaoMySQL(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setString(1, produto.getNome());
			pstmt.setInt(2, produto.getQuantidadeEstoque());
			pstmt.setDouble(3, produto.getPreco());
			pstmt.setDate(4, (java.sql.Date.valueOf(produto.getValidade())));
			pstmt.setBoolean(5, produto.getSalinidade());
			pstmt.setInt(6, id);

			int rowsAffected = pstmt.executeUpdate();
			return rowsAffected > 0;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public ArrayList<Produto> addTodosProd() {
	    ArrayList<Produto> listaDeProdutos = new ArrayList<Produto>();

	    PreparedStatement stmt1 = null;
	    Connection conn = ConexaoBD.getConexaoMySQL();

	    try {
	        System.out.println("Conectando ao banco de dados...");
	        stmt1 = conn.prepareStatement("SELECT * FROM kanepe.produtos;");
	        ResultSet res1 = stmt1.executeQuery();
	        System.out.println("Consulta realizada com sucesso, processando os produtos...");

	        while (res1.next()) {
	            Produto prod = new Produto();

	            prod.setNome(res1.getString("nome_Produto"));
	            prod.setIdProduto(res1.getInt("idProdutos"));
	            prod.setQuantidadeEstoque(Integer.parseInt(res1.getString("quantidade")));
	            prod.setPreco(Float.parseFloat(res1.getString("preco")));
	            prod.setIdProdutor(Integer.parseInt(res1.getString("Produtores_idProdutores")));
	            prod.setValidade(LocalDate.parse(res1.getString("validade"), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
	            prod.setSalinidade(res1.getBoolean("salinidade"));

	            // Recuperando a imagem do banco (LONGBLOB)
	            byte[] imagemBytes = res1.getBytes("foto");

	            if (imagemBytes != null && imagemBytes.length > 0) {
	                // Convertendo byte[] para Image
	                ImageIcon imageIcon = new ImageIcon(imagemBytes);
	                Image imagem = imageIcon.getImage();
	                prod.setFoto(imagem); // Definindo no produto
	                System.out.println("Imagem carregada com sucesso para o produto: " + prod.getNome());
	            } else {
	                prod.setFoto(null); // Se não houver imagem, define como null
	                System.out.println("Nenhuma imagem encontrada para o produto: " + prod.getNome());
	            }

	            listaDeProdutos.add(prod);
	        }

	        System.out.println("Processamento concluído, " + listaDeProdutos.size() + " produtos encontrados.");
	        res1.close();
	        stmt1.close();
	        conn.close();
	    } catch (Exception e) {
	        System.out.println("Erro ao recuperar dados dos produtos: " + e.getMessage());
	    }

	    return listaDeProdutos;
	}



	public Produto pegarProdutoPorId(int idProduto) {
	    PreparedStatement stmt1 = null;
	    ResultSet res1 = null;
	    Connection conn = ConexaoBD.getConexaoMySQL();

	    try {
	        stmt1 = conn.prepareStatement("SELECT * FROM kanepe.produtos WHERE idProdutos = ?;");
	        stmt1.setInt(1, idProduto); // Corrigido para setInt
	        res1 = stmt1.executeQuery();

	        if (res1.next()) { // Adicionado para evitar erro
	            Produto prod = new Produto();

	            // Definindo os outros atributos
	            prod.setNome(res1.getString("nome_Produto"));
	            prod.setQuantidadeEstoque(res1.getInt("quantidade"));
	            prod.setPreco(res1.getFloat("preco"));
	            prod.setIdProdutor(res1.getInt("Produtores_idProdutores"));
	            prod.setValidade(
	                LocalDate.parse(res1.getString("validade"), DateTimeFormatter.ofPattern("yyyy-MM-dd"))
	            );
	            prod.setSalinidade(res1.getBoolean("salinidade"));
	            prod.setIdProduto(res1.getInt("idProdutos"));

	            // Recuperando a imagem (BLOB)
	            byte[] imageBytes = res1.getBytes("foto"); // Coluna 'foto' do banco de dados (LONG BLOB)

	            if (imageBytes != null && imageBytes.length > 0) {
	                // Convertendo o array de bytes para Image
	                ImageIcon icon = new ImageIcon(imageBytes);
	                Image image = icon.getImage();

	                // Definindo a foto do produto
	                prod.setFoto(image);  // Definindo a imagem no objeto Produto
	            } else {
	                System.out.println("Nenhuma imagem encontrada para o produto: " + idProduto);
	            }

	            return prod;
	        } else {
	            return null; // Retorna null se o ID não for encontrado
	        }
	    } catch (Exception e) {
	        System.out.println("Erro ao buscar produto: " + e.getMessage());
	        return null;
	    } finally {
	        try {
	            if (res1 != null) res1.close();
	            if (stmt1 != null) stmt1.close();
	            if (conn != null) conn.close();
	        } catch (Exception e) {
	            System.out.println("Erro ao fechar recursos: " + e.getMessage());
	        }
	    }
	}

	
	public ArrayList<Produto> addListaProdFiltro(Usuario u, String filtro) {
	    listaProdutos.clear();

	    PreparedStatement stmt1 = null;
	    Connection conn = ConexaoBD.getConexaoMySQL();

	    try {
	        stmt1 = conn.prepareStatement("SELECT * FROM produtos WHERE Produtores_idProdutores = ? AND nome_Produto LIKE ?");
	        stmt1.setString(1, pegarIdProdutor(u));
	        stmt1.setString(2, filtro + "%"); // Adicionando '%' ao filtro para busca por prefixo
	        
	        ResultSet res1 = stmt1.executeQuery();

	        while (res1.next()) {
	            Produto prod = new Produto();

	            prod.setNome(res1.getString("nome_Produto"));
	            prod.setIdProduto(res1.getInt("idProdutos"));
	            prod.setQuantidadeEstoque(res1.getInt("quantidade"));
	            prod.setPreco(res1.getFloat("preco"));
	            prod.setIdProdutor(res1.getInt("Produtores_idProdutores"));
	            prod.setValidade(LocalDate.parse(res1.getString("validade"), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
	            prod.setSalinidade(res1.getBoolean("salinidade"));

	            // Pegando a imagem do banco (LONGBLOB)
	            byte[] imagemBytes = res1.getBytes("foto");

	            if (imagemBytes != null && imagemBytes.length > 0) {
	                // Convertendo byte[] para Image
	                ImageIcon imageIcon = new ImageIcon(imagemBytes);
	                Image imagem = imageIcon.getImage();
	                prod.setFoto(imagem); // Setando no produto
	            } else {
	                prod.setFoto(null); // Se não houver imagem, define como null
	            }

	            listaProdutos.add(prod);
	        }

	        res1.close();
	        stmt1.close();
	        conn.close();
	    } catch (Exception e) {
	        e.printStackTrace(); // Exibir erro no console para depuração
	    }

	    return listaProdutos;
	}


	public byte[] getImagemProduto(int produtoId) {
	    byte[] imagemBytes = null;
	    String sql = "SELECT foto FROM produtos WHERE idProdutos = ?";
	    
	    try (Connection conn = ConexaoBD.getConexaoMySQL(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        
	    	pstmt.setInt(1, produtoId);
	        
	        try (ResultSet rs = pstmt.executeQuery()) {
	            if (rs.next()) {
	                imagemBytes = rs.getBytes("foto");
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return imagemBytes;
	}


}