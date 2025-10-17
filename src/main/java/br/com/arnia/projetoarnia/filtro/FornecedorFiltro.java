package br.com.arnia.projetoarnia.filtro;

import br.com.linkcom.neo.controller.crud.FiltroListagem;

public class FornecedorFiltro extends FiltroListagem{

	private Integer cdfornecedor;
	private String nome;
	
	
	public Integer getCdfornecedor() {
		return cdfornecedor;
	}
	public void setCdfornecedor(Integer cdfornecedor) {
		this.cdfornecedor = cdfornecedor;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
}
