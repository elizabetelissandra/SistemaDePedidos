package br.com.arnia.projetoarnia.faturamento.dao;

import java.util.List;

import br.com.arnia.projetoarnia.faturamento.bean.Pedido;
import br.com.arnia.projetoarnia.faturamento.bean.PedidoItem;
import br.com.arnia.projetoarnia.report.filter.ProdutoVendidoFiltro;
import br.com.linkcom.neo.controller.crud.FiltroListagem;
import br.com.linkcom.neo.persistence.GenericDAO;
import br.com.linkcom.neo.persistence.QueryBuilder;

public class PedidoItemDAO extends GenericDAO<PedidoItem> {
	
	

	@Override
	public void updateListagemQuery(QueryBuilder<PedidoItem> query, FiltroListagem _filtro) {
		 query.select(
			       "pedidoItem.cdpedidoitem, pedidoItem.preco, pedidoItem.quantidade, "
			       + "pedidoItem.descontounitario, pedido.cdpedido, produto.cdProduto, produto.nome")
		 .leftOuterJoin("pedidoItem.cdproduto produto").leftOuterJoin("pedidoItem.cdpedido pedido");
	}

	@Override
	public void updateEntradaQuery(QueryBuilder<PedidoItem> query) {
		query.select(
			       "pedidoItem.cdpedidoitem, pedidoItem.preco, pedidoItem.quantidade, "
			       + "pedidoItem.descontounitario, pedido.cdpedido, produto.cdProduto, produto.nome")
		 .leftOuterJoin("pedidoItem.cdproduto produto")
		 .leftOuterJoin("pedidoItem.cdpedido pedido");
	}
	
	public List<PedidoItem> findProdutosVendidosForReport(ProdutoVendidoFiltro filtro) {

	    QueryBuilder<PedidoItem> query = query();
	    query.select(
	        "cliente.nome, " +
	        "pedido.datapedido, " +
	        "produto.nome, " +
	        "pedidoItem.preco, " +
	        "pedidoItem.quantidade");

	    query.join("pedidoItem.cdpedido pedido")
	         .join("pedido.cdcliente cliente")
	         .join("pedidoItem.cdproduto produto");

	
	    query.where("pedido.datapedido >= ?", filtro.getDataInicial());
	    query.where("pedido.datapedido <= ?", filtro.getDataFinal());

	    if (filtro.getCliente() != null && filtro.getCliente().getCdCliente() != null) {
	        query.where("cliente.cdCliente = ?", filtro.getCliente().getCdCliente());
	    }

	    query.orderBy("cliente.nome, pedido.datapedido");

	   
	    return query.list();
	}
}
