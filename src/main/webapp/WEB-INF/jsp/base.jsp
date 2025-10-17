<%@ taglib prefix="b" uri="bootstrap"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="n" uri="neo"%>
<%@ taglib prefix="t" uri="template" %>

<script type="text/javascript" src="${ctx}/js/arnia.js"></script>
<b:template menu="/WEB-INF/menu.xml" skin="green"  titulo="Arnia"  tituloMini="AR" >
	<jsp:attribute name="navbar">
		<div class="navbar-custom-menu">
			<ul class="nav navbar-nav">
			  <!-- User Account: style can be found in dropdown.less -->
			  <li class="dropdown user user-menu">
				<a href="#" class="dropdown-toggle" data-toggle="dropdown">
				  	<span class="hidden-xs">Bem vindo(a),<b> ${sessionScope.USER.nome}</b></span>
				</a>
				<ul class="dropdown-menu">
				  <!-- User image -->
				  <li class="user-header">
					<p>
					  ${sessionScope.USER.nome}
					</p>
				  </li>
				  <!-- Menu Footer-->
				  
				  <div class="inline-block col-lg-12 user-footer"> 
						<div class="pull-right">
					  		<n:link url="/logout.jsp" class="btn btn-default btn-flat">&nbsp;Sair&nbsp;</n:link>
					  		<n:link url="/pub/process/Logout"><span style="color:#FFFFFF">Sair</span></n:link>
						</div>
				  </div>
				
				</ul>
			  </li>
			</ul>
		</div>
	</jsp:attribute>
    <jsp:body>
    	
		
		<jsp:include page="${bodyPage}" />
    </jsp:body>
</b:template>


<style>
    .file-input-wrapper.btn{
        display: none;
    }
    
    @media print {
	  .noprint {
	    display: none !important;
	  }
	}
</style>