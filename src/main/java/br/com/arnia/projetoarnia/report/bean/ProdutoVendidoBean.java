package br.com.arnia.projetoarnia.report.bean;

import java.sql.Date;

import br.com.linkcom.neo.types.Money;

public class ProdutoVendidoBean {

    private String cliente;
    private Date dataPedido;
    private String produto;
    private Money preco;
    private Integer quantidade;
    private Money totalItem;

    public ProdutoVendidoBean() {
    }

    public ProdutoVendidoBean(String cliente, Date dataPedido, String produto, Money preco, Integer quantidade, Money totalItem) {
        this.cliente = cliente;
        this.dataPedido = dataPedido;
        this.produto = produto;
        this.preco = preco;
        this.quantidade = quantidade;
        this.totalItem = totalItem;
    }

    public String getCliente() { return cliente; }
    public Date getDataPedido() { return dataPedido; }
    public String getProduto() { return produto; }
    public Money getPreco() { return preco; }
    public Integer getQuantidade() { return quantidade; }
    public Money getTotalItem() { return totalItem; }
}