package br.com.arnia.projetoarnia.faturamento.dao;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.arnia.projetoarnia.faturamento.bean.FormaPagamento;
import br.com.arnia.projetoarnia.filtro.FormaPagamentoFiltro;
import br.com.arnia.projetoarnia.sistema.bean.Cliente;
import br.com.linkcom.neo.controller.crud.FiltroListagem;
import br.com.linkcom.neo.persistence.GenericDAO;
import br.com.linkcom.neo.persistence.QueryBuilder;

public class FormaPagamentoDAO extends GenericDAO<FormaPagamento>{
	
	
	@Override
	public void updateListagemQuery(QueryBuilder<FormaPagamento> query, FiltroListagem _filtro) {
		FormaPagamentoFiltro filtro = (FormaPagamentoFiltro) _filtro;
		
		
		query.select("formaPagamento");
		
	
		if (filtro.getNome() != null && !filtro.getNome().trim().isEmpty()) {
			query.where("lower(formaPagamento.nome) LIKE ?", "%" + filtro.getNome().toLowerCase() + "%");
		}
		query.orderBy("formaPagamento.nome");
	}
	

	@Override
	public void updateEntradaQuery(QueryBuilder<FormaPagamento> query) {
	
	}
    
	public List<FormaPagamento> listarTodas() {
	    return query()
	        .orderBy("formaPagamento.nome")
	        .list();
	}
	
	public FormaPagamento findById(Integer id) {
 	    if (id == null) return null;

 	    return (FormaPagamento) getSession().get(FormaPagamento.class, id);
 	} 
	
	





}
