<%@ include file="../includes/header.jsp"%>


    <h1><fmt:message key="heading"/></h1>
    <p><fmt:message key="greeting"/> <c:out value="${fecha}"/></p>
    <h3>Productos</h3>
    <c:forEach items="${products}" var="prod">
      <c:out value="${prod.description}"/> <i>$<c:out value="${prod.price}"/></i>
      <a href="inventario/detalle/${prod.id}">&nbspDetalle&nbsp</a>
      <a href="inventario/eliminar/${prod.id}">&nbspEliminar&nbsp</a><br><br>
    </c:forEach>
    <hr>
    <ol>
	    <li><a href="incremento-precio.html">Incrementar Precio</a></li>
	    <li><a href="inventario/nuevo">Nuevo</a></li>
    </ol>
    <hr>
    ${msg}
    
<%@ include file="../includes/footer.jsp"%>