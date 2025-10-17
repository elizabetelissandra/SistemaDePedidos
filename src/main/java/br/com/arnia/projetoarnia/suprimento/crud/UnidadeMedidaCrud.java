package br.com.arnia.projetoarnia.suprimento.crud;


import br.com.arnia.projetoarnia.filtro.UnidadeMedidaFiltro;
import br.com.arnia.projetoarnia.suprimento.bean.UnidadeMedida;
import br.com.arnia.projetoarnia.suprimento.service.UnidadeMedidaService;
import br.com.linkcom.neo.controller.Controller;
import br.com.linkcom.neo.controller.crud.CrudController;

@Controller(path = "/suprimentos/crud/Unidades")
public class UnidadeMedidaCrud extends CrudController<UnidadeMedidaFiltro, UnidadeMedida, UnidadeMedida> {
	
	private UnidadeMedidaService unidadeMedidaService;

	public UnidadeMedidaService getUnidadeMedidaService() {
		return unidadeMedidaService;
	}

	public void setUnidadeMedidaService(UnidadeMedidaService unidadeMedidaService) {
		this.unidadeMedidaService = unidadeMedidaService;
	}
	
}
