package br.com.arnia.projetoarnia.suprimento.dao;


import java.util.List;

import br.com.arnia.projetoarnia.filtro.ProdutoFiltro;
import br.com.arnia.projetoarnia.sistema.bean.Cliente;
import br.com.arnia.projetoarnia.suprimento.bean.Produtos;
import br.com.linkcom.neo.controller.crud.FiltroListagem;
import br.com.linkcom.neo.persistence.GenericDAO;
import br.com.linkcom.neo.persistence.QueryBuilder;
import br.com.linkcom.neo.persistence.SaveOrUpdateStrategy;

public class ProdutosDAO extends GenericDAO<Produtos>{

	@Override
	public void updateListagemQuery(QueryBuilder<Produtos> query, FiltroListagem _filtro) {
		ProdutoFiltro produtoFiltro = (ProdutoFiltro)_filtro;
		
		query.select(
			    "produtos.cdProduto, produtos.nome, produtos.codigoBarras, produtos.preco, " +
			    "unidadeMedida.cdUnidadeMedida, unidadeMedida.nome, unidadeMedida.sigla, unidadeMedida.ativo, " +
			    "fornecedorProdutos.cdfornecedorproduto, fornecedorProdutos.tempoentrega, fornecedorProdutos.principal, fornecedorProdutos.fabricante, " +
			    "fornecedor.cdfornecedor, fornecedor.nome"
			)
			.leftOuterJoin("produtos.unidadeMedida unidadeMedida")
			.leftOuterJoin("produtos.fornecedorProdutos fornecedorProdutos")
			.leftOuterJoin("fornecedorProdutos.fornecedor fornecedor");
			

		
		if (produtoFiltro.getNome() != null && !produtoFiltro.getNome().trim().isEmpty()) {
            query.where("LOWER(produtos.nome) LIKE ?", "%" + produtoFiltro.getNome().toLowerCase() + "%");
        }

		if (produtoFiltro.getCdProduto() != null) {
		    query.where("produtos.cdProduto = ?", produtoFiltro.getCdProduto());
		}
		if (produtoFiltro.getPrecoMin() != null) {
            query.where("produtos.preco >= ?", produtoFiltro.getPrecoMin());
        }
        if (produtoFiltro.getPrecoMax() != null) {
            query.where("produtos.preco <= ?", produtoFiltro.getPrecoMax());
        }
		
		if(produtoFiltro.getUnidadeMedida() != null) {
			query.where("produtos.unidadeMedida = ?", produtoFiltro.getUnidadeMedida());
		}

	}
	
	@Override
	public void updateEntradaQuery(QueryBuilder<Produtos> query) {
		query.select(
			    "produtos.cdProduto, produtos.nome, produtos.codigoBarras, produtos.preco, " +
			    "unidadeMedida.cdUnidadeMedida, unidadeMedida.nome, unidadeMedida.sigla, unidadeMedida.ativo, " +
			    "fornecedorProdutos.cdfornecedorproduto, fornecedorProdutos.tempoentrega, fornecedorProdutos.principal, fornecedorProdutos.fabricante, " +
			    "fornecedor.cdfornecedor, fornecedor.nome"
			)
			.leftOuterJoin("produtos.unidadeMedida unidadeMedida")
			.leftOuterJoin("produtos.fornecedorProdutos fornecedorProdutos")
			.leftOuterJoin("fornecedorProdutos.fornecedor fornecedor");

	}
	
	
	public List<Produtos> findAtivos(){
		return query()
				.where("produtos.ativo is true")
				.list();
	}

	@Override
	public void updateSaveOrUpdate(SaveOrUpdateStrategy save) {
		save.saveOrUpdateManaged("fornecedorProdutos");
		super.updateSaveOrUpdate(save);
	}
	
	public List<Produtos> findForReport(br.com.arnia.projetoarnia.report.filter.ProdutoFiltro filtro){
		return query()
				.select("produtos.nome, produtos.cdProduto, produtos.preco, unidadeMedida.sigla")
				.leftOuterJoin("produtos.unidadeMedida unidadeMedida")
				.where("produtos.nome = ?", filtro.getNome())
				.list();
	}
	
	public List<Produtos> listarTodos() {
        return findAll(); 
    }
	
	public Produtos findById(Integer id) {
 	    if (id == null) return null;

 	    return (Produtos) getSession().get(Produtos.class, id);
 	}
}
