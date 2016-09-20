<%@ include file="../includes/header.jsp" %>

    <h1><fmt:message key="heading"/></h1>
    <p><fmt:message key="greeting"/> <c:out value="${fecha}"/></p>
    <hr>
    <ol>
		<li><a href="<c:url value="incremento-precio.html"/>">Incrementar Precio</a></li>
		<li><a href="<c:url value="inventario/nuevo"/>">Nuevo Producto</a></li>
	</ol>
	<hr>
	${msg}
	<h2>Productos</h2>
    <c:forEach items="${products}" var="prod">
      <a href="inventario/detalle/${prod.id}"><c:out value="${prod.description}"/></a>
       <i>$<c:out value="${prod.price}"/></i>
        <a href="inventario/eliminar/${prod.id}">[X]</a>
        <br><br>
    </c:forEach>    
	<a href="<c:url value="/"/>">Home</a>
	
<%@ include file="../includes/footer.jsp" %>