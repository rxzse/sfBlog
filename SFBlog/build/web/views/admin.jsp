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
                        <div class="box content" style="margin-top: 0">
                            <c:if test = "${action == 'index'}">
                                <table border="1">
                                    <thead>
                                        <tr>
                                            <th>ID</th>
                                            <th>Title</th>
                                            <th>Publish Time</th>
                                            <th>Category</th>
                                            <th>Status</th>
                                            <th>Edit</th>
                                            <th>Delete</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${posts}" var="post">

                                            <tr>
                                                <td>${post.getId()}</td>
                                                <td><a href="?post=${post.getAlias()}">${post.getTitle()}</a></td>
                                                <td>${post.getPublishTime()}</td>
                                                <td>${post.getCateName()}</td>
                                                
                                                <td>
                                                    <c:if test = "${sessionScope.isAdmin && post.isIsActive()}">
                                                        <span class="has-text-grey-light"><i class="fa fa-eye"></i></span>
                                                        </c:if>
                                                        <c:if test = "${!post.isIsActive()}">
                                                        <span class="has-text-grey-light"><i class="fa fa-eye-slash"></i></span>
                                                        </c:if>
                                                </td>
                                                <td><span class="icon"> <i class="fa fa-edit" @click="edit_post({id: ${post.getId()}, title: '${post.getTitle()}', alias: '${post.getAlias()}', isActive: ${post.isIsActive()}, category: ${post.getCategory()}})"></i></span></td>
                                                <td><span class="icon"> <i class="fa fa-trash" @click="del_post({id: ${post.getId()}, title: '${post.getTitle()}'})"></i></span></td>
                                            </tr>

                                        </c:forEach>
                                    </tbody>
                                </table>

                                
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
                        <c:if test = "${action == 'index'}">
                            <!--                            <nav class="pagination" role="navigation" aria-label="pagination">
                                                            <a id="priv" class="pagination-previous">Previous</a>
                                                            <a id="next" class="pagination-next">Next page</a>
                                                        </nav>-->
                        </c:if>
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