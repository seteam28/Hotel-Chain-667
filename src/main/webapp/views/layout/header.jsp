<%--
  Created by IntelliJ IDEA.
  User: kuanyshanarbay
  Date: 11/4/20
  Time: 10:52 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en" >

    <head>
        <meta charset="UTF-8">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bulma@0.9.1/css/bulma.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.5.8/slick.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.5.8/slick-theme.min.css">
        <script src="https://code.jquery.com/jquery-3.4.1.min.js" integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo=" crossorigin="anonymous"></script>
        <script defer src="https://use.fontawesome.com/releases/v5.14.0/js/all.js"></script>
        <script defer src="https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.5.8/slick.min.js"></script>
        <link rel="stylesheet" href="profile.css">
        <script defer src="https://use.fontawesome.com/releases/v5.3.1/js/all.js"></script>
    </head>

    <script>
        $(document).ready(function () {
            $(".loginButton").on('click', function () {
                loginUser();
            });

            $(".toggleLogin").on('click', function () {
                toggleLoginModal();
            });
        });


        function loginUser() {
            const username = $("#username").val();
            const password = $("#password").val();
            $.post("auth?auth=login", { username: username, password: password }, function(res) {
                $("#loginNotification").html(res.message);
                $("#loginNotification").removeClass("is-hidden");
                if (res.status !== "success") {
                    $("#loginNotification").removeClass("is-success");
                    $("#loginNotification").addClass("is-danger");
                } else {
                    $("#loginNotification").addClass("is-success");
                    $("#loginNotification").removeClass("is-danger");
                    setTimeout(function () {
                        toggleLoginModal();
                    }, 1500);
                }
            });
        }

        function toggleLoginModal() {
            $("#loginModal").toggleClass("is-active");
        }
    </script>

    <body>

        <!-- Navigation bar -->
        <nav class="navbar" role="navigation" aria-label="main navigation">
            <div class="navbar-brand">
                <a class="navbar-item" href="https://bulma.io">
                    <img src="https://bulma.io/images/bulma-logo.png" width="112" height="28">
                </a>

                <a role="button" class="navbar-burger burger" aria-label="menu" aria-expanded="false" data-target="navbarBasicExample">
                    <span aria-hidden="true"></span>
                    <span aria-hidden="true"></span>
                    <span aria-hidden="true"></span>
                </a>
            </div>

            <div id="navbarBasicExample" class="navbar-menu">
                <div class="navbar-start">
                    <a class="navbar-item">
                        Home
                    </a>

                    <a href="Hotel_chain_war_exploded//hotels/search" class="navbar-item">
                        Hotels
                    </a>

                    <div class="navbar-item has-dropdown is-hoverable">
                        <a class="navbar-link">
                            More
                        </a>

                        <div class="navbar-dropdown">
                            <a class="navbar-item">
                                About
                            </a>
                            <a class="navbar-item">
                                Contact
                            </a>
                            <hr class="navbar-divider">
                            <a class="navbar-item">
                                Report an issue
                            </a>
                        </div>
                    </div>
                </div>

                <div class="navbar-end">
                    <div class="navbar-item">
                        <div class="buttons">
                            <a href="auth" class="button is-primary">
                                <strong>Sign up</strong>
                            </a>
                            <a class="button is-light toggleLogin">
                                Log in
                            </a>
                        </div>
                    </div>
                </div>
            </div>
        </nav>

        <!-- Login modal -->
        <div id="loginModal" class="modal">
            <div class="modal-background"></div>
            <div class="modal-card">
                <header class="modal-card-head">
                    <p class="modal-card-title">Login</p>
                    <button class="delete toggleLogin" aria-label="close"></button>
                </header>
                <section class="modal-card-body">
                    <div class="field">
                        <label class="label">Username</label>
                        <div class="control has-icons-left has-icons-right">
                            <input id="username" class="input" type="text" placeholder="Username">
                            <span class="icon is-small is-left">
                                        <i class="fas fa-user"></i>
                                    </span>
                        </div>
                    </div>
                    <div class="field">
                        <label class="label">Password</label>
                        <div class="control has-icons-left has-icons-right">
                            <input id="password" class="input" type="password" placeholder="Password">
                            <span class="icon is-small is-left">
                                        <i class="fas fa-lock"></i>
                                    </span>
                        </div>
                    </div>

                    <div id="loginNotification" class="notification is-light is-hidden"></div>
                </section>
                <footer class="modal-card-foot">
                    <button class="button loginButton">Login</button>
                    <button class="button toggleLogin">Cancel</button>
                </footer>
            </div>
        </div>



