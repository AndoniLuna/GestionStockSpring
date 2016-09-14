<%@ include file="/WEB-INF/views/include.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
  <title><fmt:message key="priceincrease.title"/></title>
  <style>
    .error { color: red; }
  </style>  
</head>
<body>
<h1><fmt:message key="priceincrease.title"/></h1>

<form:form method="post" commandName="priceIncrease">
  <table width="95%" bgcolor="f8f8ff" border="0" cellspacing="0" cellpadding="5">
    <tr>
      <td align="right" width="20%"><fmt:message key="increase"/> (%):</td>
        <td width="20%">
          <form:input path="porcentaje"/>
        </td>
        <td width="60%">
          <form:errors path="porcentaje" cssClass="error"/>
        </td>
    </tr>
  </table>
  <br>
  <input type="submit" align="center" value="Ejecutar">
</form:form>

<a href="<c:url value=""/>">Home</a>

</body>
</html>