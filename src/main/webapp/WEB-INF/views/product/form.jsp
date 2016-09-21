<%@include file="../includes/header.jsp" %>

<h1>Crear Producto</h1>

<form:form action="inventario/nuevo" method="post" commandName="product">
  
  <form:label path="id">id:</form:label>
  <form:input path="id" readonly="true"/>
  <form:errors path="id" cssClass="error"/>
  <br />
  
  <form:label path="description">Descripción:</form:label>
  <form:input path="description" placeholder="Minimo 3 letras"/>
  <form:errors path="description" cssClass="error"/>
  <br />
  
  <form:label path="price">Precio:</form:label>
  <form:input path="price"/>
  <form:errors path="price" cssClass="error"/>
  <br />
  
  <input type="submit" align="center" value="Crear">
</form:form>

<a href="<c:url value="/inventario"/>">&gt;&gt;Inventario</a>

<%@include file="../includes/footer.jsp" %>