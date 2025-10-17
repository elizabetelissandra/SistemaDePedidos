package br.com.arnia.projetoarnia.sistema.service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import br.com.arnia.projetoarnia.report.bean.ClienteBean;
import br.com.arnia.projetoarnia.report.filter.ClienteFiltro;
import br.com.arnia.projetoarnia.sistema.bean.Cliente;
import br.com.arnia.projetoarnia.sistema.bean.Endereco;
import br.com.arnia.projetoarnia.sistema.bean.Municipio;
import br.com.arnia.projetoarnia.sistema.bean.Uf;
import br.com.arnia.projetoarnia.sistema.dao.ClienteDAO;
import br.com.arnia.projetoarnia.sistema.dao.MunicipioDAO;
import br.com.arnia.projetoarnia.sistema.dao.UfDAO;
import br.com.linkcom.neo.report.IReport;
import br.com.linkcom.neo.report.Report;
import br.com.linkcom.neo.service.GenericService;

public class ClienteService extends GenericService<Cliente>{

	private ClienteDAO clienteDAO;
	 private UfDAO ufDAO;         
	 private MunicipioDAO municipioDAO;
	
	public void setClienteDAO(ClienteDAO clienteDAO) {
		this.clienteDAO = clienteDAO;
	}
	
	public List<Cliente> findForReport(ClienteFiltro filtro){
		return clienteDAO.findForReport(filtro);
	}
	
	public IReport createReport(ClienteFiltro filtro) {
		List<Cliente> listaCliente = this.findForReport(filtro);
		List<ClienteBean> listaClienteBeans = new ArrayList<ClienteBean>();
		for (Cliente cliente : listaCliente) {
			listaClienteBeans.add(new ClienteBean(cliente));
		}
		Report report = new Report("sistema/cliente");
		report.setDataSource(listaClienteBeans);
		report.addParameter("DATA_DE_HOJE", new SimpleDateFormat("dd/MM/yyyy HH:mm").format(new Timestamp(System.currentTimeMillis())));
		return report;
	}

	  
	  public Cliente findById(Integer id) {
		  return clienteDAO.findById(id);
	  }
	  
	  
		

}
