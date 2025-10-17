package br.com.arnia.projetoarnia.filtro;

import br.com.linkcom.neo.controller.crud.FiltroListagem;

public class MunicipioFiltro extends FiltroListagem{
	
	private String nome;
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}

}
