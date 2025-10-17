package br.com.arnia.projetoarnia.suprimento.bean;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import br.com.linkcom.neo.bean.annotation.DescriptionProperty;
import br.com.linkcom.neo.bean.annotation.DisplayName;


@Entity
@Table(name = "\"UnidadeMedida\"")
@SequenceGenerator(name = "sq_unidadeMedida", sequenceName = "sq_unidadeMedida")
public class UnidadeMedida {
	
	
	private Integer cdUnidadeMedida;
	private String nome;
	private String sigla;
	private Boolean ativo;
	private List<Produtos> produtos = new ArrayList<Produtos>();

	
	@Id
	@GeneratedValue(generator = "sq_unidadeMedida")
	@DisplayName("Código Unidade Medida")
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
	@DescriptionProperty
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

	@OneToMany(mappedBy = "unidadeMedida")
	public List<Produtos> getProdutos() {
		return produtos;
	}


	public void setProdutos(List<Produtos> produtos) {
		this.produtos = produtos;
	}

	
	
}
