package br.com.arnia.projetoarnia.filtro;

import br.com.arnia.projetoarnia.suprimento.bean.Fornecedor;
import br.com.arnia.projetoarnia.suprimento.bean.Produtos;
import br.com.linkcom.neo.controller.crud.FiltroListagem;

public class FornecedorProdutosFiltro extends FiltroListagem{
	
	private Integer cdfornecedorproduto;
	private Fornecedor fornecedor;
	private Produtos produto;
	private Integer tempoentrega;
	private Boolean principal;
	private Boolean fabricante;
	public Integer getCdfornecedorproduto() {
		return cdfornecedorproduto;
	}
	public void setCdfornecedorproduto(Integer cdfornecedorproduto) {
		this.cdfornecedorproduto = cdfornecedorproduto;
	}
	public Fornecedor getFornecedor() {
		return fornecedor;
	}
	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}
	public Produtos getProduto() {
		return produto;
	}
	public void setProduto(Produtos produto) {
		this.produto = produto;
	}
	public Integer getTempoentrega() {
		return tempoentrega;
	}
	public void setTempoentrega(Integer tempoentrega) {
		this.tempoentrega = tempoentrega;
	}
	public Boolean getPrincipal() {
		return principal;
	}
	public void setPrincipal(Boolean principal) {
		this.principal = principal;
	}
	public Boolean getFabricante() {
		return fabricante;
	}
	public void setFabricante(Boolean fabricante) {
		this.fabricante = fabricante;
	}

	
}
