package modelo;

import java.util.ArrayList;

public class Produtor extends Usuario {
	private int idProdutor;
	private String nomeComercio;
	private int IdEndereco;
	private ArrayList<Pedido> pedido;
	private String cnpj;
	private Endereco end;
	private String chavePix;
	
	public Produtor() {
	}

	public Produtor(int idUsuario, String nomeComercio, int IdEndereco, ArrayList<Pedido> pedido, String cnpj) {
		this.idUsuario = idUsuario;
		this.nomeComercio = nomeComercio;
		this.IdEndereco = IdEndereco;
		this.pedido = pedido;
		this.cnpj = cnpj;
	}
	public Endereco getEnd() {
		return end;
	}

	public void setEnd(Endereco end) {
		this.end = end;
	}


	public String getNomeComercio() {
		return nomeComercio;
	}

	public void setNomeComercio(String nomeComercio) {
		this.nomeComercio = nomeComercio;
	}

	public int getEndereco() {
		return IdEndereco;
	}

	public void setEndereco(int IdEndereco) {
		this.IdEndereco = IdEndereco;
	}

	public ArrayList<Pedido> getPedido() {
		return pedido;
	}

	public void setPedido(ArrayList<Pedido> pedido) {
		this.pedido = pedido;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public int getIdProdutor() {
		return idProdutor;
	}

	public void setIdProdutor(int idProdutor) {
		this.idProdutor = idProdutor;
	}

	public String getChavePix() {
		return chavePix;
	}

	public void setChavePix(String chavePix) {
		this.chavePix = chavePix;
	}

}
