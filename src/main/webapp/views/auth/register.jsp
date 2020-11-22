<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <base href="${pageContext.request.contextPath}">
        <title>Register</title>

        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bulma@0.9.1/css/bulma.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.5.8/slick.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.5.8/slick-theme.min.css">
        <script src="https://code.jquery.com/jquery-3.4.1.min.js" integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo=" crossorigin="anonymous"></script>
        <script defer src="https://use.fontawesome.com/releases/v5.14.0/js/all.js"></script>
        <script defer src="https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.5.8/slick.min.js"></script>

        <style type="text/css">
            .is-hidden {
                display: none;
            }
        </style>
    </head>
    <body>
        <section class="hero has-background-white-ter is fullheight">

            <form class="container box">
                <h3 class="title is-3">Create Account</h3>

                <div class="field">
                    <label class=has-text-weight-light">First Name</label>
                    <div class="control">
                        <input id="firstName" class="input" type="text" placeholder="Text input">
                    </div>
                </div>
                <div id="firstNameNotification" class="notification is-danger is-light is-hidden"></div>

                <div class="field">
                    <label class=has-text-weight-light">Second Name</label>
                    <div class="control">
                        <input id="secondName" class="input" type="text" placeholder="Text input">
                    </div>
                </div>
                <div id="secondNameNotification" class="notification is-danger is-light is-hidden"></div>

                <div class="field">
                    <label class="has-text-weight-light">Username</label>
                    <div class="control has-icons-left has-icons-right">
                        <input id="username" class="input" type="text" placeholder="Text input">
                        <span class="icon is-small is-left">
                                            <i class="fas fa-user"></i>
                                        </span>
                        <span class="icon is-small is-right">
                                            <i class="fas fa-check"></i>
                                        </span>
                    </div>
                </div>
                <div id="usernameNotification" class="notification is-danger is-light is-hidden"></div>

                <div class="field">
                    <label class=has-text-weight-light">Home Phone Number</label>
                    <div class="control">
                        <input id="homePhone" class="input" type="text" placeholder="Text input">
                    </div>
                </div>
                <div id="homePhoneNotification" class="notification is-danger is-light is-hidden"></div>

                <div class="field">
                    <label class=has-text-weight-light">Mobile Phone Number</label>
                    <div class="control">
                        <input id="mobilePhone" class="input" type="text" placeholder="Text input">
                    </div>
                </div>

                <div class="field">
                    <label class=has-text-weight-light">Identification Type:</label>
                    <br>
                    <div class="select">
                        <select id="idt">
                            <option value="0"> </option>
                            <option id="opt1" value="Passport">Passport</option>
                            <option id="opt2" value="National ID">National ID</option>
                            <option id="opt3" value="Drier's license">Driver's license</option>
                        </select>
                    </div>
                </div>
                <div id="idTypeNotification" class="notification is-danger is-light is-hidden"></div>
                <div class="field">
                    <label class=has-text-weight-light">Identification Number</label>
                    <div class="control">
                        <input id="idNumber" class="input" type="text" placeholder="Text Input">
                    </div>
                </div>
                <div id="idNumberNotification" class="notification is-danger is-light is-hidden"></div>

                <div class="field">
                    <label class=has-text-weight-light"> Address </label>
                    <div class="control">
                        <input id="address" class="input" type="text" placeholder="Text Input">
                    </div>
                </div>
                <div id="addressNotification" class="notification is-danger is-light is-hidden"></div>

                <div class="field">
                    <label>Number of adults:</label>
                    <input type="number" id="adults" name="quantity" min="1" max="40">
                </div>

                <div id="adultsNotification" class="notification is-danger is-light is-hidden"></div>

                <div class="field">
                    <label>Number of children:</label>
                    <input type="number" id="children" name="quantity" min="1" max="40">
                </div>

                <div id="childrenNotification" class="notification is-danger is-light is-hidden"></div>

                <div class="field">
                    <label class="has-text-weight-light">Create Password</label>
                    <div class="control has-icons-left has-icons-right">
                        <input id="password" type="password" placeholder="********" class="input" required>
                        <small class="icon is-small is-left">
                            <i class="fa fa-lock"></i>
                        </small>
                    </div>
                </div>
                <div id="passwordNotification" class="notification is-danger is-light is-hidden"></div>

                <div class="field">
                    <label class="has-text-weight-light">Confirm Password</label>
                    <div class="control has-icons-left has-icons-right">
                        <input id="repeatedPassword" type="password" placeholder="********" class="input" required>
                        <small class="icon is-small is-left">
                            <i class="fa fa-lock"></i>
                        </small>
                    </div>
                </div>
                <div id="repeatedPasswordNotification" class="notification is-danger is-light is-hidden"></div>

                <div class="field">
                    <div class="control">
                        <a id="registerButton" class="button is-primary">Register</a>
                    </div>
                </div>

                <div id="registerNotification" class="notification is-light is-hidden"></div>
            </form>
        </section>
    </body>

    <script>
        $(document).ready(function () {
            $("#registerButton").on('click', function () {
                registerUser();
            });

            $("#homePhone").keyup(function () {
                validPhoneNumber("home")
            });

            $("#mobilePhone").keyup(function () {
                validPhoneNumber("mobile")
            });

            $("#idNumber").keyup(function () {
                validNumber("#idNumber")
            });

            $("#password").keyup(function () {
                validPassword()
            });

            $("#repeatedPassword").keyup(function () {
                const password = $("#password").val();
                if(validPassword()) {
                    if($(this).val().length === 0 || $(this).val() === password) {
                        $("#repeatedPasswordNotification").html("");
                        $("#repeatedPasswordNotification").addClass("is-hidden");
                    } else {
                        $("#repeatedPasswordNotification").html("Please confirm password");
                        $("#repeatedPasswordNotification").removeClass("is-hidden");
                    }
                }
            });
        });

        function validPassword() {
            const password = $("#password").val();
            if(password.length === 0 || password.match(/^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z]{8,}$/)) {
                $("#passwordNotification").html("");
                $("#passwordNotification").addClass("is-hidden");
                return true;
            } else {
                $("#passwordNotification").html("Password must contain at least 8 characters, 1 number, 1 uppercase and 1 lowercase");
                $("#passwordNotification").removeClass("is-hidden");
                return false;
            }
        }

        function validPhoneNumber(pref) {
            phone = $("#" + pref + "Phone").val()
            if (phone.match(/^[0-9]*$/)){
                $("#" + pref +"PhoneNotification").html("");
                $("#" + pref +"PhoneNotification").addClass("is-hidden");
            } else {
                $("#" + pref +"PhoneNotification").html("Phone number must contain numbers only!");
                $("#" + pref +"PhoneNotification").removeClass("is-hidden");
            }
        }

        function validNumber(pref){
            num = $(pref).val()
            if (num.match(/^[0-9]*$/)){
                $(pref + "Notification").html("");
                $(pref + "Notification").addClass("is-hidden");
            } else {
                $(pref + "Notification").html("Must contain numbers only!");
                $(pref + "Notification").removeClass("is-hidden");
            }
        }

        function checkFilled(field) {
            const fieldVal = $("#" + field).val();
            if(fieldVal === null || fieldVal.length === 0) {
                $("#" + field + "Notification").html("Please fill " + field);
                $("#" + field + "Notification").removeClass("is-hidden");
                return false;
            } else {
                $("#" + field + "Notification").html("");
                $("#" + field + "Notification").addClass("is-hidden");
                return true;
            }
        }

        function checkType(idx){
            if(idx === "0") {
                $("#idTypeNotification").html("Please choose identification type");
                $("#idTypeNotification").removeClass("is-hidden");
                return false;
            } else {
                $("#idTypeNotification").html("");
                $("#idTypeNotification").addClass("is-hidden");
                return true;
            }
        }

        function registerUser() {
            const firstName = $("#firstName").val();
            const secondName = $("#secondName").val();
            const username = $("#username").val();
            const password = $("#password").val();
            const repeatedPassword = $("#repeatedPassword").val();
            const homePhone = $("#homePhone").val();
            const mobilePhone = $("#mobilePhone").val();
            const idNumber = $("#idNumber").val();
            const address = $("#address").val();

            var e = document.getElementById("idt");
            const idType = e.value;
            console.log(idType)
            if(idType == "Passport") {
                document.getElementById('opt1').id = 'idType_opt';
            } else if(idType=="National ID"){
                document.getElementById('opt2').id = 'idType_opt';
            } else if(idType=="Driver's license"){
                document.getElementById('opt3').id = 'idType_opt';
            }
            
            var e = document.getElementById("adults");
            const adults = e.value;

            var e = document.getElementById("children");
            const children = e.value;

            if(checkFilled("firstName")
                && checkFilled("secondName")
                && checkFilled("username")
                && checkFilled("password")
                && checkFilled("repeatedPassword")
                && checkFilled("homePhone")
                && checkFilled("mobilePhone")
                && checkFilled("idNumber")
                && checkType(idType)
                && checkFilled("address")
                && checkFilled("adults")
                && checkFilled("children")) {

                $.post("auth?auth=register", {
                    username: username,
                    password: password,
                    firstName: firstName,
                    secondName: secondName,
                    repeatedPassword: repeatedPassword,
                    homePhone: homePhone,
                    mobilePhone: mobilePhone,
                    idNumber: idNumber,
                    idType: idType,
                    adultsNum: adults,
                    childrenNum: children,
                    address: address
                }, function(res) {
                    $("#registerNotification").html(res.message);
                    $("#registerNotification").removeClass("is-hidden");
                    if (res.status !== "success") {
                        $("#registerNotification").removeClass("is-success");
                        $("#registerNotification").addClass("is-danger");
                    } else {
                        $("#registerNotification").addClass("is-success");
                        $("#registerNotification").removeClass("is-danger");
                        setTimeout(function () {
                            window.location.href = "";
                        }, 1500);
                    }
                });
            }
        }
    </script>
</html>
