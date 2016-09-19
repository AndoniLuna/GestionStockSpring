<%@ include file="/WEB-INF/views/include.jsp" %>

<html>
  <head><title><fmt:message key="title"/></title></head>
  <body>
    <h1><fmt:message key="heading"/></h1>
    <p><fmt:message key="greeting"/> <c:out value="${fecha}"/></p>
   
    <hr>
    	<ol>
    		<li><a href="incremento-precio.html">Incrementar Precio</a></li>
    		<li><a href="inventario/nuevo">Nuevo</a></li>
    	</ol>	
    <hr>
   
    ${msg}
   
    <h2>Productos</h2> 
    <c:forEach items="${products}" var="prod">
    	<a href="inventario/detalle/${prod.id}">
      		<c:out value="${prod.description}"/> 
      		<i>$<c:out value="${prod.price}"/></i>
      	</a>
      	<a href="inventario/eliminar/${prod.id}">
      		[X]
      	</a>      
      	      
      <br><br>
    </c:forEach>
  </body>
</html>