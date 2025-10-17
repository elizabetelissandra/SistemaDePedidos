package br.com.arnia.projetoarnia.sistema.service;

import br.com.arnia.projetoarnia.sistema.bean.Usuario;
import br.com.arnia.projetoarnia.sistema.dao.UsuarioDAO;
import br.com.linkcom.neo.service.GenericService;

public class UsuarioService extends GenericService<Usuario>{
	
	private UsuarioDAO usuarioDAO;

	public void setUsuarioDAO(UsuarioDAO usuarioDAO) {
		this.usuarioDAO = usuarioDAO;
	}

}
