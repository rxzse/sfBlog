<%-- 
    Document   : category
    Created on : Mar 14, 2022, 10:30:46 AM
    Author     : RxZ
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Blog Management</title>
    <link rel="stylesheet" type="text/css" href="./views/css/index.css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.2.0/css/all.css">
    <link rel="stylesheet" href="https://unpkg.com/buefy/dist/buefy.min.css">
</head>

<body>
    <%@ include file="/WEB-INF/jspf/navbar.jspf" %>
    <div id="app">
        <section class="container">
            <div class="columns">
                <!-- Aside Menu -->
                <div class="column is-3">
                    <%@ include file="/WEB-INF/jspf/aside.jspf" %>
                </div>
                <!-- Content list -->
                <div class="column is-9">
                    <div class="box content">
                        <article class="post">
                            <h4>Post tile 1</h4>
                            <div class="media">
                                <div class="media-content">
                                    <div class="content">
                                        <p>
                                            <a href="#">@rxzse</a> Date created &nbsp;
                                            <span class="tag">Question</span>
                                        </p>
                                    </div>
                                </div>
                            </div>
                        </article>
                    </div>
                </div>
            </div>
        </section>
    </div>
    <%@ include file="/WEB-INF/jspf/footer.jspf" %>
    
</body>

</html>