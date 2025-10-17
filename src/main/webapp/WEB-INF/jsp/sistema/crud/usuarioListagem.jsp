<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="n" uri="neo"%>
<%@ taglib prefix="t" uri="template"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="b" uri="bootstrap"%>

<t:listagem>
	<t:janelaFiltro> 
		<t:tabelaFiltro showSubmit="true">
			<t:property name="email"/>
		</t:tabelaFiltro>
	</t:janelaFiltro>
	<t:janelaResultados>
		<t:tabelaResultados>
			<t:property name="cdUsuario"/>
			<t:property name="login" />
            <t:property name="senha" />
            <t:property name="email" />
		</t:tabelaResultados>
	</t:janelaResultados>
</t:listagem>