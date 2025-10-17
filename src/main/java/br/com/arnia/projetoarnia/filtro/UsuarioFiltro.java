package br.com.arnia.projetoarnia.filtro;

import br.com.linkcom.neo.controller.crud.FiltroListagem;

public class UsuarioFiltro extends FiltroListagem {

	private String login;
	private String senha;
	private String email;
	
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
	
	

}
