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

				<t:property name="cdcliente" label="Cliente" col_lg="6" col_md="6"
					col_sm="6" col_xs="6" />
				<t:property name="cdvendedor" label="Vendedor" col_lg="6" col_md="6"
					col_sm="6" col_xs="6" />
				<t:property name="dataInicial" col_lg="6" col_md="6" col_sm="6"
					col_xs="6" />
				<t:property name="dataFinal" col_lg="6" col_md="6" col_sm="6"
					col_xs="6" />
				<t:property name="nomesProdutos" label="Produto" lookup="true" escape="false"/>
			</n:panelGridBootstrap>
		</t:tabelaFiltro>
	</t:janelaFiltro>
	<t:janelaResultados>
		<t:tabelaResultados>
			<t:property name="cdpedido"/>
			<t:property name="cdcliente.nome" label="Cliente" col_lg="6"
				col_md="6" col_sm="6" col_xs="6" />
			<t:property name="cdvendedor.nome" label="Vendedor" col_lg="6"
				col_md="6" col_sm="6" col_xs="6" />
			<t:property name="datapedido" label="Data do pedido" col_lg="6"
				col_md="6" col_sm="6" col_xs="6" />
			<t:property name="totalPedido" label="Total do Pedido" id="total" readOnly="true" col_lg="6"
				col_md="6" col_sm="6" col_xs="6" />

			<t:property name="nomesProdutos" label="Produtos" escape="false" />


		</t:tabelaResultados>
	</t:janelaResultados>
</t:listagem>