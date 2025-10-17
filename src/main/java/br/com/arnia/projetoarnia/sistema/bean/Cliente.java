package br.com.arnia.projetoarnia.sistema.bean;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

import br.com.arnia.projetoarnia.faturamento.bean.Pedido;
import br.com.arnia.projetoarnia.sistema.enumerator.TipoPessoa;
import br.com.linkcom.neo.bean.annotation.DescriptionProperty;
import br.com.linkcom.neo.types.Cnpj;
import br.com.linkcom.neo.types.Cpf;

@Entity()
@SequenceGenerator(name = "sq_cliente", sequenceName = "sq_cliente")
public class Cliente {

	private Integer cdCliente;
	private String nome;
	private TipoPessoa tipopessoa;
	private Cpf cpf;
	private Cnpj cnpj;
	private Sexo sexo;
	private List<Endereco> enderecos;
	private Usuario usuario;
	private List<Pedido> pedidos;
	
	@Id
	@GeneratedValue(generator = "sq_cliente")
	public Integer getCdCliente() {
		return cdCliente;
	}
	public void setCdCliente(Integer cdCliente) {
		this.cdCliente = cdCliente;
	}
	@DescriptionProperty
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
	
	public Cnpj getCnpj() {
		return cnpj;
	}
	
	public void setCnpj(Cnpj cnpj) {
		this.cnpj = cnpj;
	}
	
	@Enumerated(EnumType.ORDINAL)
	public TipoPessoa getTipopessoa() {
		return tipopessoa;
	}
	public void setTipopessoa(TipoPessoa tipopessoa) {
		this.tipopessoa = tipopessoa;
	}
	@OneToOne
	@JoinColumn(name = "cdsexo")
	public Sexo getSexo() {
		return sexo;
	}
	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}
	
	@OneToMany(mappedBy = "cliente")
	public List<Endereco> getEnderecos() {
	    return enderecos;
	}

	public void setEnderecos(List<Endereco> enderecos) {
		this.enderecos = enderecos;
	}
	@OneToMany(mappedBy = "cdcliente")
	public List<Pedido> getPedidos() {
		return pedidos;
	}
	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}
	
	
}
