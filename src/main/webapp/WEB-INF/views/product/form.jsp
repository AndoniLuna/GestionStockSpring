<%@include file="../includes/header.jsp" %>

<c:set var="titulo" value="Modificar Producto" scope="page"/>
<c:set var="boton" value="Modificar" scope="page"/>
<c:if test="${isNew}">
	<c:set var="titulo" value="Crear Producto" scope="page"/>
	<c:set var="boton" value="Crear" scope="page"/>
</c:if>
<h1>${titulo}</h1>

<form:form action="inventario/save" method="post" commandName="product">
  <c:if test="${!isNew}">
	  <form:label path="id">id:</form:label>
	  <form:input path="id" readonly="true"/>
	  <form:errors path="id" cssClass="error"/>
	  <br />
  </c:if>
  
  <form:label path="description">Descripción:</form:label>
  <form:input path="description" placeholder="Minimo 3 letras"/>
  <form:errors path="description" cssClass="error"/>
  <br />
  
  <form:label path="price">Precio:</form:label>
  <form:input path="price"/>
  <form:errors path="price" cssClass="error"/>
  <br />
  
  <input type="submit" align="center" value="${boton}">
</form:form>

<a href="<c:url value="/inventario"/>">&gt;&gt;Inventario</a>

<%@include file="../includes/footer.jsp" %>