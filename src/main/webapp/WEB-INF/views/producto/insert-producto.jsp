<%@ include file="../includes/header.jsp"%>



<h1>
	<fmt:message key="producto.crear.title" />
</h1>

<li><a href="inventario">Volver al inventario</a></li>
<br>
<br>

	<form:form commandName="product" action="insertar-producto.html"
		method="post">


<label for="id"><fmt:message key="producto.id" /></label>
<form:input path="id" placeholder="Id del articulo" />
<form:errors path="id" cssClass="error" />
<br>
<br>

<label for="description"><fmt:message key="producto.nombre" /></label>
<form:input path="description"
	placeholder="Escribe el nombre del articulo" />
<form:errors path="description" cssClass="error" />
<br>
<br>

<label for="price"><fmt:message key="producto.precio" /></label>
<form:input path="price" />
<form:errors path="price" cssClass="error" />
<br>
<br>
<br>
<input type="submit" value="<fmt:message key="producto.boton.crear"/>">

</form:form>

<%@ include file="../includes/footer.jsp"%>