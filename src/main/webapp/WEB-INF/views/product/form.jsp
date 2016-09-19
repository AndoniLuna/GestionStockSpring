<%@ include file="/WEB-INF/views/include.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
  <title>Producto | Nuevo</title>
  <style>
    .error { color: red; }
  </style>  
</head>
<body>
<h1>Crear Producto</h1>

<form:form action="nuevo" method="post" commandName="product">
  
  <form:label path="id">id:</form:label>
  <form:input path="id"/>
  <form:errors path="id" cssClass="error"/>
  <br />
  
  <form:label path="description">Descripción:</form:label>
  <form:input path="description" placeholder="Minimo 3 letras"/>
  <form:errors path="description" cssClass="error"/>
  <br />
  
  <form:label path="price">Precio:</form:label>
  <form:input path="price"/>
  <form:errors path="price" cssClass="error"/>
  <br />
  
  <input type="submit" align="center" value="Crear">
</form:form>

<a href="<c:url value="/inventario"/>">&gt;&gt;Inventario</a>

</body>
</html>