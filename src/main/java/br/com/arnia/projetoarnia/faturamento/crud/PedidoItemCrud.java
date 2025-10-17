package br.com.arnia.projetoarnia.faturamento.crud;

import br.com.arnia.projetoarnia.faturamento.bean.PedidoItem;
import br.com.arnia.projetoarnia.faturamento.service.PedidoItemService;
import br.com.arnia.projetoarnia.filtro.PedidoFiltro;
import br.com.linkcom.neo.controller.Controller;
import br.com.linkcom.neo.controller.crud.CrudController;

@Controller(path = "/faturamento/crud/PedidoItem")
public class PedidoItemCrud extends CrudController<PedidoFiltro, PedidoItem, PedidoItem>{

	PedidoItemService itemService;
	
	public void setItemService(PedidoItemService itemService) {
		this.itemService = itemService;
	}
}
