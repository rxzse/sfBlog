<%-- 
    Document   : auth
    Created on : Mar 13, 2022, 10:26:28 PM
    Author     : RxZ
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>



<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Auth - Administrator</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <!-- <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,700" rel="stylesheet"> -->
        <link href="https://fonts.googleapis.com/css?family=Questrial&display=swap" rel="stylesheet">
        <!-- Bulma Version 0.9.0-->
        <link rel="stylesheet" href="https://unpkg.com/bulma@0.9.0/css/bulma.min.css" />
        <link rel="stylesheet" type="text/css" href="./views/css/login.css">
        <%
            boolean isCreated = ((String) request.getAttribute("isCreated")).equals("yes");
            String authType = (String) request.getAttribute("type");
            String errType = (String) request.getAttribute("error");
            if (!isCreated) {
                authType = "new";
            }
            String ref = (String) request.getAttribute("continue");
            ref = ref != null ? ref : "/admin";
        %>
    </head>
    <body>
        <section class="hero is-success is-fullheight">
            <div class="hero-body">
                <div class="container has-text-centered">
                    <div class="column is-4 is-offset-4">
                        <h3 class="title has-text-black">
                            <%if (!isCreated) {%>
                            New setup Auth for Administrator
                            <%} else if ("chgpwd".equals(authType)) {%>
                            Change password
                            <%} else {%>
                            Login
                            <%}%>
                        </h3>
                        <hr class="login-hr">
                        <p class="subtitle has-text-black">Please enter password to proceed.</p>
                        <%if (errType != null) {%>
                        <p class="subtitle has-text-black" style="color: red">(~_~) Kiểm tra lại thông số!</p>
                        <%}%>
                        <div class="box">
                            <form method="post">

                                <div class="field">
                                    <div class="control">
                                        <%if (!"chgpwd".equals(authType)) {%>
                                        <input class="input is-large" type="password" name="password" placeholder="Your Password">
                                        <%} else {%>
                                        <input class="input is-large" type="password" name="old_pass" placeholder="Old password">
                                        <input class="input is-large" type="password" name="new_pass" placeholder="New Password">
                                        <%}%>
                                        <input hidden name="type" value="<%=authType%>">
                                        <input hidden name="continue" value="<%=ref%>">
                                    </div>
                                </div>
                                <button type="submit" class="button is-block is-info is-large is-fullwidth">Login <i class="fa fa-sign-in"
                                                                                                                     aria-hidden="true"></i></button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <script async type="text/javascript" src="../views/js/base.js"></script>
    </body>
</html>
