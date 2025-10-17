package br.com.arnia.projetoarnia.suprimento.crud;

import br.com.arnia.projetoarnia.filtro.FornecedorProdutosFiltro;
import br.com.arnia.projetoarnia.suprimento.bean.FornecedorProdutos;
import br.com.arnia.projetoarnia.suprimento.service.FornecedorProdutosService;
import br.com.linkcom.neo.controller.Controller;
import br.com.linkcom.neo.controller.crud.CrudController;

@Controller(path = "/suprimentos/crud/FornecedorProdutos")
public class FornecedorProdutosCrud extends CrudController<FornecedorProdutosFiltro, FornecedorProdutos, FornecedorProdutos>{

	private FornecedorProdutosService fornecedorProdutosService;

	public FornecedorProdutosService getFornecedorProdutosService() {
		return fornecedorProdutosService;
	}

	public void setFornecedorProdutosService(FornecedorProdutosService fornecedorProdutosService) {
		this.fornecedorProdutosService = fornecedorProdutosService;
	}
	
	
}
