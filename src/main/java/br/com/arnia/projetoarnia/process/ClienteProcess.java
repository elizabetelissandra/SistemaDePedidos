package br.com.arnia.projetoarnia.process;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.web.servlet.ModelAndView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import br.com.arnia.projetoarnia.sistema.bean.Cliente;
import br.com.arnia.projetoarnia.sistema.bean.Endereco;
import br.com.arnia.projetoarnia.sistema.bean.Municipio;
import br.com.arnia.projetoarnia.sistema.bean.Uf;
import br.com.arnia.projetoarnia.sistema.dao.ClienteDAO;
import br.com.arnia.projetoarnia.sistema.service.ClienteService;
import br.com.arnia.projetoarnia.sistema.service.EnderecoService;
import br.com.arnia.projetoarnia.sistema.service.MunicipioService;
import br.com.arnia.projetoarnia.sistema.service.UfService;
import br.com.linkcom.neo.controller.Controller;
import br.com.linkcom.neo.controller.DefaultAction;
import br.com.linkcom.neo.controller.MultiActionController;
import br.com.linkcom.neo.core.web.WebRequestContext;
import br.com.linkcom.neo.util.TLSSocketConnectionFactory;
import br.com.linkcom.neo.view.ajax.JsonModelAndView;

@Controller(path = "/sistema/process/Cliente")
public class ClienteProcess extends MultiActionController {

    private UfService ufService;
    private MunicipioService municipioService;
    private ClienteDAO clienteDAO;

    public void setClienteDAO(ClienteDAO clienteDAO) {this.clienteDAO = clienteDAO;	}
    public void setUfService(UfService ufService) { this.ufService = ufService; }
    public void setMunicipioService(MunicipioService municipioService) { this.municipioService = municipioService; }

    @DefaultAction
    public ModelAndView index(WebRequestContext request) {
        return new ModelAndView("crud/clienteEntrada");
    }
    
    public ModelAndView ajaxBuscaCep(WebRequestContext request, String cep) {
        cep = request.getParameter("cep");
        if (cep != null) cep = cep.replaceAll("\\D", "");
        if (cep == null || cep.length() != 8) {
            return new JsonModelAndView().addObject("erro", "CEP inválido");
        }

        SSLConnectionSocketFactory sf = new SSLConnectionSocketFactory(
            new TLSSocketConnectionFactory(),
            new String[] { "TLSv1.2" },
            null,
            SSLConnectionSocketFactory.getDefaultHostnameVerifier()
        );

        try {
            HttpResponse response = HttpClientBuilder.create().setSSLSocketFactory(sf).build()
                    .execute(new HttpGet("https://viacep.com.br/ws/" + cep + "/json/"));
            HttpEntity entityResponse = response.getEntity();
            String jsonRetorno = EntityUtils.toString(entityResponse, "UTF-8");

            if (jsonRetorno == null || !jsonRetorno.trim().startsWith("{")) {
                return new JsonModelAndView().addObject("erro", "Resposta inválida da API de CEP");
            }

            Gson gson = new GsonBuilder().create();
            CepResponse cepResponse = gson.fromJson(jsonRetorno, CepResponse.class);

            if ((cepResponse.getUf() == null && cepResponse.getLocalidade() == null && cepResponse.getLogradouro() == null)
                    || Boolean.TRUE.equals(cepResponse.getErro())) {
                return new JsonModelAndView().addObject("erro", "CEP não encontrado");
            }

            Uf uf = ufService.findOrCreateBySigla(cepResponse.getUf());
            Municipio municipio = municipioService.findOrCreateByNomeAndUf(cepResponse.getLocalidade(), uf);

            return new JsonModelAndView()
                    .addObject("logradouro", cepResponse.getLogradouro())
                    .addObject("bairro", cepResponse.getBairro())
                    .addObject("cep", cepResponse.getCep())
                    .addObject("municipio", municipio.getNome())
                    .addObject("uf", uf.getSigla());

        } catch (Exception e) {
            e.printStackTrace();
            return new JsonModelAndView().addObject("erro", "Erro ao buscar CEP: " + e.getMessage());
        }
    }

    public static class CepResponse {
        private String cep;
        private String logradouro;
        private String complemento;
        private String bairro;
        private String localidade;
        private String uf;
        private String ibge;
        private String gia;
        private String ddd;
        private String siafi;
        private Boolean erro;

        public String getCep() { return cep; }
        public String getLogradouro() { return logradouro; }
        public String getComplemento() { return complemento; }
        public String getBairro() { return bairro; }
        public String getLocalidade() { return localidade; }
        public String getUf() { return uf; }
        public String getIbge() { return ibge; }
        public String getGia() { return gia; }
        public String getDdd() { return ddd; }
        public String getSiafi() { return siafi; }
        public Boolean getErro() { return erro; }
        public void setErro(Boolean erro) { this.erro = erro; }
    }
}