package br.com.arnia.projetoarnia.suprimento.service;

import java.util.List;

import br.com.arnia.projetoarnia.suprimento.bean.UnidadeMedida;
import br.com.arnia.projetoarnia.suprimento.dao.UnidadeMedidaDAO;
import br.com.linkcom.neo.service.GenericService;

public class UnidadeMedidaService extends GenericService<UnidadeMedida> {

	private UnidadeMedidaDAO unidadeMedidaDAO;

	public void setUnidadeMedidaDAO(UnidadeMedidaDAO unidadeMedidaDAO) {
		this.unidadeMedidaDAO = unidadeMedidaDAO;
	}
	
	 public List<UnidadeMedida> findAtivos() {
	        return unidadeMedidaDAO.findByAtivo();
	    }

}
