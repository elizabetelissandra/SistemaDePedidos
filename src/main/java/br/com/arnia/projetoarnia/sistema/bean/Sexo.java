package br.com.arnia.projetoarnia.sistema.bean;

import javax.persistence.Entity;
import javax.persistence.Id;

import br.com.linkcom.neo.bean.annotation.DescriptionProperty;

@Entity
public class Sexo {
	
	private Integer cdSexo;
	private String nome;
	
	@Id
	public Integer getCdSexo() {
		return cdSexo;
	}
	public void setCdSexo(Integer cdSexo) {
		this.cdSexo = cdSexo;
	}
	@DescriptionProperty
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
}
