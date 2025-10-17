package br.com.arnia.projetoarnia.report.controller;

import br.com.arnia.projetoarnia.faturamento.service.VendedorService;
import br.com.arnia.projetoarnia.report.filter.VendedorFiltro;
import br.com.linkcom.neo.controller.Controller;
import br.com.linkcom.neo.controller.resource.ReportController;
import br.com.linkcom.neo.core.web.WebRequestContext;
import br.com.linkcom.neo.report.IReport;

@Controller(path = "/faturamento/relatorio/vendedor")
public class VendedorReport extends ReportController<VendedorFiltro>{
	
	private VendedorService vendedorService;
	
	public void setVendedorService(VendedorService vendedorService) {
		this.vendedorService = vendedorService;
	}

	@Override
	public IReport createReport(WebRequestContext arg0, VendedorFiltro filtro) throws Exception {
		return vendedorService.createReport(filtro);
	}

}
