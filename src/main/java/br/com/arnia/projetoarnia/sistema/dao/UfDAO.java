package br.com.arnia.projetoarnia.sistema.dao;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import br.com.arnia.projetoarnia.filtro.UfFiltro;
import br.com.arnia.projetoarnia.sistema.bean.Uf;
import br.com.linkcom.neo.controller.crud.FiltroListagem;
import br.com.linkcom.neo.persistence.GenericDAO;
import br.com.linkcom.neo.persistence.QueryBuilder;

public class UfDAO extends GenericDAO<Uf>{

	@Override
	public void updateListagemQuery(QueryBuilder<Uf> query, FiltroListagem _filtro) {
		UfFiltro filtro = (UfFiltro) _filtro;
		if(filtro.getSigla() != null && !filtro.getSigla().trim().isEmpty()) {
			query.where("LOWER(municipio.nome) LIKE ?", "%" + filtro.getSigla().toLowerCase() + "%");
		}
	}
	
	public Uf findBySigla(String sigla) {
	    List<Uf> lista = query()
	        .where("uf.sigla = ?", sigla)
	        .list();
	    return lista.isEmpty() ? null : lista.get(0);
	}


    public Uf findOrCreateBySigla(String sigla) {
        Uf uf = findBySigla(sigla);
        if (uf == null) {
            uf = new Uf();
            uf.setSigla(sigla);
            saveOrUpdate(uf); 
        }
        return uf;
    }

	
	

}
