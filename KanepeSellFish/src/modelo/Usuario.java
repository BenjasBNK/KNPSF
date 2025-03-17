package modelo;

import java.awt.Image;
import java.io.FileInputStream;

public class Usuario {

	private String nome;
	private String senha;
	private String email;
	private String cpf;
	private String tel;
	private Produtor prod;
	private Endereco end;
	protected int idUsuario;
	private String img;
	private FileInputStream fotoC;
	private Image foto;
	
	public Usuario() {}

	public Usuario(String cpf, String nome, String senha, String email) {
		this.cpf = cpf;
		this.nome = nome;
		this.senha = senha;
		this.email = email;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	
	public Produtor getProd() {
		return prod;
	}

	public void setProd(Produtor prod) {
		this.prod = prod;
	}

	public Endereco getEnd() {
		return end;
	}

	public void setEnd(Endereco end) {
		this.end = end;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}
  
  	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		Usuario usuario = (Usuario) obj;
		return cpf == usuario.cpf;
	}
	public int getIdUsuario() {
		return this.idUsuario;
	}

	public boolean isProdutor() {
		// TODO Auto-generated method stub
		return false;
	}

	public FileInputStream getFotoC() {
		return fotoC;
	}

	public void setFotoC(FileInputStream fotoC) {
		this.fotoC = fotoC;
	}

	public Image getFoto() {
		return foto;
	}

	public void setFoto(Image foto) {
		this.foto = foto;
	}
}
