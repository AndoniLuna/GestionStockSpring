<%@ include file="/WEB-INF/views/include.jsp" %>

<html>
  <head><title><fmt:message key="title"/></title></head>
  <body>
    <h1><fmt:message key="heading"/></h1>
    <p><fmt:message key="greeting"/> <c:out value="${fecha}"/></p>
    <h3>Productos</h3>
    
    <hr>
	    <ol>
	    	<li><a href="<c:url value="/"/>">Inicio</a></li>
	    	<li><a href="<c:url value="incremento-precio.html"/>">Incrementar Precio</a></li>
	    	<li><a href="<c:url value="inventario/nuevo"/>">Crear nuevo Producto</a></li>
	    </ol>
    <hr>
    
    <c:forEach items="${products}" var="prod">
      <c:out value="${prod.description}"/> <i>$<c:out value="${prod.price}"/></i><button>Borrar</button><br><br>
    </c:forEach>
  </body>
</html>