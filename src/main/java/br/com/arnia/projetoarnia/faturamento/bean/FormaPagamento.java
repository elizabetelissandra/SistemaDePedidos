package br.com.arnia.projetoarnia.faturamento.bean;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import br.com.linkcom.neo.bean.annotation.DescriptionProperty;
import br.com.linkcom.neo.bean.annotation.DisplayName;

@Entity
@SequenceGenerator(name = "sq_formapagamento", sequenceName = "sq_formapagamento")
public class FormaPagamento {

	
	private Integer cdformapagamento;
	private String nome;
	private Boolean avista;
	
	private List<Pedido> pedidos;
	
	@Id
	@GeneratedValue(generator = "sq_formapagamento", strategy = GenerationType.IDENTITY)
	public Integer getCdformapagamento() {
		return cdformapagamento;
	}
	public void setCdformapagamento(Integer cdformapagamento) {
		this.cdformapagamento = cdformapagamento;
	}
	@DescriptionProperty
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Boolean getAvista() {
		return avista;
	}
	public void setAvista(Boolean avista) {
		this.avista = avista;
	}
	@OneToMany(mappedBy = "formapagamento")
	public List<Pedido> getPedidos() {
		return pedidos;
	}
	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}
	
	
}
