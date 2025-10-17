package br.com.arnia.projetoarnia.sistema.service;

import java.util.List;

import br.com.arnia.projetoarnia.sistema.bean.Endereco;
import br.com.arnia.projetoarnia.sistema.dao.EnderecoDAO;
import br.com.linkcom.neo.service.GenericService;

public class EnderecoService extends GenericService<Endereco>{
	
	private EnderecoDAO enderecoDAO;
	
	public void setEnderecoDAO(EnderecoDAO enderecoDAO) {
		this.enderecoDAO = enderecoDAO;
	}
	
	public List<Endereco> listarPorCliente(Integer idCliente) {
        return enderecoDAO.findByCliente(idCliente);
    }
	
	 public List<Endereco> findEnderecosByClienteId(Integer id) {
		return enderecoDAO.findEnderecosByClienteId(id);
	}

	public Endereco findById(Integer id) {
		return enderecoDAO.findById(id);
	}

}
