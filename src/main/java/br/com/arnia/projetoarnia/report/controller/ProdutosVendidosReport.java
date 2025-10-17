package br.com.arnia.projetoarnia.report.controller;

import br.com.arnia.projetoarnia.faturamento.service.PedidoService;
import br.com.arnia.projetoarnia.report.filter.ProdutoVendidoFiltro;
import br.com.linkcom.neo.controller.Controller;
import br.com.linkcom.neo.controller.resource.ReportController;
import br.com.linkcom.neo.core.web.WebRequestContext;
import br.com.linkcom.neo.report.IReport;

@Controller(path = "/faturamento/relatorio/produtosVendidos")
public class ProdutosVendidosReport extends ReportController<ProdutoVendidoFiltro>{

	
	private PedidoService pedidoService;
	
	public void setPedidoService(PedidoService pedidoService) {
		this.pedidoService = pedidoService;
	}

	@Override
	public IReport createReport(WebRequestContext arg0, ProdutoVendidoFiltro filtro) throws Exception {
		return pedidoService.createReportProdutosVendidos(filtro);
	}
}
