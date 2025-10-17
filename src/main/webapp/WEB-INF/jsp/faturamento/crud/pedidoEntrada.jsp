<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ page import="com.google.gson.Gson" %>
<%@ page import="br.com.arnia.projetoarnia.faturamento.bean.Pedido" %>
<%@ page import="br.com.arnia.projetoarnia.faturamento.bean.PedidoItem" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="n" uri="neo"%>
<%@ taglib prefix="t" uri="template"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<t:entrada id="formularioPedido">
	<t:janelaEntrada>
		<t:tabelaEntrada>
			<n:group legend="Principal" columns="3">
				<t:property name="cdpedido" id="cdpedido" />
				<t:property name="cdcliente" id="cliente" type="autocomplete"
					label="Nome" itemValue="cdCliente"
					onblur="carregarEnderecos(this.value)" />
				<t:property name="cdendereco" id="endereco" label="Endereco"
					itemValue="cdendereco" onblur="buscarClimaPorEndereco(this)" />
				<n:panel id="painelClima" style="margin-top:10px;">
					<div id="infoClima"
						style="display: none; font-size: 14px; line-height: 1.6;">
						<strong>Previsão do tempo:</strong>
						<p id="climaCidade"></p>
						<p id="climaCondicao"></p>
						<p id="climaTemperatura"></p>
						<p id="climaIndiceUV"></p>
					</div>
				</n:panel>
				<t:property name="cdvendedor" label="Vendedor" id="vendedor" />
				<t:property name="datapedido" label="Data do Pedido" id="datapedido" />
				<t:property name="observacao" label="Observacao" id="observacao" />
			</n:group>

			<n:group legend="Itens">
				<t:detalhe name="itens">
					<t:property name="cdproduto" label="Produto"  
					onchange="atualizarPreco(this)" />
					<t:property name="preco" label="Preco"
						onblur="recalcularTotal(this)" readOnly="true" />
					<t:property name="quantidade" label="Quantidade"
						onchange="recalcularTotal(this)" />
					<t:property name="descontounitario" label="Desconto Unitario"
						onblur="recalcularTotal(this)" />
					<t:property name="totalItem" label="Total Item" readOnly="true" />
				</t:detalhe>
			</n:group>
			
			<n:group columns="4" legend="Pagamento">
				<t:property name="formapagamento" label="Forma de Pagamento"
					id="formapagamento" itemValue="cdformapagamento"
					data-avista="formapagamento.avista" itemLabel="descricao"
					onblur="verificarFormaPagamento(this.value)" />
				<t:property name="totalPedido" label="Total do Pedido"
					readOnly="true" />
				<t:property name="qtdparcelas" label="Quantidade de Parcelas"
					id="qtdparcelas" />
				<n:submit type="button" onclick="gerarParcelas(); return false;">Gerar Parcelas</n:submit>
			</n:group>
			
			<n:group legend="Parcelas">
				<t:detalhe name="parcelas">
					<t:property name="numeroparcela" label="Numero de Parcelas"
						readOnly="true" />
					<t:property name="valorparcela" label="Valor da Parcela"
						readOnly="true" />
					<t:property name="datavencimento" label="Data de Vencimento"
						readOnly="true" />
				</t:detalhe>
			</n:group>
		</t:tabelaEntrada>
	</t:janelaEntrada>
</t:entrada>

<script type="text/javascript">
	function retornaIdEntidade(raw) {
		if (!raw) return null;
		var match = /cdcliente=(\d+)/i.exec(raw) || /cdendereco=(\d+)/i.exec(raw) || /cdformapagamento=(\d+)/i.exec(raw) || /cdproduto=(\d+)/i.exec(raw);
		if (match) return match[1];
		return raw;
	}

	function verificarFormaPagamento(formapagamentoRaw) {
		var formaPagamentoId = document.getElementById("formapagamento");
		var qtdParcelas = document.getElementById("qtdparcelas");
		if (formaPagamentoId && qtdParcelas) {
			var selectedOption = formaPagamentoId.options[formaPagamentoId.selectedIndex];
			var avista = selectedOption ? selectedOption.getAttribute("data-avista") : null;
			if (avista === "true") {
				qtdParcelas.value = 1;
				qtdParcelas.readOnly = true;
			} else {
				qtdParcelas.readOnly = false;
			}
		}
	}

	function carregarFormasPagamento() {
		var $select = $("#formapagamento");
		$select.empty();
		$.getJSON("${pageContext.request.contextPath}/faturamento/crud/FormaPagamento", { ACAO : "ajaxFormasPagamento" }, function(json) {
			if (json.formasPagamento && json.formasPagamento.length > 0) {
				$.each(json.formasPagamento, function(i, f) {
					var formaPagamentoObj = "br.com.arnia.projetoarnia.faturamento.bean.FormaPagamento[cdformapagamento=" + f.id + "]";
					$select.append($("<option>").val(formaPagamentoObj).text(f.nome).attr("data-avista", f.avista));
				});
			} else {
				$select.append('<option value="">Nenhuma forma de pagamento encontrada</option>');
			}
		}).fail(function(jqXHR) {
			console.error("ERRO AJAX (formasPagamento):", jqXHR.responseText);
		});
	}

	function carregarEnderecos(clienteRaw) {
		var clienteId = retornaIdEntidade(clienteRaw);
		var $endereco = $("#endereco");
		$endereco.empty();
		if (!clienteId) {
			$endereco.append('<option value="">Selecione um cliente primeiro</option>');
			return;
		}
		$.getJSON("${pageContext.request.contextPath}/sistema/crud/Cliente", { ACAO : "ajaxBuscaEnderecos", clienteId : clienteId })
		.done(function(json) {
			if (json.enderecos && json.enderecos.length > 0) {
				$.each(json.enderecos, function(i, e) {
					var enderecoObj = "br.com.arnia.projetoarnia.sistema.bean.Endereco[cdendereco=" + e.id + "]";
					$endereco.append($("<option>").val(enderecoObj).text(e.descricao));
				});
			} else {
				$endereco.append('<option value="">Nenhum endereço encontrado</option>');
			}
		}).fail(function(jqXHR) {
			console.error("ERRO AJAX (enderecos):", jqXHR.responseText);
		});
	}

	function buscarClimaPorEndereco(selectElement) {
	    const enderecoId = retornaIdEntidade(selectElement.value);
	    if (!enderecoId) return;
	    $.getJSON(`${pageContext.request.contextPath}/sistema/crud/Endereco`, { ACAO: "ajaxBuscaClimaPorEndereco", enderecoId: enderecoId })
	    .done(function (json) {
			var $containerDoClima = $("#infoClima");
			if (json.erro) {
				console.error(json.erro);
				$containerDoClima.hide();
				return;
			}
			setTimeout(function() {
				$containerDoClima.find("#climaCidade").text("Clima em " + json.cidade + " - " + json.estado);
				$containerDoClima.find("#climaCondicao").text("Condição: " + json.condicao);
				$containerDoClima.find("#climaTemperatura").text("Temp. Mínima: " + json.min + "°C / Máx. " + json.max + "°C");
				$containerDoClima.find("#climaIndiceUV").text("Índice UV: " + json.indiceUV);
				$containerDoClima.fadeIn();
			}, 100);
	    }).fail(function (jqXHR) {
			console.error("Falha na chamada AJAX (clima):", jqXHR.responseText);
	    });
	}

	function carregarProdutos(selectElement) {
		var $select = $(selectElement);
		$select.empty().append('<option value="">Carregando...</option>');
		$.getJSON("${pageContext.request.contextPath}/suprimentos/crud/Produtos", { ACAO : "ajaxProdutos" }, function(json) {
			$select.empty().append('<option value="">Selecione um produto</option>');
			if (json.produtos && json.produtos.length > 0) {
				$.each(json.produtos, function(i, p) {
					var produtoObj = "br.com.arnia.projetoarnia.suprimento.bean.Produtos[cdProduto=" + p.id + "]";
					$select.append($("<option>").val(produtoObj).text(p.nome).attr("data-preco", p.preco));
				});
			} else {
				$select.append('<option value="">Nenhum produto encontrado</option>');
			}
		}).fail(function() {
			$select.empty().append('<option value="">Erro ao carregar</option>');
		});
	}

	function atualizarPreco(selectEl) {
		var $select = $(selectEl);
		var preco = $select.find("option:selected").data("preco");
		var $row = $select.closest("tr");
		var $precoInput = $row.find("input[name$='.preco']");
		if (preco !== undefined) {
			$precoInput.val(preco);
		}
	}

	function recalcularTotal(inputEl) {
		var $row = $(inputEl).closest("tr");
		var precoStr = $row.find("input[name$='.preco']").val() || "0";
		precoStr = precoStr.replace(/\./g, "").replace(",", ".");
		var preco = parseFloat(precoStr) || 0;
		var quantidade = parseInt($row.find("input[name$='.quantidade']").val()) || 0;
		var descontoStr = $row.find("input[name$='.descontounitario']").val() || "0";
		descontoStr = descontoStr.replace(/\./g, "").replace(",", ".");
		var desconto = parseFloat(descontoStr) || 0;
		var total = (preco * quantidade) - (desconto * quantidade);
		$row.find("input[name$='.totalItem']").val(total.toFixed(2));
		recalcularTotalPedido();
	}

	function recalcularTotalPedido() {
		var totalPedido = 0;
		$("input[name$='.totalItem']").each(function() {
			var valor = parseFloat($(this).val()) || 0;
			totalPedido += valor;
		});
		$("input[name='totalPedido']").val(totalPedido);
	}

	function gerarParcelas() {
		var totalPedido = $("input[name='totalPedido']").val();
		var qtdParcelas = parseInt($("#qtdparcelas").val()) || 1;
		var dataPedidoStr = $("#datapedido").val();
		var avista = $("#formapagamento option:selected").attr("data-avista") || "false";
		if (!totalPedido || totalPedido <= 0) {
			alert("Total do pedido inválido para gerar parcelas.");
			return;
		}
		if (!dataPedidoStr) {
			alert("Informe a data do pedido.");
			return;
		}
		var partes = dataPedidoStr.split("/");
		var dataPedido = new Date(partes[2], partes[1] - 1, partes[0]);
		$("#parcelas tbody").empty();
		var valorParcela = totalPedido / qtdParcelas;
		for (var i = 0; i < qtdParcelas; i++) {
			$("button[id^=detalhe_pedidoParcela]").click();
			var dataVenc = new Date(dataPedido);
			if (avista !== "true") {
				dataVenc.setMonth(dataPedido.getMonth() + (i + 1));
			}
			var dia = String(dataVenc.getDate()).padStart(2, '0');
			var mes = String(dataVenc.getMonth() + 1).padStart(2, '0');
			var ano = dataVenc.getFullYear();
			var dataFormatada = dia + "/" + mes + "/" + ano;
			var valorParcelaBR = valorParcela.toFixed(2).replace(".", ",");
			$("input[name='parcelas[" + i + "].numeroparcela']").val(i + 1);
			$("input[name='parcelas[" + i + "].valorparcela']").val(valorParcelaBR);
			$("input[name='parcelas[" + i + "].datavencimento']").val(dataFormatada);
			$('a[id^="button\\.excluir"]').hide();
			setTimeout(function() {
				$('[name="parcelas"] th:last-child').remove();
				$('[name="parcelas"] td:last-child').remove();
			}, 100);
		}
	}

	$(document).ready(function() {
		var clienteInicial = retornaIdEntidade($("#cliente").val());
		if (clienteInicial) {
			carregarEnderecos($("#cliente").val());
		}
		carregarFormasPagamento();
		
		$("#cliente").change(function() {
			carregarEnderecos($(this).val());
		});
		$("#formapagamento").change(function() {
			verificarFormaPagamento($(this).val());
		});
		
		$("button[id^=detalhe_pedidoParcela]").hide();
	});
</script>