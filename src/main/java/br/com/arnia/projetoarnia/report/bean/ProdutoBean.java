package br.com.arnia.projetoarnia.report.bean;

import br.com.arnia.projetoarnia.suprimento.bean.Produtos;
import br.com.linkcom.neo.types.Money;

public class ProdutoBean {

	private String nome;
	private Money preco;
	private String siglaUnidadeMedida;

	public ProdutoBean() {

	}

	public ProdutoBean(Produtos prod) {
		this.setNome(prod.getNome());
		this.setPreco(prod.getPreco());
		if (prod.getUnidadeMedida() != null) {
			this.setSiglaUnidadeMedida(prod.getUnidadeMedida().getSigla());
		}
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Money getPreco() {
		return preco;
	}

	public void setPreco(Money preco) {
		this.preco = preco;
	}

	public String getSiglaUnidadeMedida() {
		return siglaUnidadeMedida;
	}

	public void setSiglaUnidadeMedida(String siglaUnidadeMedida) {
		this.siglaUnidadeMedida = siglaUnidadeMedida;
	}

}
