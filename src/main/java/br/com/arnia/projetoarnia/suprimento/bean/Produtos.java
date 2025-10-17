package br.com.arnia.projetoarnia.suprimento.bean;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import br.com.linkcom.neo.bean.annotation.DescriptionProperty;
import br.com.linkcom.neo.bean.annotation.DisplayName;
import br.com.linkcom.neo.types.Money;

@Entity
@SequenceGenerator(name = "sq_produto", sequenceName = "sq_produto")
public class Produtos {

	private Integer cdProduto;
	private String nome;

	private String codigoBarras;

	private Money preco;

	private UnidadeMedida unidadeMedida;

	private List<FornecedorProdutos> fornecedorProdutos = new ArrayList<FornecedorProdutos>();

	@Id
	@GeneratedValue(generator = "sq_produto")
	@DisplayName("Código Produto")
	public Integer getCdProduto() {
		return cdProduto;
	}

	public void setCdProduto(Integer cdProduto) {
		this.cdProduto = cdProduto;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@DescriptionProperty
	@Column(name = "\"codigoBarras\"")
	public String getCodigoBarras() {
		return codigoBarras;
	}

	public void setCodigoBarras(String codigoBarras) {
		this.codigoBarras = codigoBarras;
	}

	@Column(name = "preco")
	public Money getPreco() {
		return preco;
	}

	public void setPreco(Money preco) {
		this.preco = preco;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cdunidademedida")
	public UnidadeMedida getUnidadeMedida() {
		return unidadeMedida;
	}

	public void setUnidadeMedida(UnidadeMedida unidadeMedida) {
		this.unidadeMedida = unidadeMedida;
	}

	@OneToMany(mappedBy = "produto")
	public List<FornecedorProdutos> getFornecedorProdutos() {
		return fornecedorProdutos;
	}

	public void setFornecedorProdutos(List<FornecedorProdutos> fornecedorProdutos) {
		this.fornecedorProdutos = fornecedorProdutos;
	}
	
	public void addFornecedorProduto(FornecedorProdutos fp) {
	    fp.setProduto(this); 
	    this.fornecedorProdutos.add(fp);
	}


}
