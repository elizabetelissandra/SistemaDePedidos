package br.com.arnia.projetoarnia.sistema.crud;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.web.servlet.ModelAndView;



import br.com.arnia.projetoarnia.filtro.ClienteFiltro;
import br.com.arnia.projetoarnia.sistema.bean.Cliente;
import br.com.arnia.projetoarnia.sistema.bean.Endereco;
import br.com.arnia.projetoarnia.sistema.service.ClienteService;
import br.com.arnia.projetoarnia.sistema.service.EnderecoService;
import br.com.linkcom.neo.controller.Controller;
import br.com.linkcom.neo.controller.DefaultAction;
import br.com.linkcom.neo.controller.crud.CrudController;
import br.com.linkcom.neo.core.web.WebRequestContext;

import br.com.linkcom.neo.view.ajax.JsonModelAndView;

@Controller(path = "/sistema/crud/Cliente")
public class ClienteCrud extends CrudController<ClienteFiltro, Cliente, Cliente>{
	
	private ClienteService clienteService;
	private EnderecoService enderecoService;

	public void setClienteService(ClienteService clienteService) {
		this.clienteService = clienteService;
	}
	public void setEnderecoService(EnderecoService enderecoService) {
		this.enderecoService = enderecoService;
	}

	
	public ModelAndView ajaxBuscaEnderecos(WebRequestContext request) {
	    String param = request.getParameter("clienteId");
	    System.out.println(">>> Param clienteId recebido=" + request.getParameter("clienteId"));
	    System.out.println(">>> clienteId convertido=" + param);
	    Integer clienteId = null;
	    if (param != null && !param.trim().isEmpty()) {
	        try {
	            clienteId = Integer.valueOf(param);
	        } catch (NumberFormatException e) {
	            System.out.println(">>> clienteId inválido: " + param);
	        }
	    }

	    System.out.println(">>> Entrou no ajaxBuscaEnderecos com clienteId=" + clienteId);

	    if (clienteId == null) {
	        return new JsonModelAndView().addObject("enderecos", Collections.emptyList());
	    }

	    List<Endereco> enderecos = enderecoService.findEnderecosByClienteId(clienteId);
	    
	    System.out.println(">>> Total de endereços encontrados=" + 
	    	    (enderecos != null ? enderecos.size() : "null"));
	    
	    List<Map<String, Object>> lista = new ArrayList<Map<String, Object>>();
	    for (Endereco e : enderecos) {
	        Map<String, Object> map = new HashMap<String, Object>();
	        String cidade = e.getMunicipio() != null ? e.getMunicipio().getNome() : null;
	        
	        map.put("id", e.getCdendereco());
	        map.put("descricao",
	            e.getLogradouro()
	            + (e.getNumero() != null ? ", " + e.getNumero() : "")
	            + (cidade != null ? " - " + cidade : "")
	            + (e.getMunicipio() != null ? " - " + e.getMunicipio().getNome() : "")
	            + (e.getCep() != null ? " - CEP: " + e.getCep() : "")
	        );

	        lista.add(map);
	    }

	    return new JsonModelAndView().addObject("enderecos", lista);


}
	

}
