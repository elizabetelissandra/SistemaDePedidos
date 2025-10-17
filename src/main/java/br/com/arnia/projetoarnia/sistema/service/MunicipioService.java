package br.com.arnia.projetoarnia.sistema.service;

import br.com.arnia.projetoarnia.sistema.bean.Municipio;
import br.com.arnia.projetoarnia.sistema.bean.Uf;
import br.com.arnia.projetoarnia.sistema.dao.MunicipioDAO;
import br.com.linkcom.neo.service.GenericService;

public class MunicipioService extends GenericService<Municipio>{
	
	private MunicipioDAO municipioDAO;
	
	public void setMunicipioDAO(MunicipioDAO municipioDAO) {
		this.municipioDAO = municipioDAO;
	}
	
	public Municipio findByNomeAndUf(String nome, Uf uf) {
        return municipioDAO.findByNomeAndUf(nome, uf);
    }

    public Municipio findOrCreateByNomeAndUf(String nome, Uf uf) {
        return municipioDAO.findOrCreateByNomeAndUf(nome, uf);
    }

}
