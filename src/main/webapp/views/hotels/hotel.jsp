<%@ page import="com.google.gson.JsonObject" %><%--
  Created by IntelliJ IDEA.
  User: kuanyshanarbay
  Date: 11/4/20
  Time: 9:43 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../layout/header.jsp"%>
<%JsonObject hotel = (JsonObject)request.getAttribute("hotel"); %>

<div class="container" style="margin-top: 80px">
    <div class="columns">

        <div class="column">

            <div>
                <div class="mb-4">
                    <img class="image" src="https://preview.eagle-themes.com/zante/wp-content/uploads/2018/05/single-1170x680.jpg" />
                </div>
                <h4 class="title is-3">

                </h4>
                <div class="subtitle is-6 has-text-grey">
                    <%= hotel.get("address") %> • ${hotel.phone}
                </div>
                <p class="subtitle is-6">
                    <%= hotel.get("description") %>
                </p>

                <hr />

                <h4 class="title is-4 mb-3">
                    Services
                </h4>

                <c:forEach var="feature" items="${hotel.features}" varStatus="loopCounter">
                    <button class="button is-info is-light is-small">
                            ${feature}
                    </button>
                </c:forEach>

                <hr />

                <h4 class="title is-4 mb-3">
                    Seasons
                </h4>

                <div class="columns is-multiline is-variable is-3">
                    <c:forEach var="season" items="${hotel.seasons}" varStatus="loopCounter">
                        <div class="column is-centered is-3">
                            <div class="box">
                                <h1 class="title is-4">
                                        ${season[0]}
                                </h1>
                                <h1 class="subtitle is-7">
                                        ${season[1]} - ${season[2]}
                                </h1>
                            </div>
                        </div>
                    </c:forEach>
                </div>

                <hr />

                <h4 class="title is-4 mb-3">
                    Rooms
                </h4>

                <div id="rooms" class="columns is-multiline is-variable is-1">

                </div>
            </div>
        </div>

        <div class="column is-one-quarter">
            <h4 class="title is-5 mb-3">
                Booking Details
            </h4>

            <label class="heading is-7">Check in/out</label>
            <div class="columns is-variable is-1 mb-0">
                <div class="column is-half">
                    <input type="date" id="checkIn" class="input" type="text" placeholder="Check in">
                </div>
                <div class="column is-half">
                    <input type="date" id="checkOut" class="input" type="text" placeholder="Check out">
                </div>
            </div>

            <label class="heading is-7">Guests</label>
            <div class="columns is-variable is-1 mb-0">
                <div class="column is-half">
                    <input type="number" id="adults" class="input" type="text" placeholder="Adults">
                </div>
                <div class="column is-half">
                    <input type="number" id="children" class="input" type="text" placeholder="Children">
                </div>
            </div>

            <label class="heading is-7">Room</label>
            <div class="select is-fullwidth mb-4">
                <select id="room">

                </select>
            </div>

            <button id="bookButton" class="button is-primary is-fullwidth">
                <strong>
                    Book now
                </strong>
            </button>

        </div>
    </div>
</div>

</body>

<script>

    $(document).ready(function () {

        getRooms();
        $("#bookButton").on('click', function () {

            bookRoom();
        });
    });


    const room = {
        number: 1,
        floor: 2,
        isClean: true,
        capacity: 2,
        size: "120m^2",
        features: [
            "clean",
            "comfortable"
        ],
        name: "Family room"
    };

    function getRooms() {
        showRooms([room, room, room, room])
        $.get("/hotels/rooms", { id: ${hotel.id} }, function (res) {
            showRooms(res);
        });
    }

    function roomItem(index, item) {
        return `
              <div id="` + item.id + `" class="column is-6">
                        <div class="card">
                            <div class="card-content">
                                <div class="content">
                                    <p class="mb-0">
                                        Room #` + item.number + :  + item.name + `
                                    </p>

                                    <p class="mb-2">
                                        <small>
                                            <span class="has-text-grey">Floor:</span> <b>` + item.floor + `</b>
                                            <br/>
                                            <span class="has-text-grey">Capacity:</span> <b>` + item.capacity + `</b>
                                            <br />
                                            <span class="has-text-grey">Size:</span> <b>` + item.size + `</b>
                                        </small>
                                    </p>
                                    ` +
        item.features.map(feature => `
                                        <small class="tag">
                                             + feature +
                                        </small>
                                    `)
        +`
                                </div>
                            </div>
                        </div>
                    </div>
          `
    }

    function showRooms(rooms) {
        $("#rooms").html("");
        rooms.forEach(function (e, i) {
            $("#rooms").append(roomItem(i, e));
            $("#room").append(`<option value="` + e.id + "> + e.name + `</option>`)
        });
    }

    function bookRoom() {
        const checkIn = $("#checkIn").val();
        const checkOut = $("#checkOut").val();
        const adults = $("#adults").val();
        const children = $("#children").val();
        const roomNumber = $("#rooms").val();

        $.post("booking", {
            hotelId: ${hotel.id},
            checkIn: checkIn,
            checkOut: checkOut,
            adults: adults,
            children: children,
            roomNumber: roomNumber
        }, function (res) {
            if (res.status !== "success") {
                console.log(res.message);
            } else {
                console.log(res.message);
            }
        });
    }
</script>
</html></html>