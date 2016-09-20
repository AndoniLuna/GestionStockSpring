
<%@ include file="../includes/header.jsp" %>

    <h1><fmt:message key="heading"/></h1>
    <p><fmt:message key="greeting"/> <c:out value="${fecha}"/></p>
    <h3>Productos</h3>
    
    <hr>
	    <ol>
		    <li><a href="<c:url value="incremento-precio.html"/>">Incrementar Precio</a></li>
		   <li><a href="<c:url value="insertar-producto.html"/>">Nuevo articulo</a></li>
	    </ol>
    <hr>
    
    <c:forEach items="${products}" var="prod">
     <a href="detalle-producto.html/${prod.id}"> <c:out value="${prod.description}"/></a> <i>$<c:out value="${prod.price}"/></i>
      <a href="eliminar-producto/${prod.id}">ELIMINAR</a><br><br>
    </c:forEach>
<%@ include file="../includes/footer.jsp" %>