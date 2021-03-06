﻿<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<!Doctype html>
<html>
<head>
	<meta charset="UTF-8">
	<link href="<c:url value="/resources/home.css" />" rel="stylesheet">
	<title>Symptom this!!!</title>
</head>
<body>


	<div id="okvir">
		<div id="logo">
			<img src="<c:url value="/resources/images/logo.jpg" />" alt="LOGO">
		</div>
		<hr>
	<div id="opis">
		<p>Dobro došli u aplikaciju Symptom this!</p>
		<BR>
		<p>Ova aplikacija vam omogućava jednostavno dijagnosticiranje bolesti. Sve što je potrebno da 
			uradite jeste da unesete simptome. Nakon što to uradite izvršiće 
			se pretraga bolesti u bazi podataka, te će se pokazati one koje odgovaraju unesenim simptomima.
		</p>

		<BR>
		<P> Prve na listi će biti prikazane dijagnoze koje imaju najveći postotak podudaranja sa unesenim simptomima, 
		    a potom i ostale dijagnoze u opadajućem poretku po postotku podudaranja sa unesenim simptomima. 
		    Pored svake dijagnoze u listi će biti prikazan i postotak poklapanja unesenih simptoma sa stvarnim 
		    simptomima neke bolesti koji se nalaze u bazi podataka. Odabirom 
			jedne od dijagnoza biće prikazan detaljan opis te dijagnoze.
		</P>
	</div>
	<div id="buttoni">
		<BR><BR><BR><BR><BR>
		
		<form action="login" method="post">
			<input class="dugme" type="submit" value="Log in">
		</form>
		
		<BR><BR><BR><BR><BR>
		<form action="izvrsiDijagnozu" method="post">
			<input class="dugme" type="submit" value="Izvrši dijagnozu">
		</form>
		
	</div>
	</div>
	<hr>
	
</body>
</html>
