<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="n" uri="neo"%>
<%@ taglib prefix="t" uri="template"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="b" uri="bootstrap"%>

<t:listagem>
	<t:janelaFiltro>
		<t:tabelaFiltro showSubmit="true">
			<n:panelGridBootstrap>
				<t:property name="nome" />
			</n:panelGridBootstrap>
		</t:tabelaFiltro>
	</t:janelaFiltro>
	<t:janelaResultados>
		<t:tabelaResultados>
			<t:property name="cdCliente" />
			<t:property name="nome" />
			<t:property name="tipopessoa" id="tipopessoa" />
			<t:property name="cpf" id="cpf" />
			<t:property name="cnpj" id="cnpj" />
			<t:property name="sexo" id="sexo" />
		</t:tabelaResultados>
	</t:janelaResultados>
</t:listagem>