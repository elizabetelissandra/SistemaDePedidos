package br.com.arnia.projetoarnia.report.bean;

import br.com.arnia.projetoarnia.sistema.bean.Cliente;
import br.com.arnia.projetoarnia.sistema.bean.Sexo;
import br.com.arnia.projetoarnia.sistema.enumerator.TipoPessoa;
import br.com.linkcom.neo.types.Cnpj;
import br.com.linkcom.neo.types.Cpf;

public class ClienteBean {

	private String nome;
	private TipoPessoa tipopessoa;
	private Cpf cpf;
	private Cnpj cnpj;
	private Sexo sexo;

	public ClienteBean() {

	}

	public ClienteBean(Cliente cliente) {
		this.setNome(cliente.getNome());
		this.setCpf(cliente.getCpf());
		this.setCnpj(cliente.getCnpj());
		this.setTipopessoa(cliente.getTipopessoa());
		this.setSexo(cliente.getSexo());

	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public TipoPessoa getTipopessoa() {
		return tipopessoa;
	}

	public void setTipopessoa(TipoPessoa tipopessoa) {
		this.tipopessoa = tipopessoa;
	}

	public Cpf getCpf() {
		return cpf;
	}

	public void setCpf(Cpf cpf) {
		this.cpf = cpf;
	}

	public Cnpj getCnpj() {
		return cnpj;
	}

	public void setCnpj(Cnpj cnpj) {
		this.cnpj = cnpj;
	}

	public Sexo getSexo() {
		return sexo;
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}

	public String getCpfFormatado() {
		return cpf != null ? cpf.toString() : "";
	}

	public String getCnpjFormatado() {
		return cnpj != null ? cnpj.toString() : "";
	}

	public String getTipopessoaDescricao() {
		return tipopessoa != null ? tipopessoa.toString() : "";
	}
	
	public String getSexoDescricao() {
		return sexo != null ? sexo.getNome() : "";
	}

}
