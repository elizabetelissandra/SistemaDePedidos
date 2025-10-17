<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="n" uri="neo"%>
<%@ taglib prefix="t" uri="template"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<t:entrada>
    <t:janelaEntrada>
        <t:tabelaEntrada>
            <t:property name="cdCliente" />
            <t:property name="nome" />
            <t:property name="tipopessoa" id="tipopessoa" />
            <t:property name="cpf" id="cpf" style="display:none" />
            <t:property name="cnpj" id="cnpj" style="display:none" />
            <t:property name="sexo" id="sexo" style="display:none" />
        </t:tabelaEntrada>

        <t:detalhe name="enderecos">
            <t:property name="cep" id="cep" onblur="buscarCep(this)" />
            <t:property name="logradouro" id="logradouro" />
            <t:property name="numero" />
            <t:property name="municipio.uf" id="uf" items="from UF" />
            <t:property name="municipio" id="municipio" parent="uf" />
        </t:detalhe>
    </t:janelaEntrada>
</t:entrada>

<script type="text/javascript">
window.onload = function() {
    var tipoPessoa = document.getElementById("tipopessoa");
    var cpf = document.getElementById("cpf");
    var cnpj = document.getElementById("cnpj");
    var sexo = document.getElementById("sexo");
    var labelCpf = document.querySelector("label[for='cpf']");
    var labelCnpj = document.querySelector("label[for='cnpj']");
    var labelSexo = document.querySelector("label[for='sexo']");

    // Esconde campos inicialmente
    if (labelCpf) labelCpf.style.display = "none";
    if (labelCnpj) labelCnpj.style.display = "none";
    if (labelSexo) labelSexo.style.display = "none";
    if (cpf) cpf.style.display = "none";
    if (cnpj) cnpj.style.display = "none";
    if (sexo) sexo.style.display = "none";

    
    tipoPessoa.addEventListener("change", function() {
        if (this.value === "FISICA") {
            cpf.style.display = "";
            sexo.style.display = "";
            cnpj.style.display = "none";
            labelCpf.style.display = "";
            labelSexo.style.display = "";
            labelCnpj.style.display = "none";
        } else if (this.value === "JURIDICA") {
            cpf.style.display = "none";
            sexo.style.display = "none";
            cnpj.style.display = "";
            labelCpf.style.display = "none";
            labelSexo.style.display = "none";
            labelCnpj.style.display = "";
        } else {
            cpf.style.display = "none";
            sexo.style.display = "none";
            cnpj.style.display = "none";
            labelCpf.style.display = "none";
            labelSexo.style.display = "none";
            labelCnpj.style.display = "none";
        }
    });
};


function buscarCep(cepInput) {
    var cep = cepInput.value.replace(/\D/g, '');
    if (cep.length !== 8) {
        alert("CEP inválido");
        return;
    }

    // pega o índice pelo atributo name (ex: enderecos[1].cep)
    var name = $(cepInput).attr("name");
    var match = name.match(/enderecos\[(\d+)\]/);
    if (!match) {
        alert("Não foi possível identificar o índice do endereço.");
        return;
    }
    var index = match[1];

    $.getJSON("${pageContext.request.contextPath}/sistema/process/Cliente",
        { ACAO: "ajaxBuscaCep", cep: cep },
        function(json) {
            if (json.erro) {
                alert(json.erro);
                return;
            }

            // ===== Campos básicos =====
            $('[name="enderecos['+index+'].logradouro"]').val(json.logradouro || "");
            $('[name="enderecos['+index+'].cep"]').val(json.cep || "");

            // ===== UF =====
            var ufSelect = $('[name="enderecos['+index+'].municipio.uf"]');
            if (json.ufId) {
                ufSelect.val(json.ufId).change();
            } else if (json.uf) {
                if (ufSelect.find('option[value="' + json.uf + '"]').length) {
                    ufSelect.val(json.uf).change();
                } else {
                    ufSelect.find('option').filter(function() {
                        return $(this).text().trim() === json.uf.trim();
                    }).prop('selected', true).change();
                }
            }

            // ===== Município =====
            setTimeout(function() {
                var municipioSelect = $('[name="enderecos['+index+'].municipio"]');
                if (json.municipioId) {
                    municipioSelect.val(json.municipioId);
                } else if (json.municipio) {
                    if (municipioSelect.find('option[value="' + json.municipio + '"]').length) {
                        municipioSelect.val(json.municipio);
                    } else {
                        municipioSelect.find('option').filter(function() {
                            return $(this).text().trim() === json.municipio.trim();
                        }).prop('selected', true);
                    }
                }
            }, 300);
        }
    );
}



</script>