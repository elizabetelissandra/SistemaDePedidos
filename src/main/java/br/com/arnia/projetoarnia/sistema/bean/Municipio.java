package br.com.arnia.projetoarnia.sistema.bean;


import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import br.com.linkcom.neo.bean.annotation.DescriptionProperty;


@Entity
@SequenceGenerator(name = "sq_municipio", sequenceName = "sq_municipio")
public class Municipio {

	private Integer cdmunicipio;
	private String nome;
	private Uf uf;
	private List<Endereco> listaEnderecos;
	
	@Id
	@GeneratedValue(generator = "sq_municipio")
	public Integer getCdmunicipio() {
		return cdmunicipio;
	}
	public void setCdmunicipio(Integer cdmunicipio) {
		this.cdmunicipio = cdmunicipio;
	}
	@DescriptionProperty
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	@ManyToOne
	@JoinColumn(name = "cduf")
	public Uf getUf() {
		return uf;
	}
	public void setUf(Uf uf) {
		this.uf = uf;
	}
	
	@OneToMany(mappedBy = "municipio")
	public List<Endereco> getEnderecos() {
		return listaEnderecos;
	}
	public void setEnderecos(List<Endereco> enderecos) {
		this.listaEnderecos = enderecos;
	}
	
	
	
}
