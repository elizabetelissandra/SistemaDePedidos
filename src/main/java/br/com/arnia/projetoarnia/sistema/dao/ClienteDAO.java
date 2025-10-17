package br.com.arnia.projetoarnia.sistema.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.arnia.projetoarnia.filtro.ClienteFiltro;
import br.com.arnia.projetoarnia.sistema.bean.Cliente;
import br.com.arnia.projetoarnia.sistema.bean.Endereco;
import br.com.arnia.projetoarnia.sistema.bean.Municipio;
import br.com.arnia.projetoarnia.sistema.bean.Uf;
import br.com.linkcom.neo.controller.crud.FiltroListagem;
import br.com.linkcom.neo.persistence.GenericDAO;
import br.com.linkcom.neo.persistence.QueryBuilder;
import br.com.linkcom.neo.persistence.SaveOrUpdateStrategy;

public class ClienteDAO extends GenericDAO<Cliente> {
	@Autowired
	private SessionFactory sessionFactory;


	@Override
	public void updateListagemQuery(QueryBuilder<Cliente> query, FiltroListagem _filtro) {
		ClienteFiltro clienteFiltro = (ClienteFiltro) _filtro;

		query.select(
				"cliente.cdCliente, cliente.nome, cliente.tipopessoa, cliente.cpf, cliente.cnpj, cliente.sexo, sexo.cdSexo, sexo.nome")
				.leftOuterJoin("cliente.sexo sexo").leftOuterJoin("cliente.enderecos endereco")
				.leftOuterJoin("endereco.municipio municipio").leftOuterJoin("endereco.uf uf");

		if (clienteFiltro.getNome() != null && !clienteFiltro.getNome().trim().isEmpty()) {
			query.where("LOWER(cliente.nome) LIKE ?", "%" + clienteFiltro.getNome().toLowerCase() + "%");
		}
	

		query.orderBy("cliente.nome");

	}

	@Override
	public void updateEntradaQuery(QueryBuilder<Cliente> query) {
		query.select(
				"cliente.cdCliente, cliente.nome, cliente.tipopessoa, cliente.cpf, cliente.cnpj, cliente.sexo, "
				+ "sexo.cdSexo, sexo.nome, endereco.logradouro, endereco.numero, endereco.cep, municipio.cdmunicipio,"
				+ " municipio.nome, uf.cduf, uf.nome, uf.sigla")
				.leftOuterJoin("cliente.sexo sexo").leftOuterJoin("cliente.enderecos endereco")
				.leftOuterJoin("endereco.municipio municipio").leftOuterJoin("endereco.uf uf");

	}

	public List<Cliente> findAtivos() {
		return query().where("cliente.ativo is true").list();
	}
	
	@Override
	public void updateSaveOrUpdate(SaveOrUpdateStrategy save) {
		save.saveOrUpdateManaged("enderecos");
		super.updateSaveOrUpdate(save);
	}
	
	public List<Cliente> findForReport(br.com.arnia.projetoarnia.report.filter.ClienteFiltro filtro){
		return query().select("cliente.cdCliente, cliente.nome, cliente.tipopessoa, "
				+ "cliente.cpf, cliente.cnpj, cliente.sexo, sexo.cdSexo, sexo.nome")
				.leftOuterJoin("cliente.sexo sexo")
				.where("cliente.nome = ?", filtro.getNome())
				.list();
	}
	 
	 	
	 	
	 	public Cliente findById(Integer id) {
	 	    if (id == null) return null;

	 	    return (Cliente) getSession().get(Cliente.class, id);
	 	}




}
