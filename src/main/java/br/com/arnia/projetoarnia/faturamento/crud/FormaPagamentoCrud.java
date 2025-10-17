package br.com.arnia.projetoarnia.faturamento.crud;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.servlet.ModelAndView;

import br.com.arnia.projetoarnia.faturamento.bean.FormaPagamento;
import br.com.arnia.projetoarnia.faturamento.service.FormaPagamentoService;
import br.com.arnia.projetoarnia.filtro.FormaPagamentoFiltro;
import br.com.linkcom.neo.controller.Controller;
import br.com.linkcom.neo.controller.crud.CrudController;
import br.com.linkcom.neo.core.web.WebRequestContext;
import br.com.linkcom.neo.view.ajax.JsonModelAndView;

@Controller(path = "/faturamento/crud/FormaPagamento")
public class FormaPagamentoCrud extends CrudController<FormaPagamentoFiltro, FormaPagamento, FormaPagamento>{

	private FormaPagamentoService formaPagamentoService;
	
	public void setFormaPagamentoService(FormaPagamentoService formaPagamentoService) {
		this.formaPagamentoService = formaPagamentoService;
	}
	
	public ModelAndView ajaxFormasPagamento(WebRequestContext request) {
	    System.out.println(">>> Entrou em ajaxFormasPagamento");

	    List<FormaPagamento> formas = formaPagamentoService.listarTodas();
	    System.out.println(">>> Total de formas de pagamento encontradas: " 
	                       + (formas != null ? formas.size() : 0));

	    List<Map<String, Object>> lista = new ArrayList<Map<String, Object>>();
	    for (FormaPagamento f : formas) {
	        if (f == null) {
	            System.out.println(">>> FormaPagamento nula na lista!");
	            continue;
	        }

	        System.out.println(">>> FormaPagamento:");
	        System.out.println("    id=" + f.getCdformapagamento());
	        System.out.println("    nome=" + f.getNome());
	        System.out.println("    avista=" + f.getAvista());

	        Map<String, Object> map = new HashMap<String, Object>();
	        map.put("id", f.getCdformapagamento());
	        map.put("nome", f.getNome());
	        map.put("avista", f.getAvista());
	        lista.add(map);
	    }

	    System.out.println(">>> JSON que será retornado: " + lista);

	    return new JsonModelAndView().addObject("formasPagamento", lista);
	}


}
