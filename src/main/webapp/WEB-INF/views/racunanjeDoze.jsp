<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ page session="false" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link href="<c:url value="/resources/racunanjeDoze.css"/>" rel="stylesheet">
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
		<BR>
		<BR>
	<div id="tabelaDijagnoze">
	<table id="tabela" class="table-fill">
		<thead>
			<tr>
				<th class="text-left">Naziv dijagnoze</th>
				<th class="text-left">Nivo šećera u krvi</th>
				<th class="text-left">Broj eritrocita</th>
				<th class="text-left">Broj leukocita</th>
				<th class="text-left">Broj trombocita</th>
				<th class="text-left">Proračun</th>
				<th class="text-left">Naziv lijeka</th>
				<th class="text-left">Doza lijeka(mg)</th>
				
			</tr>
		</thead>
		<tbody class="table-hover">
			
				<form:form action="prikazDoze" method="post" commandName="prikazDozeForm">
					<tr>
						<td class="text-left">${nazivDijagnoze}</td>
						<td class="text-left">
							<form:input type="text" value="${secer}" class="unos" path="secer"/>
						</td>
						<td class="text-left">
							<form:input type="text" value="${eritrociti}" class="unos" path="eritrociti"/>
						</td>
						<td class="text-left">
							<form:input type="text" value="${leukociti}" class="unos" path="leukociti"/>
						</td>
						<td class="text-left">
							<form:input type="text" value="${trombociti}" class="unos" path="trombociti"/>
						</td>
					    <td class="text-center">
					    		<input type="submit" value="Izaberi">    		
					    </td>
					    <td class="text-left">${nazivLijeka}</td>
						<td class="text-left">${doza}</td>
					    
					</tr>
				</form:form>
			
		</tbody>
	</table>
	</div>
</body>
</html>