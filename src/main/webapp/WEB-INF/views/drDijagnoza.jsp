<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ page session="false" %>
<!Doctype html>
<html>
<head>
	<meta charset="UTF-8">
	<link href="<c:url value="/resources/drDijagnoza.css" />" rel="stylesheet">
	<title>Symptom this!!!</title>
</head>
<body>

	<a id="spisak" href="spisakPacijenata">Spisak pacijenata</a>

	<a id="odjava" href="odjava">Odjava</a>
	
	<BR>
	<BR>
	<BR>
	<BR>
	<div id="lijeviDiv">
		<div class="form-style-10">
			<h1>Izbor simptoma!</h1>
			<form:form action="trazenjeDijagnoze" method="post" commandName="simptomiForm">		
				
				<div class="section"><span>1</span>Simptomi
				</div>
				<div class="inner-wrap">
					<label>Izaberite simptome: <input list="simptomiSelect" id="unosSimptoma" name="field1"></label>
					
					<jsp:useBean id="obj" class="ba.etf.personalizovanamedicina.Models.SviSimptomi" scope="page"/>
					<datalist id="simptomiSelect">
					    <c:forEach var="item" items="${obj.sviSimptomi}">
					     <option id="${item}" value="${item}">
					    </c:forEach>
					</datalist>
					<input type="button" value="Dodaj simptom" onClick="dodajSimptome()">
					<BR> <BR>
					<label>Izabrani simptomi:</label>
					
					<span id="velikiSpan">
					</span>
					<form:input type="text" value="" id="listaSimptoma" path="izabraniSimptomi" />
					<BR><BR>
					<label>Unesite prag postotka za prikaz dijagnoza:</label>
					<form:input type="number" value="0" min="0" max="100" path="postotak" />
					<p>${prazniSimptomi}</p>
					<input type="submit" value="Postavi dijagnozu" onclick="slanjeSimptoma()">
				</div>
				<script>
					function slanjeSimptoma(){
						var y = document.getElementById("listaSimptoma");
						var spanovi = document.getElementById("velikiSpan");
						console.log(spanovi.id);
						for(i = 1; i<spanovi.childNodes.length; i++){
							console.log(spanovi.childNodes[i].id);
							console.log(spanovi.childNodes[i].firstChild.innerHTML);
							y.value = y.value + spanovi.childNodes[i].firstChild.innerHTML + ',';
						}
						
						console.log(y.value);
					}
					function nekaFunkcija(id){
						
						var x = document.getElementById(id);
						x.remove();
					}
					function dodajSimptome()
					{
						var ulaz = document.getElementById("unosSimptoma").value;
						var lista = document.getElementById("simptomiSelect");
						
						var span = document.getElementById("velikiSpan");
						
						if(lista.options.namedItem(ulaz).value == ulaz){					
							
							var simptom = ulaz;
							
							if(document.getElementById(simptom+"Span") == null)
							{
								/*var y = document.getElementById("listaSimptoma");
								y.value = y.value + simptom + ",";
								console.log(y.value);*/
								var novi1 = document.createElement("span");
								novi1.id = simptom+"Span";
						
								novi1.innerHTML = "<span class=\"s1\">"+ simptom +"</span>" + "<span class=\"s2\" onclick=nekaFunkcija('"+simptom+"Span')>×</span>";
								
								span.appendChild(novi1);
							}
						}
					}
				</script>
			</form:form>
		</div>
	</div>
	
	<div id="desniDiv">
		<div class="form-style-10">
			<h1>Izbor dijagnoze i pacijenta!</h1>
			<form:form action="dodavanjeDijagnoze" method="post" commandName="dodavanjeDijagnozeForm">
				<div class="section"><span>2</span>Dijagnoze
				</div>
			 	<div class="inner-wrap">
			 	
					<label>Dijagnoze: 
					
					<jsp:useBean id="object" class="ba.etf.personalizovanamedicina.Models.SveDijagnoze" scope="page"/>
					<select onchange="prikazOpisa()" id="izborDijagnozeiOpisa">
						<option selected disabled>Izaberi:</option>
						<c:forEach var="item" items="${object.sveDijagnoze}" varStatus="status">
					     <option id="${item}" value="${object.sviOpisiDijagnoza[status.index]}">${item} ${object.sviPostoci[status.index]}%</option>
					    </c:forEach>
					</select> </label>
					
					<label>Opis dijagnoze:</label>
					
					<textarea id="opisDijagnoze" readonly></textarea>
					<BR><BR>
					<p>${novaDijagnoza }</p>
					<a id="novaDijagnoza" href="novaDijagnoza"> Dodaj novu dijagnozu</a>
				</div>
				<script type="text/javascript">
						function prikazOpisa()
						{
							var select = document.getElementById("izborDijagnozeiOpisa");
							var opis = select.options[select.selectedIndex].value;
							var nesto = document.getElementById("opisDijagnoze");	
							nesto.value =  opis;
							
							document.getElementById("skrivenaDijagnoza").value = select.options[select.selectedIndex].id;
						}
				</script>
				<div class="section"><span>3</span>Pacijent
				</div>
				<div class="inner-wrap">
				    <label>Odaberite spol:</label>
				
					<input type="radio" id="genderM" name="gender" value="M" onchange="izborSpolaM()"> Muško<br>
		 			<input type="radio" id="genderZ" name="gender" value="Z" onchange="izborSpolaZ()"> Žensko<br>
		 			<form:input type="text" value="" id="skriveniSpol" path="spol" />
		 			<form:input type="text" value="" id="skrivenaDijagnoza" path="dijagnoza" />
		 			<BR>
					<label>
						Datum rođenja:
						<form:input type="date" path="datumRodjenja" />
					</label>
					
					<label>
						Ime i prezime:
						<form:input list="pacijentSelect" type="text" path="imeiprezime" />
					</label>	
					<jsp:useBean id="pac" class="ba.etf.personalizovanamedicina.Models.SviPacijenti" scope="page"/>
					<datalist id="pacijentSelect">
					    <c:forEach var="item" items="${pac.sviPacijenti}">
					     <option id="${item}" value="${item}">
					    </c:forEach>
					</datalist>
					<p>${podaci}</p>
					<input type="submit" value="Dodaj dijagnozu">
				</div>
				<script>
					function izborSpolaM(){
						var izbor = document.getElementById("genderM").value;
						document.getElementById("skriveniSpol").value = izbor
						console.log(document.getElementById("skriveniSpol").value);
					}
					function izborSpolaZ(){
						var izbor = document.getElementById("genderZ").value;
						document.getElementById("skriveniSpol").value = izbor
						console.log(document.getElementById("skriveniSpol").value);
					}
				</script>
				
			</form:form>
		</div>
	</div>
</body>
</html>
