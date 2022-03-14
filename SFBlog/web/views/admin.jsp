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
        <link rel="stylesheet" type="text/css" href="./views/css/login.css">
        <script src="https://cdnjs.cloudflare.com/ajax/libs/markdown-it/12.3.2/markdown-it.min.js"></script>
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
                            <c:if test = "${action == 'index'}">
                                <c:forEach items="${posts}" var="post">
                                    <article class="post">
                                        <h4>
                                            <a href="?post=${post.getAlias()}">${post.getTitle()}</a>

                                            <c:if test = "${sessionScope.isAdmin}">
                                                <span class="icon"> <i class="fa fa-edit" @click="edit_post({id: ${post.getId()}, title: '${post.getTitle()}', alias: '${post.getAlias()}', isActive: ${post.isIsActive()}, category: ${post.getCategory()}})"></i></span>
                                                <span class="icon"> <i class="fa fa-trash" @click="del_post({id: ${post.getId()}, title: '${post.getTitle()}'})"></i></span>
                                                </c:if>
                                        </h4>
                                        <div class="media">
                                            <div class="media-content">
                                                <div class="content">
                                                    <p>

                                                        ${post.getPublishTime()}
                                                        <span class="tag">${post.getCateName()}</span>
                                                        <c:if test = "${sessionScope.isAdmin}">
                                                            <span class="tag">Publish: ${post.isIsActive()} </span>
                                                        </c:if>
                                                    </p>
                                                </div>
                                            </div>
                                        </div>
                                    </article>
                                </c:forEach>
                            </c:if>
                            <c:if test = "${action == 'post'}">
                                <h2>${content.getTitle()}</h2> 
                                <small>Published at ${content.getPublishTime()}</small>

                                <hr>
                                <div class="content">
                                    <c:out value="${content.getHtml()}" escapeXml="false"/>
                                </div>
                            </c:if>
                        </div>
                    </div>
                </div>
            </section>
        </div>
        <%@ include file="/WEB-INF/jspf/footer.jspf" %>

    </body>
    <script src="https://unpkg.com/vue@2"></script>
    <script src="https://unpkg.com/buefy/dist/buefy.min.js"></script>
    <script async type="text/javascript" src="./views/js/app.js"></script>

</html>