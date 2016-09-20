<%@ include file="includes/header.jsp" %>

    <h1><fmt:message key="heading"/></h1>
    <p><fmt:message key="greeting"/> <c:out value="${fecha}"/></p>
    <h3>Personas</h3>
    <c:forEach items="${personas}" var="persona">
      <c:out value="${persona.nombre}"/> <br><br>
    </c:forEach>
	<a href="<c:url value="/"/>">Home</a>

<%@ include file="includes/footer.jsp" %>