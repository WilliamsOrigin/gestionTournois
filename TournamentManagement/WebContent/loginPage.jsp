<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <title>TournamentManagement</title>
    <link
      rel="stylesheet"
      href="https://use.fontawesome.com/releases/v5.8.2/css/all.css"
    />
    <link rel="stylesheet" href="vendor/css/bootstrap.min.css" />
    <link rel="stylesheet" href="vendor/css/mdb.min.css" />
    <link
      rel="stylesheet"
      href="vendor/css/fontawesome.min.css"
    />
    <link rel="stylesheet" href="vendor/css/animate.css" />
    <link rel="stylesheet" href="assets/css/style.css" />
  </head>
  <body>
    <div class="view intro-video">
      <video class="video-fluid" autoplay loop playsinline>
        <source src="./assets/videos/tennis.mp4" type="video/mp4" />
      </video>
      <div
        class="mask rgba-black-slight h-100 d-flex justify-content-center align-items-center"
      >
        <div class="container">
          <div class="row">
            <div class="col-xl-5 col-lg-6 col-md-10 col-sm-12 mx-auto mt-lg-5">
              <div class="card wow fadeIn">
                <div class="card-body">
                  <!--Header-->
                  <div class="form-header">
                    <h3>
                      <i class="fas fa-sign-in-alt mt-2 mb-2"></i> Registration
                    </h3>
                  </div>

                  <!--Body-->
                  <form method="POST" action="login" class="text-center" style="color: white">
                    <!-- Email -->
                    <input
                      type="text"
                      name="pseudo"
                      class="form-control mb-4"
                      placeholder="Login"
                    />

                    <!-- Password -->
                    <input
                      type="password"
                      name="motdepasse"
                      class="form-control mb-4"
                      placeholder="Password"
                    />

                    <div class="d-flex justify-content-around">
                      <div>
                        <!-- Remember me -->
                        <div class="custom-control custom-checkbox">
                          <input
                            type="checkbox"
                            class="custom-control-input"
                            id="defaultLoginFormRemember"
                          />
                          <label
                            class="custom-control-label"
                            for="defaultLoginFormRemember"
                            >Remember me</label
                          >
                        </div>
                      </div>
                      <div>
                        <!-- Forgot password -->
                        <a href="">Forgot password?</a>
                      </div>
                    </div>

                    <!-- Sign in button -->
                    <button
                      class="btn blue-gradient bt btn-rounded btn-block my-4 waves-effect z-depth-0"
                      type="submit"
                    >
                      Log in
                    </button>

                    <!-- Register -->
                    <p>
                      Not a member?
                      <a href="">Register</a>
                    </p>

                    <!-- Social login -->
                    <p>or sign in with:</p>
                    <a type="button" class="btn-floating btn-fb btn-sm">
                      <i class="fab fa-facebook-f"></i>
                    </a>
                    <a type="button" class="btn-floating btn-tw btn-sm">
                      <i class="fab fa-twitter"></i>
                    </a>
                    <a type="button" class="btn-floating btn-li btn-sm">
                      <i class="fab fa-linkedin-in"></i>
                    </a>
                    <a type="button" class="btn-floating btn-git btn-sm">
                      <i class="fab fa-github"></i>
                    </a>
                  </form>
                  <!-- Form -->
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    <script src="vendor/js/jquery.min.js"></script>
    <script src="vendor/js/popper.min.js"></script>
    <script src="vendor/js/bootstrap.min.js"></script>
    <script src="vendor/js/mdb.min.js"></script>
    <script src="vendor/js/wow.min.js"></script>
    <script src="vendor/js/fontawesome.min.js"></script>
    <script>
      new WOW().init();
    </script>
  </body>
</html>
