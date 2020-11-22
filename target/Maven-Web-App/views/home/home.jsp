<%@ page language="java" contentType="text/html;charset=UTF-8"
         pageEncoding="ISO-8859-1"%>
<%@ include file="../layout/header.jsp"%>

        <div class="header">
            <div id="headerCarousel">
                <img src="https://tripfinder-redq.firebaseapp.com/static/media/3.b9923e48.jpg" alt="...">
            </div>

            <div class="searchHotel">
                <h3 class="title">Best hotels. Lowest prices.</h3>
                <p class="subtitle is-6">compares prices from 200+ booking sites to help you find the lowest price on the right hotel for you.</p>
                <form>
                    <div class="columns is-variable is-1">
                        <div class="column is-6 field">
                            <p class="control has-icons-left">
                                <input type="text" class="input" placeholder="Search location">
                                <span class="icon is-small is-left">
                                    <i class="fas fa-map-marker-alt"></i>
                                </span>
                            </p>
                        </div>
                        <div class="column field">
                            <p class="control has-icons-left">
                                <input type="text" class="input" placeholder="Start date">
                                <span class="icon is-small is-left">
                                    <i class="fas fa-calendar-alt"></i>
                                </span>
                            </p>
                        </div>
                        <div class="column field">
                            <p class="control has-icons-left">
                                <input type="text" class="input" placeholder="End date">
                                <span class="icon is-small is-left">
                                    <i class="fas fa-calendar-alt"></i>
                                </span>
                            </p>
                        </div>
                        <div class="column is-1">
                            <button class="button is-primary">Search</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>


        <!-- Cities list -->
<%--        <section class="section">--%>
<%--            <div class="container">--%>
<%--                <h1 class="title">Explore destinations</h1>--%>
<%--            </div>--%>

<%--            <div class="wrapper">--%>
<%--                <div class="carousel">--%>
<%--                    <div class="cityItem">--%>
<%--                        <img src="http://s3.amazonaws.com/redqteam.com/tripfinder-images/rome.jpg" />--%>
<%--                        <div class="content">--%>
<%--                            <h3 class="title is-4">Rome</h3>--%>
<%--                        </div>--%>
<%--                    </div>--%>
<%--                    <div class="cityItem">--%>
<%--                        <img src="http://s3.amazonaws.com/redqteam.com/tripfinder-images/rome.jpg" />--%>
<%--                        <div class="content">--%>
<%--                            <h3 class="title is-4">Rome</h3>--%>
<%--                        </div>--%>
<%--                    </div>--%>
<%--                    <div class="cityItem">--%>
<%--                        <img src="http://s3.amazonaws.com/redqteam.com/tripfinder-images/rome.jpg" />--%>
<%--                        <div class="content">--%>
<%--                            <h3 class="title is-4">Rome</h3>--%>
<%--                        </div>--%>
<%--                    </div>--%>
<%--                    <div class="cityItem">--%>
<%--                        <img src="http://s3.amazonaws.com/redqteam.com/tripfinder-images/rome.jpg" />--%>
<%--                        <div class="content">--%>
<%--                            <h3 class="title is-4">Rome</h3>--%>
<%--                        </div>--%>
<%--                    </div>--%>
<%--                    <div class="cityItem">--%>
<%--                        <img src="http://s3.amazonaws.com/redqteam.com/tripfinder-images/rome.jpg" />--%>
<%--                        <div class="content">--%>
<%--                            <h3 class="title is-4">Rome</h3>--%>
<%--                        </div>--%>
<%--                    </div>--%>
<%--                    <div class="cityItem">--%>
<%--                        <img src="http://s3.amazonaws.com/redqteam.com/tripfinder-images/rome.jpg" />--%>
<%--                        <div class="content">--%>
<%--                            <h3 class="title is-4">Rome</h3>--%>
<%--                        </div>--%>
<%--                    </div>--%>
<%--                    <div class="cityItem">--%>
<%--                        <img src="http://s3.amazonaws.com/redqteam.com/tripfinder-images/rome.jpg" />--%>
<%--                        <div class="content">--%>
<%--                            <h3 class="title is-4">Rome</h3>--%>
<%--                        </div>--%>
<%--                    </div>--%>
<%--                    <div class="cityItem">--%>
<%--                        <img src="http://s3.amazonaws.com/redqteam.com/tripfinder-images/rome.jpg" />--%>
<%--                        <div class="content">--%>
<%--                            <h3 class="title is-4">Rome</h3>--%>
<%--                        </div>--%>
<%--                    </div>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--        </section>--%>

        <!-- Top hotels -->
        <section class="section">
            <div class="container">
                <h1 class="title">Travelers’ Choice: Top hotels</h1>


                <div class="columns is-multiline">
                    <div class="column is-3">
                        <div class="card">
                            <div class="card-image">
                                <figure class="image is-3by2">
                                    <img src="https://bulma.io/images/placeholders/1280x960.png" alt="Placeholder image">
                                </figure>
                            </div>
                            <div class="card-content">
                                <div class="content">
                                    <h5 class="title is-5">Incredible Granite Chicken</h5>
                                    <p class="subtitle is-6" style="margin-bottom: 6px;">$191.00/Night - Free Cancellation</p>
                                    <small>
                                        <i class="fas fa-star has-text-warning"></i>
                                        <i class="fas fa-star has-text-warning"></i>
                                        <i class="fas fa-star has-text-warning"></i>
                                        <i class="fas fa-star has-text-warning"></i>
                                        <i class="far fa-star has-text-warning"></i>
                                        <strong>Good (123)</strong>
                                    </small>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="column is-3">
                        <div class="card">
                            <div class="card-image">
                                <figure class="image is-3by2">
                                    <img src="https://bulma.io/images/placeholders/1280x960.png" alt="Placeholder image">
                                </figure>
                            </div>
                            <div class="card-content">
                                <div class="content">
                                    <h5 class="title is-5">Incredible Granite Chicken</h5>
                                    <p class="subtitle is-6" style="margin-bottom: 6px;">$191.00/Night - Free Cancellation</p>
                                    <small>
                                        <i class="fas fa-star has-text-warning"></i>
                                        <i class="fas fa-star has-text-warning"></i>
                                        <i class="fas fa-star has-text-warning"></i>
                                        <i class="fas fa-star has-text-warning"></i>
                                        <i class="far fa-star has-text-warning"></i>
                                        <strong>Good (123)</strong>
                                    </small>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="column is-3">
                        <div class="card">
                            <div class="card-image">
                                <figure class="image is-3by2">
                                    <img src="https://bulma.io/images/placeholders/1280x960.png" alt="Placeholder image">
                                </figure>
                            </div>
                            <div class="card-content">
                                <div class="content">
                                    <h5 class="title is-5">Incredible Granite Chicken</h5>
                                    <p class="subtitle is-6" style="margin-bottom: 6px;">$191.00/Night - Free Cancellation</p>
                                    <small>
                                        <i class="fas fa-star has-text-warning"></i>
                                        <i class="fas fa-star has-text-warning"></i>
                                        <i class="fas fa-star has-text-warning"></i>
                                        <i class="fas fa-star has-text-warning"></i>
                                        <i class="far fa-star has-text-warning"></i>
                                        <strong>Good (123)</strong>
                                    </small>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="column is-3">
                        <div class="card">
                            <div class="card-image">
                                <figure class="image is-3by2">
                                    <img src="https://bulma.io/images/placeholders/1280x960.png" alt="Placeholder image">
                                </figure>
                            </div>
                            <div class="card-content">
                                <div class="content">
                                    <h5 class="title is-5">Incredible Granite Chicken</h5>
                                    <p class="subtitle is-6" style="margin-bottom: 6px;">$191.00/Night - Free Cancellation</p>
                                    <small>
                                        <i class="fas fa-star has-text-warning"></i>
                                        <i class="fas fa-star has-text-warning"></i>
                                        <i class="fas fa-star has-text-warning"></i>
                                        <i class="fas fa-star has-text-warning"></i>
                                        <i class="far fa-star has-text-warning"></i>
                                        <strong>Good (123)</strong>
                                    </small>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="column is-3">
                        <div class="card">
                            <div class="card-image">
                                <figure class="image is-3by2">
                                    <img src="https://bulma.io/images/placeholders/1280x960.png" alt="Placeholder image">
                                </figure>
                            </div>
                            <div class="card-content">
                                <div class="content">
                                    <h5 class="title is-5">Incredible Granite Chicken</h5>
                                    <p class="subtitle is-6" style="margin-bottom: 6px;">$191.00/Night - Free Cancellation</p>
                                    <small>
                                        <i class="fas fa-star has-text-warning"></i>
                                        <i class="fas fa-star has-text-warning"></i>
                                        <i class="fas fa-star has-text-warning"></i>
                                        <i class="fas fa-star has-text-warning"></i>
                                        <i class="far fa-star has-text-warning"></i>
                                        <strong>Good (123)</strong>
                                    </small>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

            </div>
        </section>


        <!-- Best rated hotels -->
        <section class="section">
            <div class="container">
                <h1 class="title">Best Rated: Luxary hotels</h1>
            </div>
        </section>




    </body>
    <style>
        #headerCarousel {
            width: 100%;
            height: 100vh;
        }

        #headerCarousel img {
            height: 100%;
        }

        .header {
            position: relative;
        }

        .searchHotel {
            position: absolute;
            bottom: 96px;
            box-shadow: rgba(0, 0, 0, 0.08) 0px 1px 20px;
            background-color: #fff;
            z-index: 1;
            width: calc(100vw - 360px);
            margin-left: 180px;
            margin-right: 180px;
            padding: 28px 30px;
            border-radius: 8px;
        }


        .modal-background {
            background-color: rgba(10, 10, 10, .86);
        }

        .wrapper{
            width:100%;
            padding-top: 20px;
            text-align:center;
        }

        .carousel{
            width:90%;
            margin:0px auto;
        }

        .cityItem {
            position: relative;
            border-radius: 6px;
        }

        .cityItem .content {
            position: absolute;
            width: 100%;
            height: 100%;
            top: 0px;
            left: 0px;
            display: flex;
            flex-direction: column;
            -webkit-box-pack: center;
            justify-content: center;
            -webkit-box-align: center;
            align-items: center;
            background-color: rgba(0, 0, 0, 0.4);
            z-index: 2;
            border-radius: 6px;
            border: none;
        }

        .cityItem .content h3 {
            color: #fff;
        }

        .slick-slide{
            margin:10px;
        }

        .slick-prev:before, .slick-next:before {
            color: #00d1b2 !important;
        }
        .slick-slide img {
            width:100%;
            border-radius: 6px;
        }

        .is-hidden {
            display: none;
        }
    </style>
    <script>
        $(document).ready(function () {
            $(".carousel").slick({
                slidesToShow: 4,
                dots: true,
                centerMode: true,
                autoplay: true
            });
        });
    </script>
</html>