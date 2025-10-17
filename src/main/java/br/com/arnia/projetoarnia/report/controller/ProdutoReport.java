package br.com.arnia.projetoarnia.report.controller;

import br.com.arnia.projetoarnia.report.filter.ProdutoFiltro;
import br.com.arnia.projetoarnia.suprimento.service.ProdutosService;
import br.com.linkcom.neo.controller.Controller;
import br.com.linkcom.neo.controller.resource.ReportController;
import br.com.linkcom.neo.core.web.WebRequestContext;
import br.com.linkcom.neo.report.IReport;

@Controller(path = "/suprimentos/relatorio/produtos")
public class ProdutoReport extends ReportController<ProdutoFiltro>{
	private ProdutosService produtosService;
	
	
	public void setProdutosService(ProdutosService produtosService) {
		this.produtosService = produtosService;
	}


	@Override
	public IReport createReport(WebRequestContext request, ProdutoFiltro filtro) throws Exception {
		return produtosService.createReport(filtro);
	}


	

}
