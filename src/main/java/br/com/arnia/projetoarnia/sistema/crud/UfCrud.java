package br.com.arnia.projetoarnia.sistema.crud;

import br.com.arnia.projetoarnia.filtro.UfFiltro;
import br.com.arnia.projetoarnia.sistema.bean.Uf;
import br.com.arnia.projetoarnia.sistema.service.UfService;
import br.com.linkcom.neo.controller.crud.CrudController;

public class UfCrud extends CrudController<UfFiltro, Uf, Uf>{
	
	private UfService ufService;
	
	public void setUfService(UfService ufService) {
		this.ufService = ufService;
	}

}
