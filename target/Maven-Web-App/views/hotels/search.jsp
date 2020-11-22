<%--
  Created by IntelliJ IDEA.
  User: kuanyshanarbay
  Date: 11/4/20
  Time: 9:43 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../layout/header.jsp"%>
<div class="container" style="margin-top: 80px">
    <div class="columns">
        <div class="column is-one-quarter">
            <h4 class="title is-5 mb-3">
                Booking Details
            </h4>

            <label class="heading is-7">Search hotel</label>
            <div class="field">
                <input id="name" class="input" type="text" placeholder="Hotel name">
            </div>

            <label class="heading is-7">Enter city</label>
            <div class="field">
                <input id="city" class="input" type="text" placeholder="City">
            </div>

            <label class="heading is-7">Season</label>
            <div class="select is-fullwidth">
                <select id="season">
                    <option>Summer</option>
                    <option>Winter</option>
                    <option>Winter</option>
                </select>
            </div>

            <hr />

            <h4 class="title is-5 mb-3">
                Included Services
            </h4>

            <ul class="content">
                <li>
                    <label class="checkbox">
                        <input id="Sauna"  class="featureCheckbox" type="checkbox">
                        Sauna
                    </label>
                </li>
                <li>
                    <label class="checkbox">
                        <input id="Breakfast" class="featureCheckbox" type="checkbox">
                        Breakfast
                    </label>
                </li>
            </ul>

            <button id="searchButton" class="button is-primary is-fullwidth">
                <strong>
                    Search hotels
                </strong>
            </button>
        </div>
        <div class="column">
            <div class="level">
                <div class="level-left">
                    <h4 id="searchResult" class="title is-6">
                        We have found 0 hotels for you
                    </h4>
                </div>
                <div class="level-right">
                    <div class="select is-small">
                        <select id="sortBy">
                            <option value="relevance">Sort by: Most relevant</option>
                            <option value="lowest">Sort by: Lowest price</option>
                            <option value="highest">Sort by: Highest price</option>
                        </select>
                    </div>
                </div>
            </div>


            <div id="hotels">

            </div>

        </div>
    </div>
</div>

</body>

<script>

    let features = [];

    $(document).ready(function () {

        $("#searchButton").on('click', function () {
            searchHotels();
        });

        $(".featureCheckbox").on('click', function (e) {
            toggleFeature(e.target.id);
        });
    });

    function toggleFeature(newFeature) {
        if(features.includes(newFeature)) {
            features = features.filter(feature => feature !== newFeature);
        } else {
            features.push(newFeature);
        }
    }

    function showHotels(hotels) {
        $("#searchResult").html(`We have found ` + hotels.length + ` hotels for you`)
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
                            <img class="image" src="` + item.image + `" />
                        </div>
                        <div class="column">
                            <div class="card-content">
                                <h4 class="title is-5">
                                    ` + item.name + `
                                </h4>
                                <p class="subtitle is-6">
                                    ` + item.name + `
                                </p>
                                ` +
                                item.features.map(feature => `
                                    <small class="tag">
                                        ` + feature + `
                                    </small>
                                `).join(" ")
                                + `
                            </div>
                        </div>
                        <div class="column is-one-fifth is-left-bordered">
                            <div class="card-content is-centered">
                                <p class="heading">Price</p>
                                <p class="title mb-3">€` + item.price + `</p>
                                <button class="button is-primary is-small">
                                    Book now
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            `
    }

    const hotel = {
        id: 1,
        name: "Family Room",
        description: "Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat. Ut wisi enim ad minim veniam, quis nostrud exerci tation ullamcorper suscipit lobortis nisl ut aliquip ex ea commodo consequat.",
        image: "https://preview.eagle-themes.com/zante/wp-content/uploads/2018/05/family-1170x680.jpg",
        features: [
            "Breakfast",
            "Coffee Maker",
            "Widescreen TV",
            "Hair Dryer",
            "Air Conditioner",
            "Free-to-use Smartphone"
        ],
        price: 5200
    }

    function searchHotels() {
        const name = $("#name").val();
        const season = $("#season").val();
        const city = $("#city").val();

        showHotels([hotel, hotel, hotel, hotel, hotel]);
        $.get("hotels/search", {
            name: name,
            city: city
        }, function (res) {
            console.log(res);
            showHotels(res.res);
        });
    }
</script>
</html>