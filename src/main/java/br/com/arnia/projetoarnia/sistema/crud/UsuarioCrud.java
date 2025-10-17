package br.com.arnia.projetoarnia.sistema.crud;

import br.com.arnia.projetoarnia.filtro.UsuarioFiltro;
import br.com.arnia.projetoarnia.sistema.bean.Usuario;
import br.com.arnia.projetoarnia.sistema.service.UsuarioService;
import br.com.linkcom.neo.controller.Controller;
import br.com.linkcom.neo.controller.crud.CrudController;

@Controller(path = "/sistema/crud/Usuario")
public class UsuarioCrud extends CrudController<UsuarioFiltro, Usuario, Usuario>{
	
	private UsuarioService usuarioService;

	public void setUsuarioService(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}
	
	

}
