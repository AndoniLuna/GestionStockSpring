<%@ include file="/WEB-INF/views/include.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
  <title>Producto | Nuevo </title>
  <style>
    .error { color: red; }
  </style>  
</head>
<body>

<a href="<c:url value="/inventario"/>"> &lt; &lt;Volver inventario</a>
<br>

<h1>Crear Producto</h1>

<form:form action="nuevo" method="post" commandName="product">

	<form:label path="id" >id:</form:label> 
	<form:input path="id" type="disabled"/>
	<form:errors path="id" cssClass="error"/>
	<br><br>
	
	<form:label path="description">Descripción:</form:label>
	<form:input path="description" placeholder="Minimo 3 letras"/>
	<form:errors path="description" cssClass="error"/>
	<br><br>
	
	<form:label path="price">Precio:</form:label>
	<form:input path="price"/>
	<form:errors path="price" cssClass="error"/>
	<br><br>
	
	<input type="submit" value="Crear" action="insertar" method="post">
</form:form>




</body>
</html>