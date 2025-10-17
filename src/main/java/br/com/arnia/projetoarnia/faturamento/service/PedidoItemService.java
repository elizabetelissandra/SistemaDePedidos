package br.com.arnia.projetoarnia.faturamento.service;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import br.com.arnia.projetoarnia.faturamento.bean.PedidoItem;
import br.com.arnia.projetoarnia.faturamento.dao.PedidoItemDAO;

import br.com.arnia.projetoarnia.report.bean.ProdutoVendidoBean;

import br.com.arnia.projetoarnia.report.filter.ProdutoVendidoFiltro;
import br.com.linkcom.neo.report.IReport;
import br.com.linkcom.neo.report.Report;
import br.com.linkcom.neo.service.GenericService;
import br.com.linkcom.neo.types.Money;


public class PedidoItemService extends GenericService<PedidoItem>{

	private PedidoItemDAO pedidoItemDAO;

	public void setPedidoItemDAO(PedidoItemDAO pedidoItemDAO) {
		this.pedidoItemDAO = pedidoItemDAO;
	}
	
	public List<PedidoItem> findForReport(ProdutoVendidoFiltro filtro){
		return pedidoItemDAO.findProdutosVendidosForReport(filtro);
	}
	
	public IReport createReport(ProdutoVendidoFiltro filtro) {
	    List<PedidoItem> listaPedidosItens = this.findForReport(filtro);
	    List<ProdutoVendidoBean> listaBeans = new ArrayList<ProdutoVendidoBean>();
	    for (PedidoItem item : listaPedidosItens) {
	    	 String cliente = item.getCdpedido().getCdcliente().getNome();
	         Date dataPedido = item.getCdpedido().getDatapedido();
	         String produto = item.getCdproduto().getNome();
	         Money preco = item.getPreco();
	         Integer quantidade = item.getQuantidade();
	         Money totalItem = preco.multiply(new Money(quantidade));
	        
	         listaBeans.add(new ProdutoVendidoBean(cliente, dataPedido, produto, preco, quantidade, totalItem));
	     }
	    
	    
	    Report report = new Report("faturamento/produtosVendidos");
	    report.setDataSource(listaBeans);
	    report.addParameter("DATA_DE_HOJE", new SimpleDateFormat("dd/MM/yyyy HH:mm").format(new Timestamp(System.currentTimeMillis())));
	    
	    return report;
	}
}
