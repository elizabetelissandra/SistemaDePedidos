package br.com.arnia.projetoarnia.faturamento.bean;

import java.util.Locale;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Transient;

import com.ibm.icu.text.NumberFormat;

import br.com.arnia.projetoarnia.suprimento.bean.Produtos;
import br.com.linkcom.neo.types.Money;

@Entity
@SequenceGenerator(name = "sq_pedidoitem", sequenceName = "sq_pedidoitem")
public class PedidoItem {

	private Integer cdpedidoitem;
	private Pedido cdpedido;
	private Produtos cdproduto;
	private Money preco;
	private Integer quantidade;
	private Money descontounitario;

	
	
	@Id
	@GeneratedValue(generator = "sq_pedidoitem",strategy = GenerationType.AUTO)
	public Integer getCdpedidoitem() {
		return cdpedidoitem;
	}
	public void setCdpedidoitem(Integer cdpedidoitem) {
		this.cdpedidoitem = cdpedidoitem;
	}
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "cdpedido")
	public Pedido getCdpedido() {
		return cdpedido;
	}
	public void setCdpedido(Pedido cdpedido) {
		this.cdpedido = cdpedido;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cdProduto", nullable = false)
	public Produtos getCdproduto() {
		return cdproduto;
	}
	public void setCdproduto(Produtos cdproduto) {
		this.cdproduto = cdproduto;
	}
	
	public Money getPreco() {
		return preco;
	}
	public void setPreco(Money preco) {
		this.preco = preco;
	}
	
	public Integer getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	
	@Column(name = "desconto_unitario")
	public Money getDescontounitario() {
		return descontounitario;
	}
	public void setDescontounitario(Money descontounitario) {
		this.descontounitario = descontounitario;
	}
	
	
	@Transient
	public Money getTotalItem() {
		if (preco == null || quantidade == null) {
            return new Money(0);
        }

		Money total = preco.multiply(new Money(quantidade));
		if(descontounitario != null) {
			total = total.subtract(descontounitario.multiply(new Money(quantidade)));
		}
		return total;

	}
	
	
}
