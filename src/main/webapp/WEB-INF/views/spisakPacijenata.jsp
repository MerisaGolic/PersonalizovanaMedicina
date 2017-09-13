<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ page session="false" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link href="<c:url value="/resources/spisakPacijenata.css"/>" rel="stylesheet">
	<title>Symptom this!!!</title>
</head>
<body>
	<form action="dijagnosticiranje" method="post">	
		<input id="dijagnosticiranje" type="submit" value="Dijagnosticiranje">
	</form>
	<a id="odjava" href="odjava">Odjava</a>
	<BR>
		<BR>
		<BR>
	

	<div id="tabelaPacijenti">
	<table id="tabela" class="table-fill">
		<thead>
			<tr>
				<th class="text-left">Ime i Prezime</th>
				<th class="text-left">Datum rođenja</th>
				<th class="text-left">Spol</th>
				<th class="tex-left">Detaljan pregled</th>
			</tr>
		</thead>
		<tbody class="table-hover">
			<c:forEach items="${spisakPacijenataForm}" var="pacijent">
				
					<tr>
						<td class="text-left"><c:out value="${pacijent.imeiprezime}" /></td>
						<td class="text-left"><c:out value="${pacijent.datumRodjenja}" /></td>
					    <td class="text-left"><c:out value="${pacijent.spol}" /></td>
					    <td class="text-center">
					    	<form:form action="detaljanPrikaz" method="post" commandName="detaljanPrikazForm">
					    		<input type="submit" value="Izaberi">
					    		<form:input type="text" value="${pacijent.imeiprezime}" class="ip" path="imeiprezime" />
					    		<form:input type="text" value="${pacijent.datumRodjenja}" class="dr" path="datumRodjenja" />
					    		<form:input type="text" value="${pacijent.spol}" class="s" path="spol" />
					    		
					    	</form:form>
					    </td>
					</tr>
				
			</c:forEach>
		</tbody>
	</table>
	</div>
</body>
</html>