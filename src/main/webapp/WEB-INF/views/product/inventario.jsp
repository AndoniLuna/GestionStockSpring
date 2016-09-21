
<%@ include file="../includes/header.jsp" %>

    <h1><fmt:message key="heading"/></h1>
    <p><fmt:message key="greeting"/> <c:out value="${fecha}"/></p>
    <h3>Productos</h3>
    <h4>${msg}</h4>
    <h4>${producto}</h4>
    
    <hr>
    	<ol>
    		<li><a href="<c:url value="incremento-precio.html"/>">Incrementar Precio</a></li>
    		<li><a href="<c:url value="inventario/nuevo"/>">Nuevo</a></li>
    	</ol>
	<hr>
    
    

   <c:forEach items="${products}" var="prod">
       <a href="<c:url value="inventario/detalle/${prod.id}"/>">
       <c:out value="${prod.description}"/>
       <i>$<c:out value="${prod.price}"/></i>
       </a>
       <a href="<c:url value="inventario/eliminar/${prod.id}"/>">[X]</a><br><br>
    </c:forEach>


<%@ include file="../includes/footer.jsp" %>
