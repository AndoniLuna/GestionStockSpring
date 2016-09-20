<%@include file="../includes/header.jsp" %>
<h1>
	<fmt:message key="heading" />
</h1>
<p>
	<fmt:message key="greeting" />
	<c:out value="${fecha}" />
</p>
<h3>Productos</h3>

<hr>
<ol>
	<li><a href="<c:url value="/"/>">Inicio</a></li>
	<li><a href="<c:url value="incremento-precio.html"/>">Incrementar
			Precio</a></li>
	<li><a href="<c:url value="inventario/nuevo"/>">Crear nuevo
			Producto</a></li>
</ol>
<hr>

${msg}
<br />
<c:forEach items="${products}" var="prod">
	<c:out value="${prod.description}" />
	<i>$<c:out value="${prod.price}" /></i>&nbsp;&nbsp;<a
		href="<c:url value="inventario/detalle/${prod.id}"/>">Ver Detalle</a>&nbsp;&nbsp;<a
		href="<c:url value="inventario/borrar/${prod.id}"/>">Borrar</a>
	<br>
	<br>
</c:forEach>

<%@include file="../includes/footer.jsp" %>