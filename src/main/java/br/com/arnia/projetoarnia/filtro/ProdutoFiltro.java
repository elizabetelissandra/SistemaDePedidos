package br.com.arnia.projetoarnia.filtro;


import br.com.arnia.projetoarnia.suprimento.bean.UnidadeMedida;
import br.com.linkcom.neo.bean.annotation.DisplayName;
import br.com.linkcom.neo.controller.crud.FiltroListagem;

public class ProdutoFiltro extends FiltroListagem {

	private String nome;
	private Integer cdProduto;
	private UnidadeMedida unidadeMedida;
	private Double precoMin;
	private Double precoMax;


	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	@DisplayName("Código Produto")
	public Integer getCdProduto() {
		return cdProduto;
	}

	public void setCdProduto(Integer cdProduto) {
		this.cdProduto = cdProduto;
	}
	
	@DisplayName("Preço mínimo")
	public Double getPrecoMin() {
		return precoMin;
	}

	public void setPrecoMin(Double precoMin) {
		this.precoMin = precoMin;
	}

	@DisplayName("Preço máximo")
	public Double getPrecoMax() {
		return precoMax;
	}

	public void setPrecoMax(Double precoMax) {
		this.precoMax = precoMax;
	}

	public UnidadeMedida getUnidadeMedida() {
		return unidadeMedida;
	}

	public void setUnidadeMedida(UnidadeMedida unidadeMedida) {
		this.unidadeMedida = unidadeMedida;
	}
	
	
}
