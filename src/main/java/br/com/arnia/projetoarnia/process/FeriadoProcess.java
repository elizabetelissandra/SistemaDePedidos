package br.com.arnia.projetoarnia.process;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;

import br.com.arnia.projetoarnia.process.filtro.FeriadosFiltro;
import br.com.linkcom.neo.controller.Controller;
import br.com.linkcom.neo.controller.DefaultAction;
import br.com.linkcom.neo.controller.MultiActionController;
import br.com.linkcom.neo.core.web.WebRequestContext;
import br.com.linkcom.neo.util.TLSSocketConnectionFactory;
import br.com.linkcom.neo.view.ajax.JsonModelAndView;

@Controller(path = "/sistema/process/Feriado")
public class FeriadoProcess extends MultiActionController {

    @DefaultAction
    public ModelAndView index(WebRequestContext request, FeriadosFiltro filtro) {
        return new ModelAndView("process/feriados").addObject("filtro", filtro);
    }

    public ModelAndView ajaxBuscaFeraidos(WebRequestContext request, FeriadosFiltro filtro) {
        SSLConnectionSocketFactory sf = new SSLConnectionSocketFactory(
                new TLSSocketConnectionFactory(),
                new String[]{"TLSv1.2"},
                null,
                SSLConnectionSocketFactory.getDefaultHostnameVerifier()
        );

        HttpGet http = new HttpGet("https://brasilapi.com.br/api/feriados/v1/" + filtro.getAno());

        try {
            HttpResponse response = HttpClientBuilder.create().setSSLSocketFactory(sf).build().execute(http);
            HttpEntity entityResponse = response.getEntity();
            String jsonRetorno = EntityUtils.toString(entityResponse, "UTF-8");

            Gson gson = new Gson();
            List<Map<String, Object>> map = gson.fromJson(jsonRetorno, List.class);

            // Formatação de datas
            SimpleDateFormat formatoEntrada = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat formatoSaida = new SimpleDateFormat("dd/MM/yyyy");

            for (Map<String, Object> feriado : map) {
                String dataOriginal = (String) feriado.get("date");
                try {
                    feriado.put("date", formatoSaida.format(formatoEntrada.parse(dataOriginal)));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }

            return new JsonModelAndView().addObject("mapaDados", map);

        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}