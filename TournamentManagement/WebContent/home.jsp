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
			<br />
			<div class="container-fluid">
				<h2 class="text-left">>> Matchs simple</h2>
				<br />
				<div class="row">
					<c:forEach items="${infoSimpleGame}" var="games">
						<div class="col-sm-6">
							<div class="jumbotron text-center hoverable p-4">
								<div class="row text-center">
									<div class="col-sm-4">
										<img src="./assets/img/${games.value.get(0).getImage()}"
											class="img-fluid rounded-circle head-img" alt="" />
										<p class="text-center">${games.value.get(0).getNom()}</p>
									</div>
									<div class="col-sm-4">
										<p class="text-center mt-2 text-black-50 h1">${games.key.getScore() }</p>
									</div>
									<div class="col-sm-4">
										<img src="./assets/img/${games.value.get(1).getImage()}"
											class="img-fluid rounded-circle head-img" alt="" />
										<p class="text-center">${games.value.get(1).getNom()}</p>
									</div>
								</div>
								<div class="row">
									<div class="col-sm-8">
										<img src="./assets/img/court.jpg" class="img-fluid rounded" />
									</div>
									<div class="col-sm-4 pt-4 text-justify">
										<p>
											Statut:
											<c:if test="${games.key.getStatut() == 0 }">
												<strong class="text-info">En attante</strong>
											</c:if>
											<c:if test="${games.key.getStatut() == 1 }">
												<strong class="text-success">Terminé</strong>
											</c:if>
										</p>
										<p>Court n° ${games.key.getCourt()}</p>
										<p>
											Début: <strong class="text-primary">${games.key.getHeuredb()}</strong>
										</p>
										<p>
											Fin: <strong class="text-danger">${games.key.getHeurefin()}</strong>
										</p>
									</div>
								</div>
							</div>
						</div>
					</c:forEach>
				</div>
				<br>
				<h2 class="text-left">>> Matchs en double</h2>
				<br />
				<div class="row">
					<c:forEach items="${infoDoubleGame}" var="games">
						<div class="col-sm-6">
							<div class="jumbotron text-center hoverable p-4">
								<div class="row text-center">
									<div class="col-sm-5">
										<span> <img
											src="./assets/img/${games.value.get(0).getImage()}"
											class="img-fluid rounded-circle mr-1 head-img" alt="" /> <img
											src="./assets/img/${games.value.get(1).getImage()}"
											class="img-fluid rounded-circle head-img" alt="" />
										</span>
										<p class="text-center">${games.value.get(0).getNom()}ft
											${games.value.get(1).getNom()}</p>
									</div>
									<div class="col-sm-2">
										<p class="text-center mt-3 text-black-50 h5">${games.key.getScore() }</p>
									</div>
									<div class="col-sm-5">
										<span> <img
											src="./assets/img/${games.value.get(2).getImage()}"
											class="img-fluid rounded-circle mr-1 head-img" alt="" /> <img
											src="./assets/img/${games.value.get(3).getImage()}"
											class="img-fluid rounded-circle head-img" alt="" />
										</span>
										<p class="text-center">${games.value.get(2).getNom()}ft
											${games.value.get(3).getNom()}</p>
									</div>
								</div>
								<div class="row">
									<div class="col-8">
										<img src="./assets/img/court2.jpg" class="img-fluid rounded" />
									</div>
									<div class="col-sm-4 pt-4 text-justify">
										<p>
											Statut:
											<c:if test="${games.key.getStatut() == 0 }">
												<strong class="text-info">En attante</strong>
											</c:if>
											<c:if test="${games.key.getStatut() == 1 }">
												<strong class="text-success">Terminé</strong>
											</c:if>
										</p>
										<p>Court n° ${games.key.getCourt()}</p>
										<p>
											Début: <strong class="text-primary">${games.key.getHeuredb()}</strong>
										</p>
										<p>
											Fin: <strong class="text-danger">${games.key.getHeurefin()}</strong>
										</p>
									</div>
								</div>
							</div>
						</div>
					</c:forEach>
				</div>
			</div>
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
