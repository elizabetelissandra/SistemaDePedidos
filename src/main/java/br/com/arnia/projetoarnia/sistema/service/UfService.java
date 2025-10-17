package br.com.arnia.projetoarnia.sistema.service;

import br.com.arnia.projetoarnia.sistema.bean.Uf;
import br.com.arnia.projetoarnia.sistema.dao.UfDAO;
import br.com.linkcom.neo.service.GenericService;

public class UfService extends GenericService<Uf>{
	
	private UfDAO ufDAO;
	
	public void setUfDAO(UfDAO ufDAO) {
		this.ufDAO = ufDAO;
	}
	
	public Uf findBySigla(String sigla) {
        return ufDAO.findBySigla(sigla);
    }

    public Uf findOrCreateBySigla(String sigla) {
        return ufDAO.findOrCreateBySigla(sigla);
    }

}
