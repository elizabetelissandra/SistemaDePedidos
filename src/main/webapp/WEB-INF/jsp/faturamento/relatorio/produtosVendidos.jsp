<%@page import="br.com.arnia.projetoarnia.report.filter.ProdutoVendidoFiltro"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="n" uri="neo"%>
<%@ taglib prefix="t" uri="template"%>

<t:tela titulo="${descricaotela}">
	<n:panelGridBootstrap>
		
		<n:bean name="filtro">
			<t:propertyConfig mode="input" showLabel="true">
				
				<t:property name="dataInicial" label="Data Inicial do Pedido"/>
				
				<t:property name="dataFinal" label="Data Final do Pedido"/>
				
				<t:property name="cliente" label="Cliente (opcional)" type="autocomplete"/>
				
			</t:propertyConfig>
		</n:bean>
		
		<n:panel>
			<n:submit url="/faturamento/relatorio/produtosVendidos" action="gerar">Gerar relatório</n:submit>
		</n:panel>
		
	</n:panelGridBootstrap>
</t:tela>

<script type="text/javascript">	
</script>