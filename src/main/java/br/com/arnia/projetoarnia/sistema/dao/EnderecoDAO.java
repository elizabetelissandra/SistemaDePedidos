package br.com.arnia.projetoarnia.sistema.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.arnia.projetoarnia.sistema.bean.Endereco;
import br.com.linkcom.neo.controller.crud.FiltroListagem;
import br.com.linkcom.neo.persistence.GenericDAO;
import br.com.linkcom.neo.persistence.QueryBuilder;

public class EnderecoDAO extends GenericDAO<Endereco>{
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public void updateListagemQuery(QueryBuilder<Endereco> query, FiltroListagem _filtro) {
		query.select("endereco")
		.leftOuterJoin("endereco.uf uf")
		.leftOuterJoin("endereco.cliente Cliente")
		.leftOuterJoin("endereco.municipio Municipio");
	}
	
	@Override
	public void updateEntradaQuery(QueryBuilder<Endereco> query) {
		query.select("endereco")
		.leftOuterJoin("endereco.uf uf")
		.leftOuterJoin("endereco.cliente Cliente")
		.leftOuterJoin("endereco.municipio Municipio");;
	}
	
	@SuppressWarnings("unchecked")
    public List<Endereco> findEnderecosByClienteId(Integer clienteId) {
        return query().where("endereco.cliente.cdCliente = ?", clienteId).list();
    }
	
	public Endereco findById(Integer id) {
		if (id == null) return null;
		return (Endereco) getSession().get(Endereco.class, id);
	}

	public List<Endereco> findByCliente(Integer idCliente) {
	    
	    if (idCliente == null) {
	        return new java.util.ArrayList<Endereco>();
	    }
	    return query()
	        .where("endereco.cliente.cdCliente = ?", idCliente)
	        .list();
	}
	
	


}
