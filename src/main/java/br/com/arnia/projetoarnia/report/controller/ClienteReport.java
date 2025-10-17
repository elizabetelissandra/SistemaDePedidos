package br.com.arnia.projetoarnia.report.controller;

import br.com.arnia.projetoarnia.report.filter.ClienteFiltro;
import br.com.arnia.projetoarnia.sistema.service.ClienteService;
import br.com.linkcom.neo.controller.Controller;
import br.com.linkcom.neo.controller.resource.ReportController;
import br.com.linkcom.neo.core.web.WebRequestContext;
import br.com.linkcom.neo.report.IReport;

@Controller(path = "/sistema/relatorio/cliente")
public class ClienteReport extends ReportController<ClienteFiltro>{
	
	private ClienteService clienteService;
	
	public void setClienteService(ClienteService clienteService) {
		this.clienteService = clienteService;
	}

	@Override
	public IReport createReport(WebRequestContext request, ClienteFiltro filtro) throws Exception {
		return clienteService.createReport(filtro);
	}

}
