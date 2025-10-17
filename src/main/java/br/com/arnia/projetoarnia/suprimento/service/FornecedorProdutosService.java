package br.com.arnia.projetoarnia.suprimento.service;

import br.com.arnia.projetoarnia.suprimento.dao.FornecedorProdutosDAO;
import br.com.linkcom.neo.service.GenericService;

public class FornecedorProdutosService extends GenericService<FornecedorProdutosService>{

	private FornecedorProdutosDAO fornecedorProdutosDAO;

	public FornecedorProdutosDAO getFornecedorProdutosDAO() {
		return fornecedorProdutosDAO;
	}

	public void setFornecedorProdutosDAO(FornecedorProdutosDAO fornecedorProdutosDAO) {
		this.fornecedorProdutosDAO = fornecedorProdutosDAO;
	}
	
	
}
