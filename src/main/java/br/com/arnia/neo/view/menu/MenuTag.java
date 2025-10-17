/*
 * Neo Framework http://www.neoframework.org
 * Copyright (C) 2007 the original author or authors.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 * 
 * You may obtain a copy of the license at
 * 
 *     http://www.gnu.org/copyleft/lesser.html
 * 
 */
package br.com.arnia.neo.view.menu;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.lang.StringUtils;
import org.xml.sax.SAXException;

import br.com.linkcom.neo.authorization.AuthorizationManager;
import br.com.linkcom.neo.authorization.User;
import br.com.linkcom.neo.core.standard.Neo;
import br.com.linkcom.neo.util.Util;
import br.com.linkcom.neo.view.BaseTag;


public class MenuTag extends BaseTag {
	
	public static final String MENU_CACHE_MAP = MenuTag.class.getName()+"_cache";
	
	/**
	 * Atributo
	 */
	protected String menupath;
	
	public String getMenupath() {
		return menupath;
	}

	public void setMenupath(String menupath) {
		this.menupath = menupath;
	}

	@Override
	@SuppressWarnings("unchecked")
	public void doComponent() throws JspException, IOException {
		if(menupath==null){
			throw new RuntimeException("A path do menu nao pode ser null");
		}
		
		String menuCode = null;
		Map<String, String> menuCacheMap = (Map<String, String>)getRequest().getSession().getAttribute(MENU_CACHE_MAP);
		if(menuCacheMap == null){
			menuCacheMap = new HashMap<String, String>();
			getRequest().getSession().setAttribute(MENU_CACHE_MAP, menuCacheMap);
		}
		
		InputStream resourceAsStream = null;
		try {
			MenuParser menuParser = new MenuParser();
			menuParser.setUrlPrefix(getRequest().getContextPath());
			resourceAsStream = getServletContext().getResourceAsStream(getMenupath());
			Menu menu = menuParser.parse(resourceAsStream);
			AuthorizationManager authorizationManager = Neo.getApplicationContext().getAuthorizationManager();
			User user = Neo.getRequestContext().getUser();
			//verificarAutorizacao(menu, authorizationManager, user);
			removeEmptyMenus(menu);
			
			MenuBuilder menuBuilder = new MenuBuilder();
			menuCode = menuBuilder.build(menu);
			menuCacheMap.put(menupath, menuCode);
			
			//fazer cache
		} catch (ParserConfigurationException e) {
			throw new JspException("Erro de parsing ao ler XML do menu. ", e);
		} catch (SAXException e) {
			throw new JspException("Erro de SAX ao ler XML do menu. ", e);
		} catch (IOException e) {
			throw new JspException("Erro de leitura (I/O) ao ler XML do menu. ", e);
		} catch (Exception e) {
			throw new RuntimeException("Erro ao gerar menu "+menupath, e);
		}
		finally {
			try {
				resourceAsStream.close();
			}
			catch (Exception e) {
			}
		}
		
		getOut().println(menuCode);

	}

	private void removeEmptyMenus(Menu menu) {
		if(menu.getSubmenus().size() > 0){
			for (int i = 0; i < menu.getSubmenus().size(); i++) {
				Menu submenu = menu.getSubmenus().get(i);
				removeEmptyMenus(submenu);
				if(!menu.getSubmenus().contains(submenu)){
					i--;
				}
			}
			
		}
		if((menu.getSubmenus().size() == 0 && Util.strings.isEmpty(menu.getUrl()) && (menu.getTitle() != null && !menu.getTitle().matches("--(-)+") ) && !menu.getTitle().equals("Área do Cliente")) || 
		   (menu.getSubmenus().size() > 0 && checkMenuDelimiters(menu.getSubmenus()))){
			Menu parent2 = menu.getParent();
			if(parent2 != null){
				parent2.getSubmenus().remove(menu);	
			}
		}
	}

	private boolean checkMenuDelimiters(List<Menu> submenus) {
		for (Menu menu : submenus) 
			if(menu.getTitle() != null && !menu.getTitle().matches("--(-)+"))
				return false;
		return true;
	}

	private void verificarAutorizacao(Menu menu, AuthorizationManager authorizationManager, User user) {
		int length = getRequest().getContextPath().length();
		for (Iterator<Menu> iter = menu.getSubmenus().iterator(); iter.hasNext();) {
			Menu submenu = iter.next();
			
			String url = submenu.getUrl();
			if (!StringUtils.isEmpty(url)) {
				if(url.contains("?")){
					url = url.substring(0, url.indexOf('?'));
				}
			}
//			verificarAutorizacao(submenu, authorizationManager, user);
		}
	}
	
}
