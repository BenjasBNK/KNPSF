package controle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import modelo.IUsuarioDAO;
import modelo.Usuario;

public class UsuarioDAO implements IUsuarioDAO {

	private static ArrayList<Usuario> listaUsuarios;

	private static UsuarioDAO instancia;

	public UsuarioDAO() {

	}

	public static UsuarioDAO getInstancia() {

		if (instancia == null) {
			instancia = new UsuarioDAO();
			listaUsuarios = new ArrayList<>();
		}

		return instancia;
	}

	@Override
	public int inserirUsuario(Usuario usuario) {
		String sql = "INSERT INTO usuarios (email_Usuario, senha_Usuario, cpf_Usuario, nome_Usuario) VALUES (?, ?, ?, ?)";
		try (Connection conn = ConexaoBD.getConexaoMySQL();
				PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

			pstmt.setString(1, usuario.getEmail());
			pstmt.setString(2, usuario.getSenha());
			pstmt.setString(3, usuario.getCpf());
			pstmt.setString(4, usuario.getNome());

			pstmt.executeUpdate();

			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				return rs.getInt(1); // Retorna o ID gerado
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return -1; // Retorna -1 em caso de erro
	}

	@Override
	public boolean removerUsuario(String cpf) {
		for (Usuario usuario : listaUsuarios) {
			if (usuario.getCpf().equals(cpf)) {
				listaUsuarios.remove(usuario);
			}
		}

		return false;
	}

	@Override
	public Usuario consultarUsuarioLoginSenha(String email, String senha) {
		PreparedStatement stmt1 = null;

		Connection conn = ConexaoBD.getConexaoMySQL();

		try {
			stmt1 = conn
					.prepareStatement("SELECT * FROM kanepe.usuarios where email_Usuario = ? and senha_Usuario = ?");
			ResultSet res1 = null;
			stmt1.setString(1, email);
			stmt1.setString(2, senha);

			res1 = stmt1.executeQuery();

			while (res1.next()) {

				Usuario u = new Usuario();

				u.setNome(res1.getString("nome_Usuario"));
				u.setCpf(res1.getString("cpf_Usuario"));
				u.setEmail(res1.getString("email_Usuario"));
				u.setSenha(res1.getString("senha_Usuario"));
				u.setIdUsuario(res1.getInt("idUsuarios"));
				u.setTel(res1.getString("telefone"));

				return u;
			}

			res1.close();
			stmt1.close();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean consultarUsuarioVendedor(Usuario usuario) {
		PreparedStatement stmt1 = null;

		Connection conn = ConexaoBD.getConexaoMySQL();

		try {
			stmt1 = conn.prepareStatement("SELECT * FROM kanepe.produtores WHERE Usuarios_idUsuarios = ?");
			ResultSet res1 = null;
			stmt1.setLong(1, usuario.getIdUsuario());

			res1 = stmt1.executeQuery();

//			while (res1.next()) {

//				Usuario u = new Usuario();
//
//				u.setNome(res1.getString("nome_Usuario"));
//				u.setCpf(res1.getString("cpf_Usuario"));
//				u.setEmail(res1.getString("email_Usuario"));
//				u.setSenha(res1.getString("senha_Usuario"));
//				u.setIdUsuario(res1.getInt("idUsuarios"));
//				u.setDesc(res1.getString("descricao"));
//				u.setImg(res1.getString("img"));
//				u.setTel(res1.getString("telefone"));
//
//				Endereco e = new Endereco();
//				e.setBairro(res1.getString("Bairro"));
//				e.setCidade(res1.getString("Cidade"));
//				e.setLogradouro(res1.getString("Rua"));
//				e.setNumero(res1.getInt("Numero"));
//				e.setId(res1.getInt("idEnderecos"));
//				u.setEnd(e);
//
//				Produtor p = new Produtor();
//				p.setCnpj(res1.getString("cnpj"));
//				p.setNomeComercio(res1.getString("nomeNegocio"));
//				p.setIdP(res1.getInt("idProdutores"));
//				u.setProd(p);
//
//				return u;

			if (res1.next()) {
				return true;
			} else {
				return false;
			}
//			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Usuario consultaUsuarioCadastrado(String cpf, String email) {
		PreparedStatement stmt1 = null;

		Connection conn = ConexaoBD.getConexaoMySQL();

		try {
			stmt1 = conn.prepareStatement("SELECT * FROM kanepe.usuarios where cpf_Usuario = ? OR email_Usuario=?");
			ResultSet res1 = null;
			stmt1.setString(1, cpf);
			stmt1.setString(2, email);

			res1 = stmt1.executeQuery();

			while (res1.next()) {

				Usuario u = new Usuario();

				u.setNome(res1.getString("nome_Usuario"));
				u.setCpf(res1.getString("cpf_Usuario"));
				u.setEmail(res1.getString("email_Usuario"));
				u.setSenha(res1.getString("senha_Usuario"));

				return u;
			}

			res1.close();
			stmt1.close();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	// Método para validar CPF
	public boolean validarCPF(String cpf) {
		// Remove caracteres não numéricos
		cpf = cpf.replaceAll("[^0-9]", "");

		// Verifica se o CPF tem 11 dígitos
		if (cpf.length() != 11)
			return false;

		// Lógica de validação do CPF
		int soma = 0;
		int peso = 10;

		for (int i = 0; i < 9; i++) {
			soma += Character.getNumericValue(cpf.charAt(i)) * peso;
			peso--;
		}

		int primeiroDigito = 11 - (soma % 11);
		primeiroDigito = primeiroDigito >= 10 ? 0 : primeiroDigito;
		if (Character.getNumericValue(cpf.charAt(9)) != primeiroDigito)
			return false;

		soma = 0;
		peso = 11;
		for (int i = 0; i < 10; i++) {
			soma += Character.getNumericValue(cpf.charAt(i)) * peso;
			peso--;
		}

		int segundoDigito = 11 - (soma % 11);
		segundoDigito = segundoDigito >= 10 ? 0 : segundoDigito;
		return Character.getNumericValue(cpf.charAt(10)) == segundoDigito;
	}
	
	public boolean alterarSenha(String email, String senha) {
	    String sql = "UPDATE usuarios SET senha_Usuario = ? WHERE email_Usuario = ?";
	    try (Connection conn = ConexaoBD.getConexaoMySQL(); 
	         PreparedStatement pstmt = conn.prepareStatement(sql)) {

	        pstmt.setString(1, senha);
	        pstmt.setString(2, email);

	        int rowsAffected = pstmt.executeUpdate();
	        return rowsAffected > 0; // Retorna true se a senha foi alterada

	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}
	@Override
	public boolean alterarUsuario(Usuario usuario) {
		String sql = "UPDATE usuarios set email_Usuario = ?, nome_Usuario = ?, telefone = ? where idUsuarios = ?";
		try (Connection conn = ConexaoBD.getConexaoMySQL(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setString(1, usuario.getEmail());
			pstmt.setString(2, usuario.getNome());
			pstmt.setString(3, usuario.getTel());
			pstmt.setInt(4, usuario.getIdUsuario());
			pstmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean verificarTelefone(String telefone, int idUsuario) {
	    String sql = "SELECT COUNT(*) FROM usuarios WHERE telefone = ? AND idUsuarios <> ?";
	    try (Connection conn = ConexaoBD.getConexaoMySQL();
	         PreparedStatement pstmt = conn.prepareStatement(sql)) {

	        pstmt.setString(1, telefone);
	        pstmt.setInt(2, idUsuario);
	        ResultSet rs = pstmt.executeQuery();

	        if (rs.next()) {
	            return rs.getInt(1) > 0; // Se COUNT(*) > 0, já existe e pertence a outro usuário
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return false;
	}

}