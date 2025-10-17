<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="n" uri="neo"%>
<%@ taglib prefix="t" uri="template"%>

<html>
	<head>
		<meta http-equiv="X-UA-Compatible" content="IE=7" />
		<meta http-equiv="content-type" content="text/html; charset=iso-8859-1">
			
		<link rel="StyleSheet" href="${ctx}/css/app.css" type="text/css">
		<link rel="StyleSheet" href="${ctx}/css/app-ie.css" type="text/css">
		<link rel="StyleSheet" href="${ctx}/css/theme.css" type="text/css">
		<link rel="StyleSheet" href="${ctx}/css/default.css" type="text/css">

		<script language="JavaScript" src="${ctx}/resource/js/autocomplete/jquery.js"></script>
		<title>Início2</title>
	</head>
	<body leftmargin="0" topmargin="0" rightmargin="0"
		style="padding: 0px; margin: 0px;">
		<form name="loginForm" action="${ctx}/interno/neo_security_manager"
			method="post">
			<div align="center">
				<div id="corpo">
					<div class="cabecalho">
						<s:logomarca showLogo="false" />
						<br/>
						<br/>
						<br/>
						<br/>
						<br/>
						<br/>
						<br/>
						<br/>
						<br/>
						<br/>
						<br/>
						<br/>
						<TABLE align="center">
							<tr>
								<td colspan="3">
									<c:if test="${!empty login_error && empty login_bloqueado}">
										<div class="flash_alert" id="error"
											style="height: 20px; vertical-align: middle; border: 1px solid #CCCCCC; font-size: 12px; margin-bottom: 12px; padding: 5px 5px 5px 30px; text-align: left;">
											Login e/ou senha inv&aacute;lidos.
										</div>
									</c:if>
									<c:if test="${!empty login_bloqueado}">
										<div class="flash_alert" id="error"
											style="height: 30px; vertical-align: middle; border: 1px solid #CCCCCC; font-size: 12px; margin-bottom: 12px; padding: 5px 5px 5px 30px; text-align: left;">
											Este usuário está bloqueado. Favor procurar o
											<br>
											Administrador do Sistema.
										</div>
									</c:if>
								</td>
							</tr>
							<TR>
								<td align="center" colspan="3">
									<div class="flash_notice" id="Flash-message"
										style="font-size: 12px;"></div>
								</td>
							</TR>
							<tr>
								<td>
									<font color=black><b>LOGIN</b></font>
									<BR>
									<input size="25" type="text" name="username"
										style="font-size: 13px; background-color: #fff9d0;"
										value="${param.username}" maxlength="50" id="login" />
								</td>
								<td>
									<font color=black><b>SENHA</b></font>
									<BR>
									<input size="20" type="password" name="password"
										style="font-size: 13px; background-color: #fff9d0;" />
								</td>
								<td align="center">
									<br>
									<input type="submit" value="Entrar" class="buttonClass"
										style="height: 22px;" />
								</td>
							</tr>
						</TABLE>
						<BR>
						<table align="center">
							<tr>
								<td align="center">
									<br>
									<!-- 
									<a href="${ctx}/pub/Enviasenha" class="destaque">Esqueceu sua senha?</a>
									-->
								</td>
							</tr>
						</table>
					</div>
				</div>
			</div>
		</form>
		<script>
			document.getElementById("login").focus();	
		</script>
	</body>
</html>