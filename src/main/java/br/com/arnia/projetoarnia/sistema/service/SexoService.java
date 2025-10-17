package br.com.arnia.projetoarnia.sistema.service;

import br.com.arnia.projetoarnia.sistema.bean.Sexo;
import br.com.arnia.projetoarnia.sistema.dao.SexoDAO;
import br.com.linkcom.neo.service.GenericService;

public class SexoService extends GenericService<Sexo>{
	
	private SexoDAO sexoDAO;
	
	public void setSexoDAO(SexoDAO sexoDAO) {
		this.sexoDAO = sexoDAO;
	}
}
