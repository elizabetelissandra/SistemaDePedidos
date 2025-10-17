package br.com.arnia.projetoarnia.sistema.bean;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Transient;

import br.com.linkcom.neo.authorization.User;

@Entity
@SequenceGenerator(name = "sq_usuario", sequenceName = "sq_usuario")
public class Usuario implements User {

	private Integer cdusuario;
	private String nome;
	private String login;
	private String senha;
	private String email;

	@Id
	@GeneratedValue(generator = "sq_usuario")
	public Integer getCdUsuario() {
		return cdusuario;
	}

	public void setCdUsuario(Integer cdUsuario) {
		this.cdusuario = cdUsuario;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
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

	@Override
	@Transient
	public String getPassword() {
		return senha;
	}

}
