package br.com.arnia.projetoarnia.sistema.dao;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import br.com.arnia.projetoarnia.filtro.MunicipioFiltro;
import br.com.arnia.projetoarnia.sistema.bean.Municipio;
import br.com.arnia.projetoarnia.sistema.bean.Uf;
import br.com.linkcom.neo.controller.crud.FiltroListagem;
import br.com.linkcom.neo.persistence.GenericDAO;
import br.com.linkcom.neo.persistence.QueryBuilder;

public class MunicipioDAO extends GenericDAO<Municipio>{

	@Override
	public void updateListagemQuery(QueryBuilder<Municipio> query, FiltroListagem _filtro) {
		MunicipioFiltro filtro = (MunicipioFiltro) _filtro;
		if(filtro.getNome() != null && !filtro.getNome().trim().isEmpty()) {
			query.where("LOWER(municipio.nome) LIKE ?", "%" + filtro.getNome().toLowerCase() + "%");
		}
	}
	
	public List<Municipio> findAtivos() {
		return query().where("municipio.ativo is true").list();
	}
	
	public Municipio findByNomeAndUf(String nome, Uf uf) {
	    List<Municipio> lista = query()
	        .where("municipio.nome = ?", nome)
	        .where("municipio.uf = ?", uf)
	        .list();
	    return lista.isEmpty() ? null : lista.get(0);
	}

	 public Municipio findOrCreateByNomeAndUf(String nome, Uf uf) {
	        Municipio municipio = findByNomeAndUf(nome, uf);
	        if (municipio == null) {
	            municipio = new Municipio();
	            municipio.setNome(nome);
	            municipio.setUf(uf);
	            saveOrUpdate(municipio);
	        }
	        return municipio;
	    }

}


