package br.com.arnia.projetoarnia.faturamento.service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import br.com.arnia.projetoarnia.faturamento.bean.FormaPagamento;
import br.com.arnia.projetoarnia.faturamento.bean.Vendedor;
import br.com.arnia.projetoarnia.faturamento.dao.VendedorDAO;
import br.com.arnia.projetoarnia.report.bean.VendedorBean;
import br.com.arnia.projetoarnia.report.filter.VendedorFiltro;
import br.com.linkcom.neo.report.IReport;
import br.com.linkcom.neo.report.Report;
import br.com.linkcom.neo.service.GenericService;

public class VendedorService extends GenericService<Vendedor>{
	
	private VendedorDAO vendedorDAO;
	
	public void setVendedorDAO(VendedorDAO vendedorDAO) {
		this.vendedorDAO = vendedorDAO;
	}

	public List<Vendedor> findForReport(VendedorFiltro filtro){
		return vendedorDAO.findForReport(filtro);
	}
	
	public IReport createReport(VendedorFiltro filtro) {
		List<Vendedor> listaVendedor = this.findForReport(filtro);
		List<VendedorBean> listaVendedorBean = new ArrayList<VendedorBean>();
		for (Vendedor vendedor : listaVendedor) {
			listaVendedorBean.add(new VendedorBean(vendedor));
		}
		Report report = new Report("/faturamento/vendedor");
		report.setDataSource(listaVendedorBean);
		report.addParameter("DATA_DE_HOJE", new SimpleDateFormat("dd/MM/yyyy HH:mm").format(new Timestamp(System.currentTimeMillis())));
		return report;
	}
	
	public Vendedor findById(Integer id) {
		return vendedorDAO.findById(id);
	}

}
