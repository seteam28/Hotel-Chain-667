<%--
  Created by IntelliJ IDEA.
  User: kuanyshanarbay
  Date: 11/4/20
  Time: 9:49 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../layout/header.jsp"%>
<div class='columns'>
    <div class="column is-one-third">
        <div class='container' style="margin-top: 0px">
            <div class='modal' id='edit-preferences-modal'>
                <div class='modal-background'></div>
                <div class='modal-card'>
                    <header class='modal-card-head'>
                        <p class='modal-card-title'>Edit profile</p>
                        <button class='delete'></button>
                    </header>
                    <section class='modal-card-body'>
                        <label class='label'>First Name</label>
                        <p class='control'>
                            <input id="fname" class='input' placeholder='Text input' type='text' value="Aidyn">
                        </p>
                        <label class='label'>Second Name</label>
                        <p class='control'>
                            <input id="sname" class='input' placeholder='Text input' type='text' value="Ubingazhibov">
                        </p>
                        <label class='label'>Username</label>
                        <p class='control has-icon has-icon-right'>
                            <input id="uname" class='input' placeholder='Text input' type='text' value='aidynUb'>
                        </p>
                        <label class='label'>Email</label>
                        <p class='control has-icon has-icon-right'>
                            <input id="edit_email" class='input' placeholder='Email input' type='text' value='aidyn.ubingazhibov@nu.edu.kz'>
                            <i class='fa fa-warning'></i>
                        </p>

                        <label class='label'>Mobile Phone Number</label>
                        <p class='control has-icon has-icon-right'>
                            <input id="edit_mobilephone" class='input' placeholder='Text input' type='text' value='87789747651'>
                        </p>

                        <label class='label'>Home Phone Number</label>
                        <p class='control has-icon has-icon-right'>
                            <input id="edit_homephone" class='input' placeholder='Text input' type='text' value='340345'>
                        </p>
                    </section>
                    <footer class='modal-card-foot'>
                        <a id="save" class='button is-primary modal-save'>Save changes</a>
                        <a class='button modal-cancel'>Cancel</a>
                    </footer>
                </div>
            </div>
            <div class='section profile-heading'>
                <div class='columns is-mobile is-multiline'>
                    <label class='column is-100-tablet is-100-mobile name'>
                        <p>
                            <span id="name" class='title is-bold'>Aidyn Ubingazhibov</span>
                            <br>
                            <label id="email" class="has-text-weight-light">aidyn.ubingazhibov@nu.edu.kz</label>
                            <br>
                            <span class='has-text-weight-light'>Joined in 2020</span>
                            <br>
                            <a class='button is-primary is-outlined' href='#' id='edit-preferences' style='margin: 5px 0'>
                                Edit Profile
                            </a>
                            <br>
                        </p>
                        <br>
                        <label class="label">About me</label>
                        <br>
                        <span class="icon">
            <i class="fas fa-home"></i>
          </span>
                        <label class="has-text-weight-light">Lives in Nur-Sultan, Kazakhstan</label>
                        <br>
                        <label class="has-text-weight-light">Home Phone number: </label>
                        <label id="homephone" class="has-text-weight-light">340345</label>
                        <br>
                        <label class="has-text-weight-light">Mobile Phone number: </label>
                        <label id="mobilephone" class="has-text-weight-light"> 87789747651</label>
                        <br>
                        <label class="has-text-weight-light">Identification type: </label>
                        <label id="idtype" class="has-text-weight-light">Passport</label>
                        <br>
                        <label class="has-text-weight-light">Identification number: </label>
                        <label id="idnum" class="has-text-weight-light">2403290920</label>
                        <br>
                        <label class="has-text-weight-light">Address: </label>
                        <label id="address" class="has-text-weight-light">Street 38, House 18, Apartment 64</label>
                        <br>
                    </label>
                </div>
            </div>
        </div>
    </div>

    <div class="column is-two-thirds">
        <div class='container' style="margin-top: 50px">
            <span class='title is-bold'>Your reservations</span>
            <br>
            <br>
            <button id="upcoming" class="button is-link">Upcoming</button>
            <button id="past" class="button is-link">Past</button>
            <button id="cancelled" class="button is-link">Cancelled</button>
            <br>
            <div id="hotels">


            </div>
        </div>
    </div>
</div>

<script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.0/jquery.min.js'></script>
</body>

<script>
    $(document).ready(function () {
        let upcoming = [hotel]
        let cancelled = []

        $(() => {
            $('#edit-preferences').click(function(){
                $('#edit-preferences-modal').addClass('is-active');
            });
            $('.modal-card-head button.delete, .modal-save, .modal-cancel').click(function(){
                $('#edit-preferences-modal').removeClass('is-active');
            });
        });

        $("#upcoming").on('click', function(){
            var button = document.getElementById("upcoming")
            button.style.backgroundColor = "#3FFF33"
            var button = document.getElementById("past")
            button.style.backgroundColor = "#3368FF"
            var button = document.getElementById("cancelled")
            button.style.backgroundColor = "#3368FF"

            showHotels(upcoming)
            $(() => {
                $('#update').click(function(){
                    $('#update-modal').addClass('is-active');
                });
                $('.modal-card-head button.delete, .modal-save, .modal-cancel').click(function(){
                    $('#update-modal').removeClass('is-active');
                });
            });

            $("#save2").on('click', function () {
                const from = $("#from").val()
                const to = $("#to").val()
                if(from.length != 10 || to.length != 10){
                    return
                }
                document.getElementById('date').innerHTML = "Reserved Date:" + from + " to " + to;
            });

            $("#updatecancel").append("<a class='button is-primary is-outlined' href='#1' id='update' style='margin: 5px 0'> Update reservation </a>")
            $("#updatecancel").append("<a class='button is-primary is-outlined' href='#' id='cancel' style='margin: 5px 0'> Cancel reservation </a>")

            $("#cancel").on('click', function () {
                showHotels([])
                cancelled.push(hotel)
                upcoming.pop()
            });
        });

        $("#past").on('click', function(){
            var button = document.getElementById("upcoming")
            button.style.backgroundColor = "#3368FF"
            var button = document.getElementById("past")
            button.style.backgroundColor = "#3FFF33"
            var button = document.getElementById("cancelled")
            button.style.backgroundColor = "#3368FF"

            showHotels([hotel2])
        });

        $("#cancelled").on('click', function(){
            var button = document.getElementById("cancelled")
            button.style.backgroundColor = "#3FFF33"
            var button = document.getElementById("past")
            button.style.backgroundColor = "#3368FF"
            var button = document.getElementById("upcoming")
            button.style.backgroundColor = "#3368FF"
            showHotels(cancelled)
        });

        $("#save").on('click', function () {
            const fname = $("#fname").val()
            const sname = $("#sname").val()
            document.getElementById('name').innerHTML = fname + " " + sname;

            const homephone = $("#edit_homephone").val()
            const mobilephone = $("#edit_mobilephone").val()
            //const idtype = $("#idtype").val()
            //const idnum = $("#idnum").val()
            const email = $("#edit_email").val()
            document.getElementById('homephone').innerHTML = homephone;
            document.getElementById('mobilephone').innerHTML = mobilephone;
            document.getElementById('email').innerHTML = email;
        });
    });

    function showHotels(hotels) {
        $("#hotels").html("");
        hotels.forEach(function (e, i) {
            $("#hotels").append(hotelItem(i, e));
        });
    }

    function hotelItem(index, item) {
        return `
		            <div id="{item.id}" class="card is-bordered mb-5">
                        <div class="columns is-gapless">
                            <div class="column is-one-quarter">
                                <img class="image" src="${item.image}" />
                            </div>
                            <div class="column">
                                <div class="card-content">
                                    <h4 class="title is-5">
                                        ${item.name}
                                    </h4>
                                    <p class="subtitle is-6">
                                        ${item.description}
                                    </p>
                                    <p id="date" class="subtitle is-6">
                                        Reserved date: ${item.date}
                                    </p>
                                    <div class='columns'>
                                    <div class='container' style="margin-top: 0px">
                                    <div class='modal' id='update-modal'>
                                        <div class='modal-background'></div>
                                            <div class='modal-card'>
                                            <header class='modal-card-head'>
                                                    <p class='modal-card-title'>update reservation</p>
                                                    <button class='delete'></button>
                                            </header>
                                            <section class='modal-card-body'>
                                                <p class='control'>
                                                    From: <input id="from" type="date">
                                                </p>
                                                <p class='control'>
                                                    Until: <input id="to" type="date">
                                                </p>
                                            </section>
                                            <a id="save2" class='button is-primary modal-save'>Save changes</a>
                                            </div>
                                            </div>
                                            </div></div>

                                    <div id="updatecancel">

                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
		        `
    }
    const hotel = {
        id: 1,
        name: "Family Room",
        description: "Deluxe room, hotel Four Seasons, Little India",
        image: "https://preview.eagle-themes.com/zante/wp-content/uploads/2018/05/family-1170x680.jpg",
        date: "2020-11-05 to 2020-11-13",
        features: [
            "Breakfast",
            "Coffee Maker",
            "Widescreen TV",
            "Hair Dryer",
            "Air Conditioner",
            "Free-to-use Smartphone"
        ],
        avgPrice: 5200
    }

    const hotel2 = {
        id: 1,
        name: "Family Room",
        description: "Deluxe room, hotel The Breakers, Kabanbay Batyr",
        image: "hotel.jpg",
        date: "2019-11-05 to 2019-11-13",
        features: [
            "Breakfast",
            "Coffee Maker",
            "Widescreen TV",
            "Hair Dryer",
            "Air Conditioner",
            "Free-to-use Smartphone"
        ],
        avgPrice: 5200
    }
</script>
</html>
