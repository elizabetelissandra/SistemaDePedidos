<%@page import="br.com.arnia.projetoarnia.report.filter.ProdutoFiltro"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="n" uri="neo"%>
<%@ taglib prefix="t" uri="template"%>

<t:tela titulo="${descricaotela}">
	<n:panelGridBootstrap>
		<n:bean name="filtro">
			<t:propertyConfig mode="input" showLabel="true">
				<t:property name="nome"/>
			</t:propertyConfig>
		</n:bean>
		<n:panel>
			<n:submit url="/suprimentos/relatorio/produtos" action="gerar">Gerar relatório</n:submit>
		</n:panel>
	</n:panelGridBootstrap>
</t:tela>

<script type="text/javascript">	
</script>