<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:choose>
	<c:when test="${clearBase}">
		<jsp:include page="../baseClean.jsp"></jsp:include>
	</c:when>
	<c:otherwise>
		<jsp:include page="../base.jsp"></jsp:include>
	</c:otherwise>
</c:choose>