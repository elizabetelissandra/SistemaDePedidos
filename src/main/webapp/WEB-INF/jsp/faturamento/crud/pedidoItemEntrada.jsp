<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="n" uri="neo"%>
<%@ taglib prefix="t" uri="template"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<t:entrada>
	<t:janelaEntrada>
		<t:tabelaEntrada>


			<t:property name="cdproduto" label="Produto" />
			<t:property name="preco" label="Preço" />
			<t:property name="quantidade" label="Quantidade" />
			<t:property name="descontounitario" label="Desconto Unitário" />
			<t:property name="totalItem" label="Total Item" readOnly="true" />


		</t:tabelaEntrada>
	</t:janelaEntrada>
</t:entrada>

