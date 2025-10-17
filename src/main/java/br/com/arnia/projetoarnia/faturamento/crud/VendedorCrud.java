package br.com.arnia.projetoarnia.faturamento.crud;

import br.com.arnia.projetoarnia.faturamento.bean.Vendedor;
import br.com.arnia.projetoarnia.faturamento.service.VendedorService;
import br.com.arnia.projetoarnia.filtro.VendedorFiltro;
import br.com.linkcom.neo.controller.Controller;
import br.com.linkcom.neo.controller.crud.CrudController;

@Controller(path = "/faturamento/crud/Vendedor")
public class VendedorCrud extends CrudController<VendedorFiltro, Vendedor, Vendedor>{
	
	private VendedorService vendedorService;
	
	public void setVendedorService(VendedorService vendedorService) {
		this.vendedorService = vendedorService;
	}
	
	

}
