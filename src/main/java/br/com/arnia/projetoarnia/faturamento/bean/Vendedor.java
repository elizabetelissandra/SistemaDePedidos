package br.com.arnia.projetoarnia.faturamento.bean;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import br.com.linkcom.neo.bean.annotation.DescriptionProperty;
import br.com.linkcom.neo.types.Cpf;

@Entity
@SequenceGenerator(name = "sq_vendedor", sequenceName = "sq_vendedor")
public class Vendedor {

	private Integer cdvendedor;
	private String nome;
	private Cpf cpf;
	private List<Pedido> pedidos;
	
	@Id
	@GeneratedValue(generator = "sq_vendedor")
	public Integer getCdvendedor() {
		return cdvendedor;
	}
	public void setCdvendedor(Integer cdvendedor) {
		this.cdvendedor = cdvendedor;
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
	
	@OneToMany(mappedBy = "cdvendedor")
	public List<Pedido> getPedidos() {
		return pedidos;
	}
	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}
	
	
	
}
