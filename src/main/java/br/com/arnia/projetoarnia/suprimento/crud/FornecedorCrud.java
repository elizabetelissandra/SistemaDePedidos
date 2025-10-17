package br.com.arnia.projetoarnia.suprimento.crud;

import br.com.arnia.projetoarnia.filtro.FornecedorFiltro;
import br.com.arnia.projetoarnia.suprimento.bean.Fornecedor;
import br.com.arnia.projetoarnia.suprimento.service.FornecedorService;
import br.com.linkcom.neo.controller.Controller;
import br.com.linkcom.neo.controller.crud.CrudController;

@Controller(path = "/suprimentos/crud/Fornecedor")
public class FornecedorCrud extends CrudController<FornecedorFiltro, Fornecedor, Fornecedor>{

	private FornecedorService fornecedorService;

	public void setFornecedorService(FornecedorService fornecedorService) {
		this.fornecedorService = fornecedorService;
	}
	
	
}
