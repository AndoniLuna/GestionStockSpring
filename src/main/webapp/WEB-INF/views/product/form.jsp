<%@ include file="../includes/header.jsp"%>


<a href="<c:url value="/inventario"/>"> &lt; &lt;Volver inventario</a>
<br>

<h1>Crear Producto</h1>

<form:form action="inventario/nuevo" method="post" commandName="product">

	<form:label path="id" >id:</form:label> 
	<form:input path="id" disabled="true"/>
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
	
	${isNew}
	
	<c:set var="boton" value="Modificar" />
	<c:if test="${isNew}">	
		<c:set var="boton" value="Crear" />
	</c:if>
	
	<input type="submit" value="<c:out value="${boton}"/>" >	
	
</form:form>



<%@ include file="../includes/footer.jsp"%>