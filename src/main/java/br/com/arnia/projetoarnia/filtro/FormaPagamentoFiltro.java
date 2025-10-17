package br.com.arnia.projetoarnia.filtro;

import br.com.linkcom.neo.controller.crud.FiltroListagem;

public class FormaPagamentoFiltro extends FiltroListagem{

	private String nome;
	private Boolean avista;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Boolean getAvista() {
		return avista;
	}
	public void setAvista(Boolean avista) {
		this.avista = avista;
	}
	
	
}
