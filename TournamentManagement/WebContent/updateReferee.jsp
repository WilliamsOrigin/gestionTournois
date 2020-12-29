<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="beans.Arbitre"%>
<html lang="en">
<head>
<meta charset="UTF-8" />
<title>TournamentManagement</title>
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.8.2/css/all.css" />
<link rel="stylesheet" href="vendor/css/bootstrap.min.css" />
<link rel="stylesheet" href="vendor/css/mdb.min.css" />
<link rel="stylesheet" href="vendor/css/fontawesome.min.css" />
<link rel="stylesheet" href="vendor/css/animate.css" />
<link rel="stylesheet" href="assets/css/sidebar.css" />
<link rel="stylesheet" href="assets/css/listOfPl.css" />
</head>
<body>

	<div class="page-wrapper chiller-theme toggled">
		<jsp:include page="sidebar.jsp" />
		<!-- sidebar-wrapper  -->
		<main class="page-container">
			<div class="text-right h5 mr-3">
				<a href=""> <i class="fas fa-sync-alt p-2"></i>Refresh
				</a>
			</div>
			<div class="container-fluid">
				<h2 class="text-left">>> Edit / Delete a referee</h2>
				<div class="row">
					<div class="col-sm-8">
						<%
							String s_error = (String) request.getAttribute("searchError");
							if (s_error != null) {
						%>
						<div class="alert alert-danger" role="alert">
							<%
								out.print(s_error);
							%>
							<button type="button" class="close" data-dismiss="alert"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
						</div>
						<br />

						<%
							}
						%>
					</div>
					<div class="col-sm-4">
						<form action="findReferee" method="POST">
							<div class="input-group">
								<input class="form-control my-0 py-1 border-dark" type="text"
									name="search"
									placeholder="referee full name to fill the edit form" required/>
								<div class="input-group-append">
									<button class="btn btn-mdb-color mt-0 ml-0" id="basic-text1">
										<i class="fas fa-search text-grey" aria-hidden="true"></i>
									</button>
								</div>
							</div>
						</form>
					</div>
				</div>
				<br /> <br /> <br />
				<%
					String error = (String) request.getAttribute("error");
					Arbitre a = (Arbitre) request.getAttribute("referee");
					if (error != null) {
				%>
				<div class="alert alert-danger" role="alert">
					<%
						out.print(error);
					%>
					<button type="button" class="close" data-dismiss="alert"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<br />

				<%
					}
				%>
				<div class="row">
					<div class="col-sm-2"></div>
					<div class="col-sm-7">
						<form action="updateReferee" method="POST">
							<div class="container">
								<div class="row">
									<div class="col-sm-6">
										<input type="text" name="nom" placeholder="Referee name"
											class="form-control" value="${referee.getNom() }" />
										<input type="hidden" name="ida" value="${referee.getIdArbitre() }" />
									</div>
									<div class="col-sm-6">
										<select name="nationalite" class="form-control">
											<option value="">nationalite</option>
											<option value="Afghanistan">Afghanistan</option>
											<option value="Afrique_du_Sud">Afrique du Sud</option>
											<option value="Albanie">Albanie</option>
											<option value="Algerie">Alg�rie</option>
											<option value="Allemagne">Allemagne</option>
											<option value="Andorre">Andorre</option>
											<option value="Angola">Angola</option>
											<option value="Antigua-et-Barbuda">
												Antigua-et-Barbuda</option>
											<option value="Arabie_saoudite">Arabie saoudite</option>
											<option value="Argentine">Argentine</option>
											<option value="Armenie">Arm�nie</option>
											<option value="Australie">Australie</option>
											<option value="Autriche">Autriche</option>
											<option value="Azerbaidjan">Azerba�djan</option>
											<option value="Bahamas">Bahamas</option>
											<option value="Bahrein">Bahre�n</option>
											<option value="Bangladesh">Bangladesh</option>
											<option value="Barbade">Barbade</option>
											<option value="Belau">Belau</option>
											<option value="Belgique">Belgique</option>
											<option value="Belize">Belize</option>
											<option value="Benin">B�nin</option>
											<option value="Bhoutan">Bhoutan</option>
											<option value="Bielorussie">Bi�lorussie</option>
											<option value="Birmanie">Birmanie</option>
											<option value="Bolivie">Bolivie</option>
											<option value="Bosnie-Herz�govine">
												Bosnie-Herz�govine</option>
											<option value="Botswana">Botswana</option>
											<option value="Bresil">Br�sil</option>
											<option value="Brunei">Brunei</option>
											<option value="Bulgarie">Bulgarie</option>
											<option value="Burkina">Burkina</option>
											<option value="Burundi">Burundi</option>
											<option value="Cambodge">Cambodge</option>
											<option value="Cameroun">Cameroun</option>
											<option value="Canada">Canada</option>
											<option value="Cap-Vert">Cap-Vert</option>
											<option value="Chili">Chili</option>
											<option value="Chine">Chine</option>
											<option value="Chypre">Chypre</option>
											<option value="Colombie">Colombie</option>
											<option value="Comores">Comores</option>
											<option value="Congo">Congo</option>
											<option value="Cook">Cook</option>
											<option value="Coree_du_Nord">Cor�e du Nord</option>
											<option value="Coree_du_Sud">Cor�e du Sud</option>
											<option value="Costa_Rica">Costa Rica</option>
											<option value="Cote_Ivoire">C�te d'Ivoire</option>
											<option value="Croatie">Croatie</option>
											<option value="Cuba">Cuba</option>
											<option value="Danemark">Danemark</option>
											<option value="Djibouti">Djibouti</option>
											<option value="Dominique">Dominique</option>
											<option value="Egypte">�gypte</option>
											<option value="Emirats_arabes_unis">�mirats arabes
												unis</option>
											<option value="Equateur">�quateur</option>
											<option value="Erythree">�rythr�e</option>
											<option value="Espagne">Espagne</option>
											<option value="Estonie">Estonie</option>
											<option value="Etats-Unis">�tats-Unis</option>
											<option value="Ethiopie">�thiopie</option>
											<option value="Fidji">Fidji</option>
											<option value="Finlande">Finlande</option>
											<option value="France">France</option>
											<option value="Gabon">Gabon</option>
											<option value="Gambie">Gambie</option>
											<option value="Georgie">G�orgie</option>
											<option value="Ghana">Ghana</option>
											<option value="Gr�ce">Gr�ce</option>
											<option value="Grenade">Grenade</option>
											<option value="Guatemala">Guatemala</option>
											<option value="Guinee">Guin�e</option>
											<option value="Guinee-Bissao">Guin�e-Bissao</option>
											<option value="Guinee_equatoriale">Guin�e
												�quatoriale</option>
											<option value="Guyana">Guyana</option>
											<option value="Haiti">Ha�ti</option>
											<option value="Honduras">Honduras</option>
											<option value="Hongrie">Hongrie</option>
											<option value="Inde">Inde</option>
											<option value="Indonesie">Indon�sie</option>
											<option value="Iran">Iran</option>
											<option value="Iraq">Iraq</option>
											<option value="Irlande">Irlande</option>
											<option value="Islande">Islande</option>
											<option value="Isra�l">Isra�l</option>
											<option value="Italie">Italie</option>
											<option value="Jamaique">Jama�que</option>
											<option value="Japon">Japon</option>
											<option value="Jordanie">Jordanie</option>
											<option value="Kazakhstan">Kazakhstan</option>
											<option value="Kenya">Kenya</option>
											<option value="Kirghizistan">Kirghizistan</option>
											<option value="Kiribati">Kiribati</option>
											<option value="Koweit">Kowe�t</option>
											<option value="Laos">Laos</option>
											<option value="Lesotho">Lesotho</option>
											<option value="Lettonie">Lettonie</option>
											<option value="Liban">Liban</option>
											<option value="Liberia">Liberia</option>
											<option value="Libye">Libye</option>
											<option value="Liechtenstein">Liechtenstein</option>
											<option value="Lituanie">Lituanie</option>
											<option value="Luxembourg">Luxembourg</option>
											<option value="Macedoine">Mac�doine</option>
											<option value="Madagascar">Madagascar</option>
											<option value="Malaisie">Malaisie</option>
											<option value="Malawi">Malawi</option>
											<option value="Maldives">Maldives</option>
											<option value="Mali">Mali</option>
											<option value="Malte">Malte</option>
											<option value="Maroc">Maroc</option>
											<option value="Marshall">Marshall</option>
											<option value="Maurice">Maurice</option>
											<option value="Mauritanie">Mauritanie</option>
											<option value="Mexique">Mexique</option>
											<option value="Micronesie">Micron�sie</option>
											<option value="Moldavie">Moldavie</option>
											<option value="Monaco">Monaco</option>
											<option value="Mongolie">Mongolie</option>
											<option value="Mozambique">Mozambique</option>
											<option value="Namibie">Namibie</option>
											<option value="Nauru">Nauru</option>
											<option value="Nepal">N�pal</option>
											<option value="Nicaragua">Nicaragua</option>
											<option value="Niger">Niger</option>
											<option value="Nigeria">Nigeria</option>
											<option value="Niue">Niue</option>
											<option value="Norv�ge">Norv�ge</option>
											<option value="Nouvelle-Zelande">Nouvelle-Z�lande</option>
											<option value="Oman">Oman</option>
											<option value="Ouganda">Ouganda</option>
											<option value="Ouzbekistan">Ouzb�kistan</option>
											<option value="Pakistan">Pakistan</option>
											<option value="Panama">Panama</option>
											<option value="Papouasie-Nouvelle_Guinee">Papouasie
												- Nouvelle Guin�e</option>
											<option value="Paraguay">Paraguay</option>
											<option value="Pays-Bas">Pays-Bas</option>
											<option value="Perou">P�rou</option>
											<option value="Philippines">Philippines</option>
											<option value="Pologne">Pologne</option>
											<option value="Portugal">Portugal</option>
											<option value="Qatar">Qatar</option>
											<option value="Republique_centrafricaine">R�publique
												centrafricaine</option>
											<option value="Republique_dominicaine">R�publique
												dominicaine</option>
											<option value="Republique_tcheque">R�publique
												tch�que</option>
											<option value="Roumanie">Roumanie</option>
											<option value="Royaume-Uni">Royaume-Uni</option>
											<option value="Russie">Russie</option>
											<option value="Rwanda">Rwanda</option>
											<option value="Saint-Christophe-et-Nieves">
												Saint-Christophe-et-Ni�v�s</option>
											<option value="Sainte-Lucie">Sainte-Lucie</option>
											<option value="Saint-Marin">Saint-Marin</option>
											<option value="Saint-Si�ge">Saint-Si�ge, ou
												leVatican</option>
											<option value="Saint-Vincent-et-les_Grenadines">
												Saint-Vincent-et-les Grenadines</option>
											<option value="Salomon">Salomon</option>
											<option value="Salvador">Salvador</option>
											<option value="Samoa_occidentales">Samoa
												occidentales</option>
											<option value="Sao_Tome-et-Principe">Sao
												Tom�-et-Principe</option>
											<option value="Senegal">S�n�gal</option>
											<option value="Seychelles">Seychelles</option>
											<option value="Sierra_Leone">Sierra Leone</option>
											<option value="Singapour">Singapour</option>
											<option value="Slovaquie">Slovaquie</option>
											<option value="Slovenie">Slov�nie</option>
											<option value="Somalie">Somalie</option>
											<option value="Soudan">Soudan</option>
											<option value="Sri_Lanka">Sri Lanka</option>
											<option value="Sued">Su�de</option>
											<option value="Suisse">Suisse</option>
											<option value="Suriname">Suriname</option>
											<option value="Swaziland">Swaziland</option>
											<option value="Syrie">Syrie</option>
											<option value="Tadjikistan">Tadjikistan</option>
											<option value="Tanzanie">Tanzanie</option>
											<option value="Tchad">Tchad</option>
											<option value="Thailande">Tha�lande</option>
											<option value="Togo">Togo</option>
											<option value="Tonga">Tonga</option>
											<option value="Trinite-et-Tobago">Trinit�-et-Tobago</option>
											<option value="Tunisie">Tunisie</option>
											<option value="Turkmenistan">Turkm�nistan</option>
											<option value="Turquie">Turquie</option>
											<option value="Tuvalu">Tuvalu</option>
											<option value="Ukraine">Ukraine</option>
											<option value="Uruguay">Uruguay</option>
											<option value="Vanuatu">Vanuatu</option>
											<option value="Venezuela">Venezuela</option>
											<option value="Viet_Nam">Vi�t Nam</option>
											<option value="Yemen">Y�men</option>
											<option value="Yougoslavie">Yougoslavie</option>
											<option value="Zaire">Za�re</option>
											<option value="Zambie">Zambie</option>
											<option value="Zimbabwe">Zimbabwe</option>
										</select>
									</div>
								</div>
								<br />
								<div class="row">
									<div class="col-sm-12">
										<textarea name="description" class="form-control">
					                      	${referee.getDescription()}
					                    </textarea>
									</div>
								</div>
								<div class="row btn-part">
									<div class="col-sm-4"></div>
									<div class="col-sm-4">
										<button type="submit" class="btn btn-mdb-color btn-block">
											update
										</button>
									</div>
								</div>
							</div>
						</form>
						<%
							if (a != null) {
						%>
						<form action="deleteReferee" method="POST">
							<input type="hidden" name="idArbitre"
								value="${referee.getIdArbitre() }" />
							<button type="submit" class="btn btn-danger btn-block">
								delete</button>
						</form>
						<%
							}
						%>
					</div>
				</div>
			</div>
			<br /> <br />
		</main>
	</div>
	<script src="vendor/js/jquery.min.js"></script>
	<script src="vendor/js/popper.min.js"></script>
	<script src="vendor/js/bootstrap.min.js"></script>
	<script src="vendor/js/mdb.min.js"></script>
	<script src="vendor/js/wow.min.js"></script>
	<script src="vendor/js/fontawesome.min.js"></script>
	<script src="assets/js/index.js"></script>
	<script>
		new WOW().init();	
	</script>
</body>
</html>
