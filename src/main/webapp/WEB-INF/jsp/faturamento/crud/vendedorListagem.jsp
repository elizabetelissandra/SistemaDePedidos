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
				<t:property name="nome" col_lg="6" col_md="6" col_sm="6" col_xs="6"/>
			</n:panelGridBootstrap>
		</t:tabelaFiltro>
	</t:janelaFiltro>
	<t:janelaResultados>
		<t:tabelaResultados>
			<t:property name="cdvendedor" col_lg="6" col_md="6" col_sm="6" col_xs="6"/>
			<t:property name="nome" col_lg="6" col_md="6" col_sm="6" col_xs="6"/>
			<t:property name="cpf" col_lg="6" col_md="6" col_sm="6" col_xs="6"/>
		</t:tabelaResultados>
	</t:janelaResultados>
</t:listagem>