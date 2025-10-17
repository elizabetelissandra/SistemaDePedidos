package br.com.arnia.projetoarnia.filtro;

import br.com.linkcom.neo.bean.annotation.DisplayName;
import br.com.linkcom.neo.controller.crud.FiltroListagem;

public class UnidadeMedidaFiltro extends FiltroListagem {

	private Integer cdUnidadeMedida;
	private String nome;
	private String sigla;
	private Boolean ativo;
	
	@DisplayName("Códido Unidade de Medida")
	public Integer getCdUnidadeMedida() {
		return cdUnidadeMedida;
	}
	public void setCdUnidadeMedida(Integer cdUnidadeMedida) {
		this.cdUnidadeMedida = cdUnidadeMedida;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSigla() {
		return sigla;
	}
	public void setSigla(String sigla) {
		this.sigla = sigla;
	}
	public Boolean getAtivo() {
		return ativo;
	}
	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
	
	
}
