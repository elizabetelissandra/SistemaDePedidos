package br.com.arnia.projetoarnia.suprimento.dao;

import br.com.arnia.projetoarnia.filtro.FornecedorFiltro;
import br.com.arnia.projetoarnia.suprimento.bean.Fornecedor;
import br.com.linkcom.neo.controller.crud.FiltroListagem;
import br.com.linkcom.neo.persistence.GenericDAO;
import br.com.linkcom.neo.persistence.QueryBuilder;
import br.com.linkcom.neo.persistence.SaveOrUpdateStrategy;

public class FornecedorDAO extends GenericDAO<Fornecedor> {

	@Override
	public void updateListagemQuery(QueryBuilder<Fornecedor> query, FiltroListagem _filtro) {
		FornecedorFiltro filtro = (FornecedorFiltro) _filtro;

		query.select(
			    "fornecedor.cdfornecedor, fornecedor.nome, fornecedorProdutos.cdfornecedorproduto, fornecedorProdutos.tempoentrega, fornecedorProdutos.fabricante, produto.cdProduto, produto.nome"
			)
			.leftOuterJoin("fornecedor.fornecedorProdutos fornecedorProdutos")
			.leftOuterJoin("fornecedorProdutos.produto produto");
		if (filtro.getNome() != null && !filtro.getNome().trim().isEmpty()) {
			query.where("LOWER(fornecedor.nome) LIKE ?", "%" + filtro.getNome().toLowerCase() + "%");
		}
	}

	@Override
	public void updateEntradaQuery(QueryBuilder<Fornecedor> query) {
		query.select(
			    "fornecedor.cdfornecedor, fornecedor.nome, fornecedorProdutos.cdfornecedorproduto, fornecedorProdutos.tempoentrega, fornecedorProdutos.fabricante, produto.cdProduto, produto.nome"
			)
			.leftOuterJoin("fornecedor.fornecedorProdutos fornecedorProdutos")
			.leftOuterJoin("fornecedorProdutos.produto produto");
	}

	@Override
	public void updateSaveOrUpdate(SaveOrUpdateStrategy save) {
		save.saveOrUpdateManaged("fornecedorProdutos");
		super.updateSaveOrUpdate(save);
	}

}
