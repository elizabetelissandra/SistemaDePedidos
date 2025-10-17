package br.com.arnia.projetoarnia.sistema.bean;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import br.com.linkcom.neo.bean.annotation.DescriptionProperty;

@Entity
@SequenceGenerator(name = "sq_uf", sequenceName = "sq_uf")
public class Uf {

	private Integer cduf;
	private String nome;
	private String sigla;
	private List<Municipio> municipios;
	private List<Endereco> listaEnderecos;

	@Id
	@GeneratedValue(generator = "sq_uf")
	public Integer getCduf() {
		return cduf;
	}
	
	public void setCduf(Integer cduf) {
		this.cduf = cduf;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Column(name = "nome")
	public String getNome() {
		return nome;
	}

	@DescriptionProperty
	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	@OneToMany(mappedBy = "uf")
	public List<Municipio> getMunicipios() {
		return municipios;
	}

	public void setMunicipios(List<Municipio> municipios) {
		this.municipios = municipios;
	}

	@OneToMany(mappedBy = "uf")
	public List<Endereco> getListaEnderecos() {
		return listaEnderecos;
	}

	public void setListaEnderecos(List<Endereco> listaEnderecos) {
		this.listaEnderecos = listaEnderecos;
	}
	
	@Override
	public String toString() {
	    return "Uf{id=" + cduf + ", sigla='" + sigla + "', nome='" + nome + "'}";
	}


}
