package br.com.arnia.projetoarnia.faturamento.dao;

import java.util.List;

import br.com.arnia.projetoarnia.faturamento.bean.FormaPagamento;
import br.com.arnia.projetoarnia.faturamento.bean.Vendedor;
import br.com.arnia.projetoarnia.filtro.VendedorFiltro;
import br.com.linkcom.neo.controller.crud.FiltroListagem;
import br.com.linkcom.neo.persistence.GenericDAO;
import br.com.linkcom.neo.persistence.QueryBuilder;

public class VendedorDAO extends GenericDAO<Vendedor> {

	@Override
	public void updateListagemQuery(QueryBuilder<Vendedor> query, FiltroListagem _filtro) {
		VendedorFiltro filtro = (VendedorFiltro) _filtro;
		if (filtro.getNome() != null && !filtro.getNome().trim().isEmpty()) {
			query.where("LOWER(vendedor.nome) LIKE ?", "%" + filtro.getNome().toLowerCase() + "%");
		}
	}

	public List<Vendedor> findAtivos() {
		return query().where("vendedor.ativo is true").list();
	}

	public List<Vendedor> findForReport(br.com.arnia.projetoarnia.report.filter.VendedorFiltro filtro) {
		return query().select("vendedor.nome, vendedor.cpf")
				.where("vendedor.nome = ?", filtro.getNome())
				.list();
	}
	
	public Vendedor findById(Integer id) {
 	    if (id == null) return null;

 	    return (Vendedor) getSession().get(Vendedor.class, id);
 	} 

}
