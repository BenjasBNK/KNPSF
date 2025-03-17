package modelo;

public interface IEnderecoDAO {
	public int inserirEnderecoDoComercio(Endereco endereco);
	 public int atualizarEndereco(Endereco endereco);
	 public Endereco buscarendereco(int idEndereco);
}
