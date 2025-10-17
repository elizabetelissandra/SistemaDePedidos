package br.com.arnia.projetoarnia.suprimento.bean;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import br.com.linkcom.neo.bean.annotation.DisplayName;

@Entity
@SequenceGenerator(name = "sq_fornecedorproduto", sequenceName = "sq_fornecedorproduto")
public class FornecedorProdutos {

	
	private Integer cdfornecedorproduto;
	private Fornecedor fornecedor;
	private Produtos produto;
	private Integer tempoentrega;
	private Boolean principal;
	private Boolean fabricante;

	@Id
	@GeneratedValue(generator = "sq_fornecedorproduto", strategy = GenerationType.AUTO)
	@JoinColumn(name = "cdfornecedorproduto")
	public Integer getCdfornecedorproduto() {
	    return cdfornecedorproduto;
	}

	public void setCdfornecedorproduto(Integer cdfornecedorproduto) {
	    this.cdfornecedorproduto = cdfornecedorproduto;
	}


	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fornecedor")
	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "produto")
	public Produtos getProduto() {
		return produto;
	}

	public void setProduto(Produtos produto) {
		this.produto = produto;
	}

	@DisplayName("Tempo de Entrega (dias)")
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
