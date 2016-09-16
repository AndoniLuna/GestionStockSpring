<%@ include file="/WEB-INF/views/include.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>

<head>
  <title></title>
  <style>
    .error { color: red; }
  </style>  
</head>

<body>
<h1><fmt:message key="persona.crear.title"/></h1>

<form:form commandName="persona" action="insert-persona.html" method="post">
	<form:input path="nombre"  placeholder="Nombre" /><br>
	<form:errors path="nombre" cssClass="error"/><br>
	
	<form:input path="edad"  placeholder="Edad" /><br>
	<form:errors path="edad" cssClass="error"/><br>
	
	<input type="submit" value="<fmt:message key="persona.boton.crear"/>">
</form:form>



<a href="<c:url value=""/>">Home</a>

</body>
</html>