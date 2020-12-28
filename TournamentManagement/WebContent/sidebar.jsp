<div>
	<a id="show-sidebar" class="btn btn-sm btn-dark" href="#"> <i
		class="fas fa-bars"></i>
	</a>
	<div id="sidebar" class="sidebar-wrapper">
		<div class="sidebar-content">
			<div class="sidebar-brand">
				<a href="#">Roland Garos APP</a>
			</div>
			<br />
			<div class="sidebar-header">
				<div class="user-pic">
					<img class="img-responsive img-rounded"
						src="./assets/img/profile.jpg" alt="User picture" />
				</div>
				<div class="user-info">
					<span class="user-name">Bertrand </span> <span class="user-role">Administrator</span>
					<span class="user-status"> <i class="fa fa-circle"></i> <span>Online</span>
					</span>
				</div>
			</div>
			<!-- sidebar-header  -->
			<div class="sidebar-search">
				<div>
					<div class="input-group">
						<input type="text" class="form-control search-menu"
							placeholder="Search..." />
						<div class="input-group-append">
							<span class="input-group-text"> <i class="fa fa-search"
								aria-hidden="true"></i>
							</span>
						</div>
					</div>
				</div>
			</div>
			<!-- sidebar-search  -->
			<div class="sidebar-menu">
				<ul>
					<li class="header-menu"><span>General settings</span></li>
					<li class="sidebar"><a
						href="${pageContext.request.contextPath}/home"> <i
							class="fa fa-home"></i> <span>Home</span>
					</a></li>
					<li class="sidebar-dropdown"><a href="#"> <i
							class="fas fa-table-tennis"></i> <span>Tennis Player </span>
					</a>
						<div class="sidebar-submenu">
							<ul>
								<li><a href="${pageContext.request.contextPath}/listPlayer">
										List of players </a></li>
								<li><a
									href="${pageContext.request.contextPath}/updatePlayer">Update
										a player</a></li>
							</ul>
						</div></li>
					<li class="sidebar-dropdown"><a href="#"> <i
							class="fas fa-child"></i> <span>Referee</span>
					</a>
						<div class="sidebar-submenu">
							<ul>
								<li><a
									href="${pageContext.request.contextPath}/listReferee">list
										of referees</a></li>
								<li><a
									href="${pageContext.request.contextPath}/updateReferee">Update
										a referee</a></li>
							</ul>
						</div></li>
					<li class="sidebar-dropdown"><a href="#"> <i
							class="fas fa-trophy"></i> <span>Game Management</span>
					</a>
						<div class="sidebar-submenu">
							<ul>
								<li><a href="${pageContext.request.contextPath}/createGameHome">Create a game</a></li>
								<li><a href="updGame.html">Update a game</a></li>
							</ul>
						</div>
					<li class="sidebar"><a href="#"> <i
							class="fas fa-info-circle"></i> <span>About the app</span>
					</a></li>
				</ul>
			</div>
			<!-- sidebar-menu  -->
		</div>
		<!-- sidebar-content  -->
		<div class="sidebar-footer">
			<a href="#"> <i class="fa fa-bell"></i>
			</a> <a href="#"> <i class="fa fa-cog"></i>
			</a> <a href="#"> <i class="fa fa-power-off"></i>
			</a>
		</div>
	</div>
	<div class="page-content">
		<!--Navbar-->
		<nav class="navbar fixed-top navbar-dark peach-gradient">
			<div class="container-fluid">
				<div class="navbar-collapse justify-content-center text-center">
					<ul class="navbar-nav mb-2 mb-lg-0">
						<li class="nav-item"><a class="nav-link active"
							aria-current="page" href="#">Roland Garos Tournament App
								Management </a></li>
					</ul>
				</div>
			</div>
		</nav>
		<!--/.Navbar-->
	</div>
	<!-- sidebar-wrapper  -->
</div>