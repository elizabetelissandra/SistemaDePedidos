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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import br.com.linkcom.neo.core.web.NeoWeb;

public class MenuBuilder {

	public String build(Menu menu) {
		StringBuilder stringBuilder = new StringBuilder();

		String urlAtiva = NeoWeb.getRequestContext().getServletRequest()
				.getContextPath()
				+ NeoWeb.getRequestContext().getRequestQuery();
		
		List<Menu> caminhoMenuAtual = new ArrayList<Menu>();

		for (Menu submenu : menu.getSubmenus()) {
			if (pertenceCaminhoAtual(submenu, caminhoMenuAtual, urlAtiva)){
				caminhoMenuAtual.add(menu);
			}
		}

		for (Iterator<Menu> iter = menu.getSubmenus().iterator(); iter
				.hasNext();) {
			Menu submenu = iter.next();
			build(submenu, stringBuilder, iter.hasNext(), caminhoMenuAtual, urlAtiva);
		}

		return stringBuilder.toString();
	}

	private boolean pertenceCaminhoAtual(Menu menu,
			List<Menu> caminhoMenuAtual, String urlAtual) {
		
		if (menu.getSubmenus() == null || menu.getSubmenus().isEmpty()){
			if (menu.getUrl().equals(urlAtual)){
				caminhoMenuAtual.add(menu);
				return true;
			}
		}

		for (Menu submenu : menu.getSubmenus()) {
			if (pertenceCaminhoAtual(submenu, caminhoMenuAtual, urlAtual)){
				caminhoMenuAtual.add(menu);
				return true;
			}
		}
		
		return false;
	}

	private void build(Menu menu, StringBuilder stringBuilder, boolean hasNext,
			List<Menu> caminhoMenuAtual, String urlAtiva) {

		if (menu.getTitle() != null && menu.getTitle().matches("--(-)+")) {
			stringBuilder.append("<li><hr style=\"margin-top: 5px; margin-bottom: 5px; border-top: 2px solid #eee;\"/></li>");
			return;
		}
		
		boolean dark = menu.getDark() != null && menu.getDark().equalsIgnoreCase("true");
		boolean activepart = menu.getActivepart() != null && !menu.getActivepart().trim().equals("") && urlAtiva.indexOf(menu.getActivepart()) != -1;

		if (menu.getSubmenus() != null && menu.getSubmenus().size() > 0) {
			if (caminhoMenuAtual.contains(menu) || activepart) {
				stringBuilder.append("<li class=\"treeview active\">");
			}else{
				stringBuilder.append("<li class=\"treeview\">");
			}
		} else if (caminhoMenuAtual.contains(menu) || activepart) {
			stringBuilder.append("<li class=\"active\">");
		} else {
			stringBuilder.append("<li>");
		}
		String url = menu.getUrl();
		if (url == null || url.trim().isEmpty())
			url = "#";
		stringBuilder.append("<a ").append(dark ? "class=\"dark\"" : "").append(" href=\"").append(url).append("\">");
		if (menu.getIcon() != null && !menu.getIcon().trim().isEmpty()) {
			stringBuilder.append(menu.getIcon());
		}
		if (menu.getBootstrapIcon() != null
				&& !menu.getBootstrapIcon().trim().isEmpty()) {
			stringBuilder.append("<i class=\"fa ").append(
					menu.getBootstrapIcon()).append("\"></i>");
		}
		stringBuilder.append("<span>").append(menu.getTitle())
				.append("</span>");

		if (menu.getSubmenus() != null && !menu.getSubmenus().isEmpty()) {
			stringBuilder
					.append("<i class=\"fa fa-angle-left pull-right\"></i>");
		}

		stringBuilder.append("</a>");

		if (menu.getSubmenus() != null && !menu.getSubmenus().isEmpty()) {
			stringBuilder.append("<ul class=\"treeview-menu\">");
			for (Iterator<Menu> iter = menu.getSubmenus().iterator(); iter
					.hasNext();) {
				Menu submenu = iter.next();

				build(submenu, stringBuilder, iter.hasNext(), caminhoMenuAtual, urlAtiva);
			}
			stringBuilder.append("</ul>");
		}

		stringBuilder.append("</li>");
	}

}
