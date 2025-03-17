package controle;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import modelo.Endereco;
import modelo.IEnderecoDAO;

public class EnderecoDAO implements IEnderecoDAO {

    private static EnderecoDAO instancia;

    private EnderecoDAO() {
    }

    public static EnderecoDAO getInstancia() {
        if (instancia == null) {
            instancia = new EnderecoDAO();
        }
        return instancia;
    }

    @Override
    public int inserirEnderecoDoComercio(Endereco endereco) {
        String sql = "INSERT INTO enderecos (Cidade, Rua, Bairro, Numero, cep) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = ConexaoBD.getConexaoMySQL();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, endereco.getCidade());
            pstmt.setString(2, endereco.getLogradouro());
            pstmt.setString(3, endereco.getBairro());
            pstmt.setInt(4, endereco.getNumero());
            pstmt.setString(5, endereco.getCep());

            pstmt.executeUpdate();

            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1); // Retorna o ID gerado
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1; // Retorna -1 em caso de erro
    }

    @Override
    public int atualizarEndereco(Endereco endereco) {
        String sql = "UPDATE enderecos SET Cidade = ?, Bairro = ?, Rua = ?, Numero = ?, cep = ? WHERE idEnderecos = ?";
        try (Connection conn = ConexaoBD.getConexaoMySQL();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, endereco.getCidade());
            pstmt.setString(2, endereco.getBairro());
            pstmt.setString(3, endereco.getLogradouro());
            pstmt.setInt(4, endereco.getNumero());
            pstmt.setString(5, endereco.getCep());
            pstmt.setInt(6, endereco.getIdEndereco());

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1; // Retorna -1 em caso de erro
    }

    public Endereco buscaCEP(String cep) {
        Endereco endereco = new Endereco();
        try {
            URL url = new URL("http://cep.republicavirtual.com.br/web_cep.php?cep=" + cep + "&formato=xml");
            SAXReader xml = new SAXReader();
            Document doc = xml.read(url);
            Element root = doc.getRootElement();

            for (Iterator<Element> it = root.elementIterator(); it.hasNext();) {
                Element element = it.next();
                switch (element.getQualifiedName()) {
                    case "cidade":
                        endereco.setCidade(element.getText());
                        break;
                    case "logradouro":
                        endereco.setLogradouro(element.getText());
                        break;
                    case "bairro":
                        endereco.setBairro(element.getText());
                        break;
                }
            }
            return endereco;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Endereco buscarendereco(int idEndereco) {
        String sql = "SELECT * FROM enderecos WHERE idEnderecos = ?";
        try (Connection conn = ConexaoBD.getConexaoMySQL();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, idEndereco);

            try (ResultSet res = pstmt.executeQuery()) {
                if (res.next()) {
                    Endereco endereco = new Endereco();
                    endereco.setIdEndereco(idEndereco);
                    endereco.setBairro(res.getString("Bairro"));
                    endereco.setCep(res.getString("cep"));
                    endereco.setCidade(res.getString("Cidade"));
                    endereco.setLogradouro(res.getString("Rua"));
                    endereco.setNumero(res.getInt("Numero"));

                    return endereco;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
