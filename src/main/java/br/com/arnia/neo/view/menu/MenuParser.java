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
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import br.com.linkcom.neo.core.web.NeoWeb;
import br.com.linkcom.neo.util.Util;

public class MenuParser {
	
	protected static final String DTD_LOCATION = "br/com/linkcom/neo/view/menu/menu.dtd";
	
	protected String urlPrefix;

	public String getUrlPrefix() {
		return urlPrefix;
	}

	public void setUrlPrefix(String urlPrefix) {
		this.urlPrefix = urlPrefix;
	}

	public Menu parse(InputStream inputStream) throws ParserConfigurationException, SAXException, IOException{
		if(inputStream == null){
			throw new NullPointerException("O arquivo de menu não foi encontrado");
		}
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();

		DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
		documentBuilder.setEntityResolver(new MenuEntityResolver());
		
		
		Document document = documentBuilder.parse(inputStream);
		
		Element element = document.getDocumentElement();
		String orientation = element.getAttribute("orientation");
		
		Menu menu = new Menu();
		menu.setOrientation(orientation);
		createMenu(element, menu);
		
		List<Menu> submenus = menu.getSubmenus();
		// os primeiros menus nao tevem ter espaçadores
		for (Menu menu2 : submenus) {
			if(menu2.getIcon().equals("&nbsp;&nbsp;&nbsp;&nbsp;")){
				menu2.setIcon("");
			}
		}
		
		return menu;
	}

	private void createMenu(Node basenode, Menu basemenu) {
		NodeList childNodes = basenode.getChildNodes();
		for (int i = 0; i < childNodes.getLength(); i++) {
			Node node = childNodes.item(i);
			if(node.getNodeType() == Node.ELEMENT_NODE){
				Menu menu = new Menu();
				basemenu.addMenu(menu);
				fillMenuAttributes(menu, node.getAttributes());
				
				createMenu(node, menu);
				
			}
		}
	}

	private void fillMenuAttributes(Menu menu, NamedNodeMap map) {
		String icon = map.getNamedItem("icon").getNodeValue();
		String title = map.getNamedItem("title").getNodeValue();

		if(icon != null && !"".equals(icon)){
			icon = NeoWeb.getRequestContext().getServletRequest().getContextPath() + icon;
		}
		
		//I18N
		String bundleKey = Util.locale.getBundleKey("menu."+title);
		if (bundleKey != null)
			title = bundleKey;

		String activepart = map.getNamedItem("activepart").getNodeValue();
		String dark = map.getNamedItem("dark").getNodeValue();
		String url = map.getNamedItem("url").getNodeValue();
		String target = map.getNamedItem("target").getNodeValue();
		String description = map.getNamedItem("description").getNodeValue();
		String report = map.getNamedItem("report").getNodeValue();
		String module = map.getNamedItem("module").getNodeValue();
		if(icon.equals("")){
			icon = "&nbsp;&nbsp;&nbsp;&nbsp;";
		} else {
			icon = "<img src=\""+icon+"\" align=\"absmiddle\">";
		}
		menu.setIcon(icon);
		menu.setBootstrapIcon(map.getNamedItem("bootstrap-icon").getNodeValue());
		menu.setTitle(title);
		if(urlPrefix != null && url != null && !url.equals("") && ! url.startsWith("javascript:")) {
			url = urlPrefix + url;
		}
		menu.setUrl(url);
		menu.setTarget(target);
		menu.setDescription(description);
		menu.setDark(dark);
		menu.setActivepart(activepart);
		menu.setReport(report);
		menu.setModule(module);
	}
}


class MenuEntityResolver implements EntityResolver{
	
	public MenuEntityResolver() {
		super();
	}

	public InputSource resolveEntity(String publicId, String systemId) throws SAXException, IOException {
		return new InputSource(Thread.currentThread().getContextClassLoader().getResourceAsStream(MenuParser.DTD_LOCATION));
	}
	
}