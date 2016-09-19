<%@ include file="/WEB-INF/views/include.jsp" %>

<html>
  <head><title><fmt:message key="title"/></title></head>
  <body>
    <h1><fmt:message key="heading"/></h1>
    <p><fmt:message key="greeting"/> <c:out value="${fecha}"/></p>
    <h3>Productos</h3>
    
    <hr>
    	<ol>
    		<li><a href="<c:url value="incremento-precio.html"/>">Incrementar Precio</a></li>
    		<li><a href="<c:url value="/inventario/nuevo"/>">CREAR NUEVO PRODUCTO</a></li>
    	
    	</ol>
    	
    <hr>
    
    ${msg}
    
    <h2>Productos</h2>
    <c:forEach items="${products}" var="prod">
       <a href="<c:url  value="inventario/detalle/${prod.id }"/>">
	       <c:out value="${prod.description}"/>
	       <i>$<c:out value="${prod.price}"/></i>
       </a>
       <a href="<c:url value="inventario/eliminar/${prod.id }"/>">
       		[X]
       </a>
       
        <br><br>
    </c:forEach>
  </body>
</html>