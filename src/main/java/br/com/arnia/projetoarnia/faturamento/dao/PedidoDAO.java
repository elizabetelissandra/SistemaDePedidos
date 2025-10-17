package br.com.arnia.projetoarnia.faturamento.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;

import br.com.arnia.projetoarnia.faturamento.bean.Pedido;
import br.com.arnia.projetoarnia.filtro.PedidoFiltro;
import br.com.arnia.projetoarnia.sistema.bean.Cliente;
import br.com.arnia.projetoarnia.suprimento.bean.Produtos;
import br.com.linkcom.neo.controller.crud.FiltroListagem;
import br.com.linkcom.neo.persistence.GenericDAO;
import br.com.linkcom.neo.persistence.QueryBuilder;
import br.com.linkcom.neo.persistence.SaveOrUpdateStrategy;

public class PedidoDAO extends GenericDAO<Pedido> {

	@Override
	public void updateListagemQuery(QueryBuilder<Pedido> query, FiltroListagem _filtro) {
		PedidoFiltro filtro = (PedidoFiltro) _filtro;
		
		query.select("pedido")
        .leftOuterJoin("pedido.cdcliente cliente")
        .leftOuterJoin("pedido.cdvendedor vendedor")
        .leftOuterJoin("pedido.itens pedidoItem")
        .leftOuterJoin("pedidoItem.cdproduto produto")
        .leftOuterJoin("pedido.parcelas pedidoParcela")
        .orderBy("pedido.cdpedido desc");
		 //produto.nome, pedidoItem.cdpedidoitem, pedidoItem.preco, pedidoItem.quantidade, pedidoItem.descontounitario
		 
		if (filtro.getCdcliente() != null && filtro.getCdcliente().getCdCliente() != null) {
		    query.where("pedido.cdcliente.cdCliente = ?", filtro.getCdcliente().getCdCliente());
		}


	    
	    if (filtro.getCdvendedor() != null && filtro.getCdvendedor().getCdvendedor() != null) {
	    	query.where("pedido.cdvendedor.cdvendedor = ?", filtro.getCdvendedor().getCdvendedor());
	    	}

	    if (filtro.getDataInicial() != null) {
	        query.where("pedido.datapedido >= ?", filtro.getDataInicial());
	    }

	    if (filtro.getDataFinal() != null) {
	        query.where("pedido.datapedido <= ?", filtro.getDataFinal());
	    }


	    if (filtro.getNomesProdutos() != null && !filtro.getNomesProdutos().trim().isEmpty()) {    
	        String nomeProdutoBusca = "%" + filtro.getNomesProdutos().toLowerCase() + "%";
	        query.where("exists (from pedido.itens item where lower(item.cdproduto.nome) LIKE ?)", nomeProdutoBusca);
	    
	    }
	   
	    if (filtro.getPrecoMinimo() != null) {
	        query.where("pedidoItem.preco >= ?", filtro.getPrecoMinimo());
	    }
	    if (filtro.getPrecoMaximo() != null) {
	        query.where("pedidoItem.preco <= ?", filtro.getPrecoMaximo());
	    }

	    if (filtro.getVencimentoInicial() != null) {
	        query.where("pedidoParcela.datavencimento >= ?", filtro.getVencimentoInicial());
	    }
	    if (filtro.getVencimentoFinal() != null) {
	        query.where("pedidoParcela.datavencimento <= ?", filtro.getVencimentoFinal());
	    }

	    if (Boolean.TRUE.equals(filtro.getParcelasVencidas())) {
	        query.where("pedidoParcela.datavencimento < current_date");
	    }
		
		}

	
	public List<Pedido> findPedidosPorProduto(Produtos produto) {
	    String hql = "SELECT p FROM Pedido p JOIN p.itens item WHERE item.cdproduto = :produto";
	    
	    Query query = getSession().createQuery(hql);
	    query.setParameter("produto", produto);
	    
	    return query.list();
	}

	@Override
	public void updateEntradaQuery(QueryBuilder<Pedido> query) {
		query.select("pedido.cdcliente,pedido.datapedido, cliente.nome, pedido.cdendereco, endereco.logradouro, endereco.numero, endereco.cep, pedido.cdpedido, cliente.cdCliente, endereco.cdendereco, vendedor.cdvendedor, formapagamento.cdformapagamento, formapagamento.nome, formapagamento.avista,pedidoItem.cdpedidoitem, pedidoItem.quantidade,pedidoItem.cdproduto, pedidoItem.preco,"
				+ "produto.cdProduto, produto.nome, pedidoParcela.cdpedidoparcela, pedidoParcela.cdpedido, pedidoParcela.numeroparcela, pedidoParcela.valorparcela, pedidoParcela.datavencimento")
		.leftOuterJoin("pedido.cdcliente cliente")
        .leftOuterJoin("pedido.cdendereco endereco")
        .leftOuterJoin("pedido.cdvendedor vendedor")
        .leftOuterJoin("pedido.formapagamento formapagamento")
        .leftOuterJoin("pedido.itens pedidoItem")
        .leftOuterJoin("pedidoItem.cdproduto produto")
        .leftOuterJoin("pedido.parcelas pedidoParcela");
	}

	public List<Pedido> findAtivos() {
		return query().where("pedido.ativo is true").list();
	}
	
	@Override
	public void updateSaveOrUpdate(SaveOrUpdateStrategy save) {
		save.saveOrUpdateManaged("itens");
		save.saveOrUpdateManaged("parcelas");
		super.updateSaveOrUpdate(save);
	}

}
