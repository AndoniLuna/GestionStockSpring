<%@ include file="../includes/header.jsp"%>


<a href="inventario"> &lt; &lt;Volver inventario</a>
<br>

<!-- declaramos una variable 'titulo' con un valor -->
<c:set var="titulo" value="Modificar Producto" scope="page"/>
<c:set var="boton" value="Modificar" scope="page"/>
<c:if test="${isNew}">
	<c:set var="titulo" value="Crear Producto" scope="page"/>
	<c:set var="boton" value="Crear" scope="page"/>
</c:if>
<h1>${titulo}</h1>

<form:form action="inventario/save" method="post" commandName="product">

	<c:if test="${!isNew}">   <!-- si el producto es nuevo no sale el 'id' -->
		<form:label path="id" >id:</form:label> 
		<form:input path="id" readonly="true"/>  <!-- con readonly no se puede cambiar el valor solo leerlo -->
		<form:errors path="id" cssClass="error"/>
		</c:if>
	
	<br><br>
	
	<form:label path="description">Descripción:</form:label>
	<form:input path="description" placeholder="Minimo 3 letras"/>
	<form:errors path="description" cssClass="error"/>
	<br><br>
	
	<form:label path="price">Precio:</form:label>
	<form:input path="price"/>
	<form:errors path="price" cssClass="error"/>
	<br><br>
	
	<input type="submit" value="${boton}" >	
	
</form:form>



<%@ include file="../includes/footer.jsp"%>