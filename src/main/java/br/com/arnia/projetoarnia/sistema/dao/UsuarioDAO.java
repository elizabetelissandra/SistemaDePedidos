package br.com.arnia.projetoarnia.sistema.dao;

import java.util.List;

import br.com.arnia.projetoarnia.sistema.bean.Usuario;
import br.com.linkcom.neo.controller.crud.FiltroListagem;
import br.com.linkcom.neo.persistence.GenericDAO;
import br.com.linkcom.neo.persistence.QueryBuilder;


public class UsuarioDAO extends GenericDAO<Usuario>{

	@Override
	public void updateListagemQuery(QueryBuilder<Usuario> query, FiltroListagem _filtro) {
		query().select("usuario");
	}
	
	public Usuario findByLogin(String login) {
		List<Usuario> lista = query()
		        .where("usuario.login = ?", login)
		        .list();
		    return lista.isEmpty() ? null : lista.get(0);
	}
}
