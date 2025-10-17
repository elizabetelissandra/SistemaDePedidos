package br.com.arnia.projetoarnia.faturamento.bean;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import br.com.linkcom.neo.types.Money;

@Entity
@SequenceGenerator(name = "sq_pedidoparcela", sequenceName = "sq_pedidoparcela")
public class PedidoParcela {

	private Integer cdpedidoparcela;
	private Pedido cdpedido;
	private Integer numeroparcela;
	private Money valorparcela;
	private Date datavencimento;
	
    @Id
    @GeneratedValue(generator =  "sq_pedidoparcela",strategy = GenerationType.AUTO)
	public Integer getCdpedidoparcela() {
		return cdpedidoparcela;
	}
	public void setCdpedidoparcela(Integer cdpedidoparcela) {
		this.cdpedidoparcela = cdpedidoparcela;
	}
	
	@ManyToOne
    @JoinColumn(name = "cdpedido", nullable = false)
	public Pedido getCdpedido() {
		return cdpedido;
	}
	public void setCdpedido(Pedido cdpedido) {
		this.cdpedido = cdpedido;
	}
	public Integer getNumeroparcela() {
		return numeroparcela;
	}
	public void setNumeroparcela(Integer numeroparcela) {
		this.numeroparcela = numeroparcela;
	}
	public Money getValorparcela() {
		return valorparcela;
	}
	public void setValorparcela(Money valorparcela) {
		this.valorparcela = valorparcela;
	}
	public Date getDatavencimento() {
		return datavencimento;
	}
	public void setDatavencimento(Date datavencimento) {
		this.datavencimento = datavencimento;
	}
	
	
	
}
