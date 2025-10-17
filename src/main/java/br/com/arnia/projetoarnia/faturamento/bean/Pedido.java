package br.com.arnia.projetoarnia.faturamento.bean;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Transient;


import br.com.arnia.projetoarnia.sistema.bean.Cliente;
import br.com.arnia.projetoarnia.sistema.bean.Endereco;
import br.com.linkcom.neo.types.ListSet;
import br.com.linkcom.neo.types.Money;

@Entity
@SequenceGenerator(name = "sq_pedido", sequenceName = "sq_pedido")
public class Pedido {
	
	private Integer cdpedido;
	private Cliente cdcliente;
	private Endereco cdendereco;
	private Vendedor cdvendedor;
	private Date datapedido;
	private FormaPagamento formapagamento;
	private String observacao;
	private Integer qtdparcelas;
	private List<PedidoItem> itens  = new ArrayList<PedidoItem>();
    private List<PedidoParcela> parcelas = new ArrayList<PedidoParcela>();
	
	@Id
	@GeneratedValue(generator = "sq_pedido", strategy = GenerationType.AUTO)
	public Integer getCdpedido() {
		return cdpedido;
	}
	public void setCdpedido(Integer cdpedido) {
		this.cdpedido = cdpedido;
	}
	@ManyToOne
	@JoinColumn(name = "cdcliente")
	public Cliente getCdcliente() {
		return cdcliente;
	}
	public void setCdcliente(Cliente cdcliente) {
		this.cdcliente = cdcliente;
	}
	@ManyToOne
    @JoinColumn(name = "cdendereco")
	public Endereco getCdendereco() {
		return cdendereco;
	}
	public void setCdendereco(Endereco cdendereco) {
		this.cdendereco = cdendereco;
	}
	@ManyToOne
	@JoinColumn(name = "cdvendedor", nullable = false)
	public Vendedor getCdvendedor() {
		return cdvendedor;
	}
	public void setCdvendedor(Vendedor cdvendedor) {
		this.cdvendedor = cdvendedor;
	}
	
	public Date getDatapedido() {
		return datapedido;
	}
	
	public void setDatapedido(Date datapedido) {
		this.datapedido = datapedido;
	}
	
	@ManyToOne
	@JoinColumn(name = "cdformapagamento", nullable = false)
	public FormaPagamento getFormapagamento() {
		return formapagamento;
	}
	public void setFormapagamento(FormaPagamento formapagamento) {
		this.formapagamento = formapagamento;
	}
	
	public String getObservacao() {
		return observacao;
	}
	
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	public Integer getQtdparcelas() {
		return qtdparcelas;
	}
	public void setQtdparcelas(Integer qtdparcelas) {
		this.qtdparcelas = qtdparcelas;
	}
	@OneToMany(mappedBy = "cdpedido")
	public List<PedidoItem> getItens() {
		return itens;
	}
	public void setItens(List<PedidoItem> itens) {
		this.itens = itens;
	}
	@OneToMany(mappedBy = "cdpedido")
	public List<PedidoParcela> getParcelas() {
		return parcelas;
	}
	public void setParcelas(List<PedidoParcela> parcelas) {
		this.parcelas = parcelas;
	}

	@Transient
	public Money getTotalPedido() {
	    if (itens == null || itens.isEmpty()) {
	        return new Money(0);
	    }
	    Money total = new Money(0);
	    for (PedidoItem item : itens) {
	        if (item.getTotalItem() != null) {
	            total = total.add(item.getTotalItem());
	        }
	    }
	    return total;
	}

	
	@Transient
	public String getNomesProdutos() {
	   
	    if (itens == null || itens.isEmpty()) {
	        return "";
	    }

	    StringBuilder sb = new StringBuilder();
	    for (PedidoItem item : itens) {

	        if (item != null && item.getCdproduto() != null && item.getCdproduto().getNome() != null) {
	            if (sb.length() > 0) {
	                sb.append("<br/>");
	            }
	            sb.append(item.getCdproduto().getNome());
	        }
	    }
 return sb.toString();
	}
	
}
