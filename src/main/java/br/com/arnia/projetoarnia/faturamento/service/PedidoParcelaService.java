package br.com.arnia.projetoarnia.faturamento.service;

import br.com.arnia.projetoarnia.faturamento.bean.PedidoParcela;
import br.com.arnia.projetoarnia.faturamento.dao.PedidoParcelaDAO;
import br.com.linkcom.neo.service.GenericService;

public class PedidoParcelaService extends GenericService<PedidoParcela>{

	private PedidoParcelaDAO pedidoParcelaDAO;

	public void setPedidoParcelaDAO(PedidoParcelaDAO pedidoParcelaDAO) {
		this.pedidoParcelaDAO = pedidoParcelaDAO;
	}
	
	
	
}
