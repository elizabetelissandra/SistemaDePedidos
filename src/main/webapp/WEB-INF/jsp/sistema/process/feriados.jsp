<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="n" uri="neo"%>
<%@ taglib prefix="t" uri="template"%>

<t:tela titulo="${descricaotela}">
    <n:bean name="filtro">
        <t:propertyConfig mode="input" showLabel="true">
            <t:property name="ano" id="anoTeste"/>
        </t:propertyConfig>
    </n:bean>
    
    <button onclick="buscaFeriados()">Buscar feriados</button>

    
    <div id="listaFeriados" style="margin-top:20px;"></div>
</t:tela>

<style>
  
    .tabela-feriados {
        margin: 30px auto; 
        border-collapse: collapse;
        text-align: left;
        min-width: 300px;
    }

    .tabela-feriados th, 
    .tabela-feriados td {
        padding: 10px 15px; 
        border: 1px solid #ccc;
    }

    .tabela-feriados th {
        background-color: #f2f2f2;
        text-align: center;
    }

    .tabela-feriados tr:nth-child(even) {
        background-color: #fafafa;
    }
</style>

<script type="text/javascript">
    function buscaFeriados(){
        var jsonParametros = {
            "ACAO": "ajaxBuscaFeraidos",
            "ano": document.getElementById("anoTeste").value
        };

        $.getJSON("${ctx}/sistema/process/Feriado", jsonParametros, function(json){
            if(!json.mapaDados || json.mapaDados.length === 0){
                document.getElementById("listaFeriados").innerHTML = "<p style='text-align:center;'>Nenhum feriado encontrado.</p>";
                return;
            }

            var html = "<table class='tabela-feriados'>";
            html += "<tr><th>Data</th><th>Nome</th></tr>";

            json.mapaDados.forEach(function(obj) {
                html += "<tr>";
                html += "<td style='text-align:center;'>" + obj.date + "</td>";
                html += "<td>" + obj.name + "</td>";
                html += "</tr>";
            });

            html += "</table>";

            document.getElementById("listaFeriados").innerHTML = html;
        });
    }
</script>