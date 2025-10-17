<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="n" uri="neo"%>
<%@ taglib prefix="t" uri="template"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<t:entrada>
	<t:janelaEntrada>
		<t:tabelaEntrada>
			<t:property name="cdvendedor"/>
			<t:property name="nome"/>
			<t:property name="tipopessoa" id="tipopessoa" onchange="showCpfCnpj()"/>
			<t:property name="cpf" id="campoCpf"/>
			<t:property name="cnpj" id="campoCnpj"/>
		</t:tabelaEntrada>
	</t:janelaEntrada>
</t:entrada>

<script type="text/javascript">
	function showCpfCpnj(){
		String tipo = document.getElementById("tipopessoa").value;

		// Esconde os dois campos primeiro
		document.getElementById("campoCpf").closest("tr").style.display = "none";
		document.getElementById("campoCnpj").closest("tr").style.display = "none";

		if(tipo === "F"){ 
			document.getElementById("campoCpf").closest("tr").style.display = "";
		}
		else if(tipo === "J"){ 
			document.getElementById("campoCnpj").closest("tr").style.display = "";
		}
		}
	window.onload = showCpfCnpj;
	</script>