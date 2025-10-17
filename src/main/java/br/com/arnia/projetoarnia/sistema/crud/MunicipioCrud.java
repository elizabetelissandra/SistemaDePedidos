package br.com.arnia.projetoarnia.sistema.crud;

import br.com.arnia.projetoarnia.filtro.MunicipioFiltro;
import br.com.arnia.projetoarnia.sistema.bean.Municipio;
import br.com.arnia.projetoarnia.sistema.service.MunicipioService;
import br.com.linkcom.neo.controller.crud.CrudController;

public class MunicipioCrud extends CrudController<MunicipioFiltro, Municipio, Municipio>{

	private MunicipioService municipioService;
	
	public void setMunicipioService(MunicipioService municipioService) {
		this.municipioService = municipioService;
	}
}
