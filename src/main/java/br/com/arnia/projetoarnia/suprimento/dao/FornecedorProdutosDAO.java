package br.com.arnia.projetoarnia.suprimento.dao;

import br.com.linkcom.neo.controller.crud.FiltroListagem;
import br.com.linkcom.neo.persistence.GenericDAO;
import br.com.linkcom.neo.persistence.QueryBuilder;

public class FornecedorProdutosDAO extends GenericDAO<FornecedorProdutosDAO>{

	@Override
	public void updateListagemQuery(QueryBuilder<FornecedorProdutosDAO> query, FiltroListagem _filtro) {
		query.select(
			    "fornecedorProdutos.cdfornecedorproduto, " +
			    "fornecedorProdutos.tempoentrega, fornecedorProdutos.principal, fornecedorProdutos.fabricante, " +
			    "fornecedor.cdfornecedor, fornecedor.nome, fornecedor.cnpj, " +
			    "produto.cdProduto, produto.nome, produto.codigoBarras, produto.preco"
			)
			.leftOuterJoin("fornecedorProdutos.fornecedor fornecedor")
			.leftOuterJoin("fornecedorProdutos.produto produto");
	}
	
	@Override
	public void updateEntradaQuery(QueryBuilder<FornecedorProdutosDAO> query) {
		query.select(
			    "fornecedorProdutos.cdfornecedorproduto, " +
			    "fornecedorProdutos.tempoentrega, fornecedorProdutos.principal, fornecedorProdutos.fabricante, " +
			    "fornecedor.cdfornecedor, fornecedor.nome, fornecedor.cnpj, " +
			    "produto.cdProduto, produto.nome, produto.codigoBarras, produto.preco"
			)
			.leftOuterJoin("fornecedorProdutos.fornecedor fornecedor")
			.leftOuterJoin("fornecedorProdutos.produto produto");
	}
}
