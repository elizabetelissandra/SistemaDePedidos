package br.com.arnia.projetoarnia.suprimento.service;

import java.util.ArrayList;
import java.util.List;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import br.com.arnia.projetoarnia.faturamento.bean.Pedido;
import br.com.arnia.projetoarnia.report.bean.ProdutoBean;
import br.com.arnia.projetoarnia.report.filter.ProdutoFiltro;
import br.com.arnia.projetoarnia.suprimento.bean.Produtos;
import br.com.arnia.projetoarnia.suprimento.dao.ProdutosDAO;
import br.com.linkcom.neo.report.IReport;
import br.com.linkcom.neo.report.Report;
import br.com.linkcom.neo.service.GenericService;

public class ProdutosService extends GenericService<Produtos> {

	private ProdutosDAO produtosDAO;

	public void setProdutosDAO(ProdutosDAO produtosDAO) {
		this.produtosDAO = produtosDAO;
	}

	public List<Produtos> findForReport(ProdutoFiltro filtro) {
		return produtosDAO.findForReport(filtro);
	}

	
	
	public IReport createReport(ProdutoFiltro filtro) {
		List<Produtos> listaProduto = this.findForReport(filtro);
		List<ProdutoBean> listaProdutoBean = new ArrayList<ProdutoBean>();
		for (Produtos prod : listaProduto) {
			listaProdutoBean.add(new ProdutoBean(prod));
		}

		Report report = new Report("suprimentos/produtos");
		report.setDataSource(listaProdutoBean);
		report.addParameter("DATA_DE_HOJE",
				new SimpleDateFormat("dd/MM/yyyy HH:mm").format(new Timestamp(System.currentTimeMillis())));
		return report;
	}

	public List<Produtos> listarTodos(){
		return produtosDAO.listarTodos();
	}
}
