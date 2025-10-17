package br.com.arnia.projetoarnia.suprimento.service;

import br.com.arnia.projetoarnia.suprimento.bean.Fornecedor;
import br.com.arnia.projetoarnia.suprimento.crud.FornecedorCrud;
import br.com.arnia.projetoarnia.suprimento.dao.FornecedorDAO;
import br.com.linkcom.neo.service.GenericService;

public class FornecedorService extends GenericService<Fornecedor>{
	
	private FornecedorDAO fornecedorDAO;


	public void setFornecedorDAO(FornecedorDAO fornecedorDAO) {
		this.fornecedorDAO = fornecedorDAO;
	}
	
	

}
