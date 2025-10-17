package br.com.arnia.projetoarnia.suprimento.crud;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.arnia.projetoarnia.filtro.ProdutoFiltro;
import br.com.arnia.projetoarnia.suprimento.bean.Produtos;
import br.com.arnia.projetoarnia.suprimento.bean.UnidadeMedida;
import br.com.arnia.projetoarnia.suprimento.service.ProdutosService;
import br.com.arnia.projetoarnia.suprimento.service.UnidadeMedidaService;
import br.com.linkcom.neo.controller.Controller;
import br.com.linkcom.neo.controller.crud.CrudController;
import br.com.linkcom.neo.core.web.WebRequestContext;
import br.com.linkcom.neo.view.ajax.JsonModelAndView;

@Controller(path = "/suprimentos/crud/Produtos")
public class ProdutosCrud extends CrudController<ProdutoFiltro, Produtos, Produtos>{
	private UnidadeMedidaService unidadeMedidaService;
	private ProdutosService produtosService;

	public void setProdutosService(ProdutosService produtosService) {
		this.produtosService = produtosService;
	}

	public UnidadeMedidaService getUnidadeMedidaService() {
		return unidadeMedidaService;
	}

	public void setUnidadeMedidaService(UnidadeMedidaService unidadeMedidaService) {
		this.unidadeMedidaService = unidadeMedidaService;
	}

	public ModelAndView ajaxProdutos(WebRequestContext request) {
		List<Produtos> produtos = produtosService.listarTodos();
		List<Map<String, Object>> lista = new ArrayList<Map<String,Object>>();
		for(Produtos p : produtos) {
			if (p == null) {
				continue;
			}
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", p.getCdProduto());
			map.put("nome", p.getNome());
			map.put("preco", p.getPreco());
			lista.add(map);
		}
		return new JsonModelAndView().addObject("produtos", lista);
	}

	

}
