<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
				<h2 class="text-left">>> Simple Game (Men / Women)</h2>
				<br /> <br />
				<%
					String error = (String) request.getAttribute("error");
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
					<div class="col-sm-11">
						<form action="createSimpleGame" method="POST">
							<div class="container-fluid">
								<div class="row">
									<div class="col-sm-4">
										<select name="categorie" class="form-control text-center"
											required>
											<option value="">Categorie</option>
											<option value="1">Simple Messieurs</option>
											<option value="2">Simple Dames</option>
										</select>
									</div>
									<div class="col-sm-4">
										<input type="date" name="jour" min="2020-12-23"
											max="2021-02-11" class="form-control text-center" required />
									</div>
									<div class="col-sm-2 pr-0">
										<input type="time" name="heureDeb" min="06:00" max="20:00"
											class="form-control" value="06:30" required />
									</div>
									<div class="col-sm-2 pl-0">
										<input type="time" name="heureFin" min="06:00" max="20:00"
											class="form-control" value="07:30" required />
									</div>
									<br /> <br />
								</div>
								<div class="row pt-4">
									<div class="col-sm-4"></div>
									<div class="col-sm-4">
										<select name="arbitre" class="form-control text-center"
											required>
											<option value="">Arbitre</option>
											<c:forEach items="${referees}" var="referee">
												<option value="${referee.idArbitre }">${referee.nom }</option>
											</c:forEach>
										</select>
									</div>
								</div>
								<div class="row pt-4">
									<div class="col-sm-2 pr-0">
										<input type="number" placeholder="Court" name="court"
											class="form-control" min="1" max="23" required />
									</div>
									<div class="col-sm-2 pl-0">
										<input type="text" name="score" placeholder="Score (0-0)"
											class="form-control" required />
									</div>
									<div class="col-sm-4">
										<select name="joueur" class="form-control text-center"
											required>
											<option value="">Player</option>
											<c:forEach items="${players}" var="player">
												<option value="${player.idJoueur }">${player.nom }</option>
											</c:forEach>
										</select>
									</div>
									<div class="col-sm-4">
										<select name="adversaire" class="form-control text-center"
											required>
											<option value="">Opponent</option>
											<c:forEach items="${players}" var="player">
												<option value="${player.idJoueur }">${player.nom }</option>
											</c:forEach>
										</select>
									</div>
								</div>
								<div class="row pt-4">
									<div class="col-sm-2"></div>
									<div class="col-sm-8">
										<button type="submit" class="btn btn-mdb-color btn-block">
											Create</button>
									</div>
								</div>
							</div>
						</form>
					</div>
				</div>
				<br /> <br />
				<h2 class="text-left">>> Double Game (Men / Women / mixed)</h2>
				<br /> <br />
				<%
					String d_error = (String) request.getAttribute("d_error");
				if (d_error != null) {
				%>
				<div class="alert alert-danger" role="alert">
					<%
						out.print(d_error);
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
					<div class="col-sm-11">
						<form action="createDoubleGame" method="POST">
							<div class="container-fluid">
								<div class="row">
									<div class="col-sm-4">
										<select name="dcategorie" class="form-control text-center"
											required>
											<option value="">Categorie</option>
											<option value="3">Double Messieurs</option>
											<option value="4">Double Dames</option>
											<option value="5">Double mixte</option>
										</select>
									</div>
									<div class="col-sm-4">
										<input type="date" name="djour" min="2020-12-23"
											max="2021-02-11" class="form-control text-center" required />
									</div>
									<div class="col-sm-2 pr-0">
										<input type="time" name="dheureDeb" min="06:00" max="20:00"
											class="form-control" value="06:30" required />
									</div>
									<div class="col-sm-2 pl-0">
										<input type="time" name="dheureFin" min="06:00" max="20:00"
											class="form-control" value="07:30" required />
									</div>
									<br /> <br />
								</div>
								<div class="row pt-4">
									<div class="col-sm-2"></div>
									<div class="col-sm-3">
										<select name="darbitre" class="form-control text-center"
											required>
											<option value="">Arbitre</option>
											<c:forEach items="${referees}" var="referee">
												<option value="${referee.idArbitre }">${referee.nom }</option>
											</c:forEach>
										</select>
									</div>
									<div class="col-sm-3 pr-0">
										<select name="djoueur" class="form-control text-center"
											required>
											<option value="">Player</option>
											<c:forEach items="${players}" var="player">
												<option value="${player.idJoueur }">${player.nom }</option>
											</c:forEach>
										</select>
									</div>
									<div class="col-sm-3 pl-0">
										<select name="j_equipier" class="form-control text-center"
											required>
											<option value="">Partner</option>
											<c:forEach items="${players}" var="player">
												<option value="${player.idJoueur }">${player.nom }</option>
											</c:forEach>
										</select>
									</div>
								</div>
								<div class="row pt-4">
									<div class="col-sm-2 pr-0">
										<input type="number" placeholder="Court" name="dcourt"
											class="form-control" min="1" max="23" required />
									</div>
									<div class="col-sm-2 pl-0">
										<input type="text" name="dscore" placeholder="Score (0-0)"
											class="form-control" required />
									</div>
									<div class="col-sm-4 pr-0">
										<select name="dadversaire" class="form-control text-center"
											required>
											<option value="">Opponent</option>
											<c:forEach items="${players}" var="player">
												<option value="${player.idJoueur }">${player.nom }</option>
											</c:forEach>
										</select>
									</div>
									<div class="col-sm-4 pl-0">
										<select name="o_equipier" class="form-control text-center"
											required>
											<option value="">Partner</option>
											<c:forEach items="${players}" var="player">
												<option value="${player.idJoueur }">${player.nom }</option>
											</c:forEach>
										</select>
									</div>
								</div>
								<div class="row pt-4">
									<div class="col-sm-2"></div>
									<div class="col-sm-8">
										<button type="submit" class="btn btn-mdb-color btn-block">
											Create</button>
									</div>
								</div>
							</div>
						</form>
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
