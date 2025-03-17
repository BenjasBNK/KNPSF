package controle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import modelo.Endereco;
import modelo.IProdutorDAO;
import modelo.Produtor;
import modelo.Usuario;

public class ProdutorDAO implements IProdutorDAO {

	private static ProdutorDAO instancia;
	EnderecoDAO eDAO = EnderecoDAO.getInstancia();
	UsuarioDAO uDAO = UsuarioDAO.getInstancia();

	public ProdutorDAO() {
	}

	public static ProdutorDAO getInstancia() {
		if (instancia == null) {
			instancia = new ProdutorDAO();
		}
		return instancia;
	}

	@Override
	public boolean inserirProdutor(Produtor produtor) {
		String sql = "INSERT INTO produtores (nomeNegocio, Usuarios_idUsuarios, Enderecos_idEnderecos, cnpj, chavePix) VALUES (?, ?, ?, ?, ?)";
		try (Connection conn = ConexaoBD.getConexaoMySQL(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setString(1, produtor.getNomeComercio());
			pstmt.setInt(2, produtor.getIdUsuario());
			pstmt.setInt(3, produtor.getEndereco());
			pstmt.setString(4, produtor.getCnpj());
			if (produtor.getChavePix() == null) {
				pstmt.setString(5, null);
			} else {
				pstmt.setString(5, produtor.getChavePix());
			}
			System.out.println(pstmt);
			int rowsAffected = pstmt.executeUpdate();
			return rowsAffected > 0;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public Produtor consultaProdutor(Usuario usuario) {
		String sql = "SELECT * FROM produtores WHERE Usuarios_idUsuarios = ?";
		try (Connection conn = ConexaoBD.getConexaoMySQL(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setLong(1, usuario.getIdUsuario());
			try (ResultSet res = stmt.executeQuery()) {
				if (res.next()) {
					Produtor p = new Produtor();
					Endereco e = eDAO.buscarendereco(res.getInt("Enderecos_idEnderecos"));

					p.setNomeComercio(res.getString("nomeNegocio"));
					p.setCnpj(res.getString("cnpj"));
					p.setChavePix(res.getString("chavePix"));
					p.setEnd(e);

					return p;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean alterarProdutor(Produtor produtor, Usuario u) {
		String sql = "UPDATE produtores SET nomeNegocio = ?, cnpj = ?, chavePix = ? WHERE Usuarios_idUsuarios = ?";
		try (Connection conn = ConexaoBD.getConexaoMySQL(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setString(1, produtor.getNomeComercio());
			pstmt.setString(2, produtor.getCnpj());
			pstmt.setString(3, produtor.getChavePix());
			pstmt.setLong(4, u.getIdUsuario());

			int rowsAffected = pstmt.executeUpdate();
			if (rowsAffected > 0) {

				return uDAO.alterarUsuario(u);
			}
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
}
