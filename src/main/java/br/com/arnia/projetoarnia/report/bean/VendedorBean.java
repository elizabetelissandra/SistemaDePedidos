package br.com.arnia.projetoarnia.report.bean;

import br.com.arnia.projetoarnia.faturamento.bean.Vendedor;
import br.com.linkcom.neo.types.Cpf;

public class VendedorBean {

	private String nome;
	private Cpf cpf;

	public VendedorBean() {

	}

	public VendedorBean(Vendedor vend) {
		this.setNome(vend.getNome());
		this.setCpf(vend.getCpf());
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Cpf getCpf() {
		return cpf;
	}

	public void setCpf(Cpf cpf) {
		this.cpf = cpf;
	}

}
