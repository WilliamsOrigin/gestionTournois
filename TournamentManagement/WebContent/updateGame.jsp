<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="beans.TMatch"%>
<html lang="en">
<head>
<meta charset="UTF-8" />
<title>TournamentManagement</title>
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.8.2/css/all.css" />
<link rel="stylesheet" href="vendor/css/bootstrap.min.css" />
<link rel="stylesheet" href="vendor/css/mdb.min.css" />
<link rel="stylesheet" href="vendor/css/datatables.min.css" />
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
				<div class="row">
					<div class="col-sm-4">
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
						<form action="findGame" method="POST">
							<div class="input-group">
								<input class="form-control my-0 py-1 border-dark" type="number"
									name="search" min="1"
									placeholder="game ID" required />
								<div class="input-group-append">
									<button class="btn btn-mdb-color mt-0 ml-0" id="basic-text1">
										<i class="fas fa-search text-grey" aria-hidden="true"></i>
									</button>
								</div>
							</div>
						</form>
					</div>
				</div>
				<br />
				<h2 class="text-left">>> Edit / Delete a game</h2>
				<br />
				<%
					TMatch game = (TMatch) request.getAttribute("game");
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
						<form action="updateGame" method="POST">
							<div class="container-fluid">
								<div class="row">
									<div class="col-sm-4">
										<select name="statut" class="form-control text-center"
											required>
											<option value="">Statut</option>
											<option value="0">En cours</option>
											<option value="1">Terminé</option>
										</select> <input type="hidden" name="idmatch" value="${game.idMatch }" />
									</div>
									<div class="col-sm-4">
										<input type="date" name="jour" min="2020-12-23"
											max="2021-02-11" class="form-control text-center" />
									</div>
									<div class="col-sm-2 pr-0">
										<input type="time" name="heureDeb" min="06:00" max="20:00"
											class="form-control" value="${game.heuredb }" />
									</div>
									<div class="col-sm-2 pl-0">
										<input type="time" name="heureFin" min="06:00" max="20:00"
											class="form-control" value="${game.heurefin }" />
									</div>
									<br /> <br />
								</div>
								<div class="row pt-4">
									<div class="col-sm-4"></div>
									<div class="col-sm-2 pr-0">
										<input type="number" placeholder="Court" name="court"
											class="form-control" value="${game.court }" min="1" max="23" />
									</div>
									<div class="col-sm-2 pl-0">
										<input type="text" name="score" placeholder="Score (0-0)"
											class="form-control" value="${game.score  }" />
									</div>
								</div>
								<div class="row pt-5">
									<div class="col-sm-2"></div>
									<div class="col-sm-8">
										<button type="submit" class="btn btn-mdb-color btn-block">
											Update</button>
									</div>
								</div>
							</div>
						</form>
						<%
							if (game != null) {
						%>
						<form action="deleteGame" method="POST">
							<input type="hidden" name="idmatch" value="${game.idMatch }" />
							<button type="submit" class="btn btn-danger btn-block">
								delete</button>
						</form>
						<%
							}
						%>
					</div>
				</div>
				<br /><br />
				<h2 class="text-left">>> Games list</h2>
				<br />
				<div class="row">
					<div class="col-sm-1"></div>
					<div class="col-sm-10">
						<table id="dtBasicExample"
							class="table table-striped table-bordered text-center"
							cellspacing="0" width="100%">
							<thead>
								<tr>
									<th class="th-sm">Identifiant</th>
									<th class="th-sm">Category</th>
									<th class="th-sm">Statut</th>
									<th class="th-sm">Nom Arbitre</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${games}" var="jeu">
									<tr>
										<td>${jeu.idMatch }</td>
										<td>${jeu.getCustomCategory()}</td>
										<td>${jeu.getCustomStatut()}</td>
										<td>${jeu.getArbitre().getNom() }</td>
									</tr>
								</c:forEach>
							</tbody>
							<tfoot>
								<tr>
									<th>Identifiant</th>
									<th>Category</th>
									<th>Statut</th>
									<th>Nom Arbitre</th>
								</tr>
							</tfoot>
						</table>
					</div>
				</div>
			</div>
			<br /> <br />
		</main>
	</div>
	<script src="vendor/js/jquery.min.js"></script>
	<script src="vendor/js/popper.min.js"></script>
	<script src="vendor/js/bootstrap.min.js"></script>
	<script src="vendor/js/datatables.min.js"></script>
	<script src="vendor/js/mdb.min.js"></script>
	<script src="vendor/js/wow.min.js"></script>
	<script src="vendor/js/fontawesome.min.js"></script>
	<script src="assets/js/index.js"></script>
	<script>
		$(document).ready(function() {
			$("#dtBasicExample").DataTable();
			$(".dataTables_length").addClass("bs-select");
			new WOW().init();
		});
	</script>
</body>
</html>
