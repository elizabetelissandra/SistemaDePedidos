package br.com.arnia.projetoarnia.faturamento.dao;

import br.com.arnia.projetoarnia.faturamento.bean.PedidoParcela;
import br.com.linkcom.neo.controller.crud.FiltroListagem;
import br.com.linkcom.neo.persistence.GenericDAO;
import br.com.linkcom.neo.persistence.QueryBuilder;

public class PedidoParcelaDAO extends GenericDAO<PedidoParcela>{

	@Override
	public void updateListagemQuery(QueryBuilder<PedidoParcela> query, FiltroListagem _filtro) {
		query
		.select("pedidoparcela.pedido, pedidoparcela.numeroparcela,"
		+ " pedidoparcela.valorparcela, pedidoparcela.datavencimento,"
		+ " pedido.cdpedido, pedido.qtdparcelas, pedido")
		.leftOuterJoin("pedidoparcela.pedido pedido");
	}
	
	@Override
	public void updateEntradaQuery(QueryBuilder<PedidoParcela> query) {
		query
		.select("pedidoparcela.pedido, pedidoparcela.numeroparcela,"
		+ " pedidoparcela.valorparcela, pedidoparcela.datavencimento,"
		+ " pedido.cdpedido, pedido.qtdparcelas, pedido")
		.leftOuterJoin("pedidoparcela.pedido pedido");
	}
}
