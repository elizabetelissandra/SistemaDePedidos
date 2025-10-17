package br.com.arnia.projetoarnia.faturamento.service;

import java.util.List;

import br.com.arnia.projetoarnia.faturamento.bean.FormaPagamento;
import br.com.arnia.projetoarnia.faturamento.dao.FormaPagamentoDAO;
import br.com.linkcom.neo.service.GenericService;

public class FormaPagamentoService extends GenericService<FormaPagamento>{

	private FormaPagamentoDAO formaPagamentoDAO;
	
	public void setFormaPagamentoDAO(FormaPagamentoDAO formaPagamentoDAO) {
		this.formaPagamentoDAO = formaPagamentoDAO;
	}

	
	public List<FormaPagamento> listarTodas(){
		return formaPagamentoDAO.listarTodas();
	}
	
	public FormaPagamento findById(Integer id) {
		return formaPagamentoDAO.findById(id);
	}
}
