package br.com.arnia.projetoarnia.sistema.crud;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import br.com.arnia.projetoarnia.api.dto.CepResponse;
import br.com.arnia.projetoarnia.api.dto.CidadeResponse;
import br.com.arnia.projetoarnia.api.dto.ClimaResponse;
import br.com.arnia.projetoarnia.filtro.EnderecoFiltro;
import br.com.arnia.projetoarnia.sistema.bean.Endereco;
import br.com.arnia.projetoarnia.sistema.service.EnderecoService;
import br.com.linkcom.neo.controller.Controller;
import br.com.linkcom.neo.controller.DefaultAction;
import br.com.linkcom.neo.controller.crud.CrudController;
import br.com.linkcom.neo.core.web.WebRequestContext;
import br.com.linkcom.neo.util.TLSSocketConnectionFactory;
import br.com.linkcom.neo.view.ajax.JsonModelAndView;

@Controller(path = "/sistema/crud/Endereco")
public class EnderecoCrud extends CrudController<EnderecoFiltro, Endereco, Endereco> {

	private EnderecoService enderecoService;

	public void setEnderecoService(EnderecoService enderecoService) {
		this.enderecoService = enderecoService;
	}

	@DefaultAction
	public ModelAndView index(WebRequestContext request) {
		return new ModelAndView("crud/pedidoEntrada");
	}

	public ModelAndView ajaxBuscaClimaPorEndereco(WebRequestContext request) {
		String param = request.getParameter("enderecoId");
		Integer enderecoId = null;

		try {
			if (param != null && !param.trim().isEmpty()) {
				enderecoId = Integer.valueOf(param);
			}
		} catch (NumberFormatException e) {
			return new JsonModelAndView().addObject("erro", "enderecoId inválido");
		}

		if (enderecoId == null) {
			return new JsonModelAndView().addObject("erro", "enderecoId não informado");
		}

		Endereco e = enderecoService.findById(enderecoId);
		if (e == null) {
			return new JsonModelAndView().addObject("erro", "Endereço não encontrado");
		}

		String cep = e.getCep();
		if (cep == null || cep.trim().isEmpty()) {
			return new JsonModelAndView().addObject("erro", "Endereço não possui um CEP válido.");
		}

		cep = cep.replaceAll("[^0-9]", "");

		try {
			SSLConnectionSocketFactory sf = new SSLConnectionSocketFactory(new TLSSocketConnectionFactory(),
					new String[] { "TLSv1.2" }, null, SSLConnectionSocketFactory.getDefaultHostnameVerifier());

			Gson gson = new GsonBuilder().create();

			HttpResponse respCep = HttpClientBuilder.create().setSSLSocketFactory(sf).build()
					.execute(new HttpGet("https://brasilapi.com.br/api/cep/v2/" + cep));
			String jsonCep = EntityUtils.toString(respCep.getEntity(), "UTF-8");

			CepResponse cepInfo = gson.fromJson(jsonCep, CepResponse.class);

			if (cepInfo == null || cepInfo.getCity() == null) {
				return new JsonModelAndView().addObject("erro", "CEP não encontrado na BrasilAPI: " + cep);
			}

			String cidade = cepInfo.getCity();

			HttpResponse respCidade = HttpClientBuilder.create().setSSLSocketFactory(sf).build().execute(
					new HttpGet("https://brasilapi.com.br/api/cptec/v1/cidade/" + URLEncoder.encode(cidade, "UTF-8")));
			String jsonCidade = EntityUtils.toString(respCidade.getEntity(), "UTF-8");

			List<CidadeResponse> listaCidades;
			JsonElement elementoJson = new JsonParser().parse(jsonCidade);

			if (elementoJson.isJsonArray()) {
				CidadeResponse[] arrayCidades = gson.fromJson(elementoJson, CidadeResponse[].class);
				listaCidades = new ArrayList<CidadeResponse>(Arrays.asList(arrayCidades));
			} else {
				CidadeResponse cidadeUnica = gson.fromJson(elementoJson, CidadeResponse.class);
				listaCidades = new ArrayList<CidadeResponse>();
				if (cidadeUnica != null && cidadeUnica.getId() != null) {
					listaCidades.add(cidadeUnica);
				}
			}

			if (listaCidades.isEmpty()) {
				return new JsonModelAndView().addObject("erro",
						"Cidade '" + cidade + "' não encontrada na API de Clima.");
			}

			Integer cityCode = listaCidades.get(0).getId();

			HttpResponse respClima = HttpClientBuilder.create().setSSLSocketFactory(sf).build()
					.execute(new HttpGet("https://brasilapi.com.br/api/cptec/v1/clima/previsao/" + cityCode));
			String jsonClima = EntityUtils.toString(respClima.getEntity(), "UTF-8");

			ClimaResponse clima = gson.fromJson(jsonClima, ClimaResponse.class);

			if (clima == null || clima.getClima() == null || clima.getClima().isEmpty()) {
				return new JsonModelAndView().addObject("erro",
						"Não foi possível obter a previsão do tempo para esta cidade.");
			}

			ClimaResponse.Previsao hoje = clima.getClima().get(0);

			return new JsonModelAndView().addObject("cidade", clima.getCidade()).addObject("estado", clima.getEstado())
					.addObject("condicao", hoje.getCondicao_desc()).addObject("min", hoje.getMin())
					.addObject("max", hoje.getMax()).addObject("indiceUV", hoje.getIndice_uv());

		} catch (Exception ex) {
			ex.printStackTrace();
			return new JsonModelAndView().addObject("erro", "Erro ao buscar clima: " + ex.getMessage());
		}
	}



}
