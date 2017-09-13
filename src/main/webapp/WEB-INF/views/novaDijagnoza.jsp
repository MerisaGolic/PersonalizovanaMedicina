<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ page session="false" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link href="<c:url value="/resources/novaDijagnoza.css"/>" rel="stylesheet">
	<title>Symptom this!!!</title>
</head>
<body>
	<div class="form-style-10">
			<h1>Dodavanje nove dijagnoze!</h1>
			<form:form action="dodajNovuDijagnozu" method="post" commandName="novaDijagnozaForm">		
				
				<div class="section"><span>1</span>Dijagnoza
				</div>
				<div class="inner-wrap">
					<label>Unesite naziv nove dijagnoze:</label>
					<form:input type="text" value="" path="naziv" />	
					<BR><BR>
					<label>Unesite opis nove dijagnoze:</label>
					<form:input type="text" value="" path="opis" />	
					
				</div>
				<div class="section"><span>2</span>Lijek
				</div>
				<div class="inner-wrap">
					<label>Unesite naziv lijeka:</label>
					<form:input type="text" value="" path="nazivLijeka" list="lijekoviSelect"/>	
					<jsp:useBean id="obj" class="ba.etf.personalizovanamedicina.Models.SviLijekovi" scope="page"/>
					<datalist id="lijekoviSelect">
					    <c:forEach var="item" items="${obj.sviLijekovi}">
					     <option id="${item}" value="${item}">
					    </c:forEach>
					</datalist>
					<BR><BR>
					<label>Unesite standardnu dozu lijeka:</label>
					<form:input type="text" value="" path="doza" />
				</div>
				<p>${nova}</p>
				<input id="dugme" type="submit" value="Dodaj dijagnozu">
					<BR> <BR>
				</form:form>
				</div>
				
	
</body>
</html>