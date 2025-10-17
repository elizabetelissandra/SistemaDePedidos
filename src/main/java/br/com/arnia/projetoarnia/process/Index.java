package br.com.arnia.projetoarnia.process;

import java.io.IOException;

import org.springframework.web.servlet.ModelAndView;

import br.com.linkcom.neo.bean.annotation.Bean;
import br.com.linkcom.neo.controller.Controller;
import br.com.linkcom.neo.controller.DefaultAction;
import br.com.linkcom.neo.controller.MultiActionController;
import br.com.linkcom.neo.core.web.WebRequestContext;

@Bean
@Controller(path = {"/sistema/index"})
public class Index  extends MultiActionController {

	@DefaultAction
	public ModelAndView index(WebRequestContext request) throws IOException{
		return new ModelAndView("index");
	}
}
