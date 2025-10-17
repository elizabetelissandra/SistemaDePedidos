<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="n" uri="neo"%>
<%@ taglib prefix="t" uri="template"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<t:entrada>
	<t:janelaEntrada>
		<t:tabelaEntrada>
			<t:property name="cdProduto"/>
			<t:property name="nome"/>
			<t:property name="preco"/>
			<t:property name="unidadeMedida"/>
		</t:tabelaEntrada>
		<t:detalhe name="fornecedorProdutos">
			<t:property name="cdfornecedorproduto"/>
			<t:property name="fornecedor"/>
			<t:property name="tempoentrega"/>
			<t:property name="principal"/>
			<t:property name="fabricante"/>
		</t:detalhe>

	</t:janelaEntrada>
</t:entrada>