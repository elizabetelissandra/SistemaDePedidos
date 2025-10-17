package br.com.arnia.projetoarnia.suprimento.bean;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.linkcom.neo.bean.annotation.DescriptionProperty;

@Entity
@Table(name = "fornecedor")
@SequenceGenerator(name = "sq_fornecedor", sequenceName = "sq_fornecedor")
public class Fornecedor {

	private Integer cdfornecedor;
	private String nome;

	private List<FornecedorProdutos> fornecedorProdutos = new ArrayList<FornecedorProdutos>();

	@Id
	@GeneratedValue(generator = "sq_fornecedor", strategy = GenerationType.AUTO)
	public Integer getCdfornecedor() {
		return cdfornecedor;
	}

	public void setCdfornecedor(Integer cdfornecedor) {
		this.cdfornecedor = cdfornecedor;
	}
	@DescriptionProperty
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@OneToMany(mappedBy = "fornecedor", cascade = CascadeType.ALL)
	public List<FornecedorProdutos> getFornecedorProdutos() {
		return fornecedorProdutos;
	}

	public void setFornecedorProdutos(List<FornecedorProdutos> fornecedorProdutos) {
		this.fornecedorProdutos = fornecedorProdutos;
	}

}
