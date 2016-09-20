<%@ include file="includes/header.jsp" %>

	<h1><fmt:message key="persona.crear.title"/></h1>

	<form:form commandName="persona" action="insertar-persona.html" method="post">
	
		<label for="nombre"><fmt:message key="persona.nombre"/></label>
		<form:input path="nombre" placeholder="Escribe tu nombre"/>
		<form:errors path="nombre" cssClass="error"/>
		<br><br>
		
		<label for="edad"><fmt:message key="persona.edad"/></label>
		<form:input path="edad"/>
		<form:errors path="edad" cssClass="error"/>
		<br><br><br>		
		<input type="submit" value="<fmt:message key="persona.boton.crear"/>">
		
	</form:form>

<a href="<c:url value="/"/>">Home</a>

<%@ include file="includes/footer.jsp" %>