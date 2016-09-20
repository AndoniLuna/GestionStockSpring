<%@ include file="../includes/header.jsp"%>


	<h1>Crear producto</h1>
	
	<form:form action="nuevo" method="post" commandName="product">
	  <table width="95%" bgcolor="f8f8ff" border="0" cellspacing="0" cellpadding="5">
	    <tr>
	      <td align="right" width="20%"></td>
	        <td width="20%">
	       		<h4>ID</h4>
	         	<form:input path="id"/><br>
	        	<form:errors path="id" cssClass="error"/><br>
	         	<h4>DESCRIPTION</h4>
	         	<form:input path="description" placeholder="Minimo tres letras"/><br>
	         	<form:errors path="description" cssClass="error"/><br>
	         	<h4>PRICE</h4>
	         	<form:input path="price"/><br>
	         	<form:errors path="price" cssClass="error"/>
	        </td>
	    </tr> 
	  </table>
	  <br>
	  <input type="submit" align="center" value="Crear">
	</form:form>
	
	<br>
	
 <a href="inventario">&lt;&lt;Volver a inventario</a><br><br>

<%@ include file="../includes/footer.jsp"%>