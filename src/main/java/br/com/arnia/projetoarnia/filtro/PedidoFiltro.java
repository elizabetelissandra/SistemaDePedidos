package br.com.arnia.projetoarnia.filtro;

import java.sql.Date;

import br.com.arnia.projetoarnia.faturamento.bean.FormaPagamento;
import br.com.arnia.projetoarnia.faturamento.bean.Vendedor;
import br.com.arnia.projetoarnia.sistema.bean.Cliente;
import br.com.arnia.projetoarnia.sistema.bean.Endereco;
import br.com.arnia.projetoarnia.suprimento.bean.Produtos;
import br.com.linkcom.neo.controller.crud.FiltroListagem;
import br.com.linkcom.neo.types.Money;

public class PedidoFiltro extends FiltroListagem{

	private Cliente cdcliente = new Cliente();
    private Vendedor cdvendedor = new Vendedor();
    private Date dataInicial;
    private Date dataFinal;
    private Integer qtdParcelas;
    private String formaPagamento;
    

   
    private Produtos cdproduto = new Produtos();          
    private Money precoMinimo;          
    private Money precoMaximo;          
    private String nomesProdutos;

    private Date vencimentoInicial;   
    private Date vencimentoFinal;      
    private Boolean parcelasVencidas;
    
   
    
	public String getNomesProdutos() {
		return nomesProdutos;
	}
	public void setNomesProdutos(String nomesProdutos) {
		this.nomesProdutos = nomesProdutos;
	}
	public Cliente getCdcliente() {
		return cdcliente;
	}
	public void setCdcliente(Cliente cdcliente) {
		this.cdcliente = cdcliente;
	}
	public Vendedor getCdvendedor() {
		return cdvendedor;
	}
	public void setCdvendedor(Vendedor cdvendedor) {
		this.cdvendedor = cdvendedor;
	}
	public Date getDataInicial() {
		return dataInicial;
	}
	public void setDataInicial(Date dataInicial) {
		this.dataInicial = dataInicial;
	}
	public Date getDataFinal() {
		return dataFinal;
	}
	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}
	public Integer getQtdParcelas() {
		return qtdParcelas;
	}
	public void setQtdParcelas(Integer qtdParcelas) {
		this.qtdParcelas = qtdParcelas;
	}
	public String getFormaPagamento() {
		return formaPagamento;
	}
	public void setFormaPagamento(String formaPagamento) {
		this.formaPagamento = formaPagamento;
	}
	public Produtos getCdproduto() {
		return cdproduto;
	}
	public void setCdproduto(Produtos cdproduto) {
		this.cdproduto = cdproduto;
	}
	public Money getPrecoMinimo() {
		return precoMinimo;
	}
	public void setPrecoMinimo(Money precoMinimo) {
		this.precoMinimo = precoMinimo;
	}
	public Money getPrecoMaximo() {
		return precoMaximo;
	}
	public void setPrecoMaximo(Money precoMaximo) {
		this.precoMaximo = precoMaximo;
	}
	public Date getVencimentoInicial() {
		return vencimentoInicial;
	}
	public void setVencimentoInicial(Date vencimentoInicial) {
		this.vencimentoInicial = vencimentoInicial;
	}
	public Date getVencimentoFinal() {
		return vencimentoFinal;
	}
	public void setVencimentoFinal(Date vencimentoFinal) {
		this.vencimentoFinal = vencimentoFinal;
	}
	public Boolean getParcelasVencidas() {
		return parcelasVencidas;
	}
	public void setParcelasVencidas(Boolean parcelasVencidas) {
		this.parcelasVencidas = parcelasVencidas;
	} 

    
	
}