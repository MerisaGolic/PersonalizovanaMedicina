<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ page session="false" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link href="<c:url value="/resources/detaljanPrikazPacijenta.css"/>" rel="stylesheet">
	<title>Symptom this!!!</title>
</head>
<body>
	<form action="dijagnosticiranje" method="post">	
		<input id="dijagnosticiranje" type="submit" value="Dijagnosticiranje">
	</form>
	<a id="odjava" href="odjava">Odjava</a>
	<form action="spisakPacijenata" method="post">	
		<input id="sp" type="submit" value="Spisak pacijenata">
	</form>
	<BR>
		<BR>
		<BR>
	<div id="tabelaPacijenti">
	<table id="tabela" class="table-fill">
		<thead>
			<tr>
				<th class="text-left">Naziv dijagnoze</th>
				<th class="text-left">Opis dijagnoze</th>
				<th class="text-left">Datum dijagnoze</th>
				<th class="tex-left">Detaljan pregled</th>
			</tr>
		</thead>
		<tbody class="table-hover">
			<c:forEach items="${detaljanPrikazForm}" var="dijagnoza">
				
					<tr>
						<td class="text-left"><c:out value="${dijagnoza.naziv}" /></td>
						<td class="text-left"><c:out value="${dijagnoza.opis}" /></td>
					    <td class="text-left"><c:out value="${dijagnoza.datum}" /></td>
					    <td class="text-center">
					    	<form:form action="racunanjeDoze" method="post" commandName="racunanjeDozeForm">
					    		<input type="submit" value="Izaberi">
					    		<form:input type="text" value="${dijagnoza.naziv}" class="naziv" path="naziv" />
					    		
							</form:form>					    		
					    </td>
					</tr>
				
			</c:forEach>
		</tbody>
	</table>
	</div>
</body>
</html>