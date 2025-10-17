package br.com.arnia.projetoarnia.suprimento.dao;

import java.util.List;


import br.com.arnia.projetoarnia.filtro.UnidadeMedidaFiltro;
import br.com.arnia.projetoarnia.suprimento.bean.UnidadeMedida;
import br.com.linkcom.neo.controller.crud.FiltroListagem;
import br.com.linkcom.neo.persistence.GenericDAO;
import br.com.linkcom.neo.persistence.QueryBuilder;

public class UnidadeMedidaDAO extends GenericDAO<UnidadeMedida>{
	@Override
	public void updateListagemQuery(QueryBuilder<UnidadeMedida> query, FiltroListagem _filtro) {
		
		UnidadeMedidaFiltro unidadeFiltro = (UnidadeMedidaFiltro)_filtro;
		
		query.select(
			    "unidadeMedida.cdUnidadeMedida, unidadeMedida.nome, unidadeMedida.sigla, unidadeMedida.ativo, " +
			    "produtos.cdProduto, produtos.nome, produtos.codigoBarras, produtos.preco"
			)
			.leftOuterJoin("unidadeMedida.produtos produtos");
		
		if(unidadeFiltro.getAtivo() != null) {
			query.where("unidadeMedida.ativo = ?", unidadeFiltro.getAtivo());
		}
	}
	
	@Override
	public void updateEntradaQuery(QueryBuilder<UnidadeMedida> query) {
		
		query.select(
			    "unidadeMedida.cdUnidadeMedida, unidadeMedida.nome, unidadeMedida.sigla, unidadeMedida.ativo, " +
			    "produtos.cdProduto, produtos.nome, produtos.codigoBarras, produtos.preco"
			)
			.leftOuterJoin("unidadeMedida.produtos produtos");
	}
	
	 public List<UnidadeMedida> findByAtivo() {
	        return query()
	                .where("unidadeMedida.ativo is true")
	                .list();
	    }
}
