package br.com.arnia.projetoarnia.faturamento.crud;



import br.com.arnia.projetoarnia.faturamento.bean.Pedido;

import br.com.arnia.projetoarnia.faturamento.service.PedidoService;
import br.com.arnia.projetoarnia.filtro.PedidoFiltro;

import br.com.linkcom.neo.controller.Controller;
import br.com.linkcom.neo.controller.crud.CrudController;


@Controller(path = "/faturamento/crud/Pedido")
public class PedidoCrud extends CrudController<PedidoFiltro, Pedido, Pedido> {

	private PedidoService pedidoService;

	public void setPedidoService(PedidoService pedidoService) {
		this.pedidoService = pedidoService;
	}
	
}
