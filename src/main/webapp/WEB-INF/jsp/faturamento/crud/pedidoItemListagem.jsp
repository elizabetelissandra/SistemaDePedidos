<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="n" uri="neo"%>
<%@ taglib prefix="t" uri="template"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="b" uri="bootstrap"%>

<t:listagem>
	<t:janelaFiltro> 
		<t:tabelaFiltro showSubmit="true">
			<n:panelGridBootstrap>
				<t:property name="preco" col_lg="6" col_md="6" col_sm="6" col_xs="6"/>
			</n:panelGridBootstrap>
		</t:tabelaFiltro>
	</t:janelaFiltro>
	<t:janelaResultados>
		<t:tabelaResultados>
			<t:property name="cdproduto.nome" label="Produto" />
			<t:property name="preco" label="Preço" />
			<t:property name="quantidade" label="Quantidade" />
			<t:property name="descontounitario" label="Desconto Unitário" />
			<t:property name="totalItem" label="Total Item" readOnly="true" />
		</t:tabelaResultados>
	</t:janelaResultados>
</t:listagem>