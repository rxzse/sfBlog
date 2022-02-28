<%-- 
    Document   : index
    Created on : Feb 28, 2022, 3:55:04 PM
    Author     : RxZ
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h3> 
            First: 
            (${names[0]}, ${names[1]}, ${names[2]})
        </h3>
        <h3>
            Second: 
            (${nameArray[0]}, ${nameArray[1]}, ${nameArray[2]})
        </h3>

        <h3>
            Third: 
            (${nameArrayList.get(0)}, ${nameArrayList.get(1)}, ${nameArrayList.get(2)})
        </h3>
        
        <h3>
            Fourth 
            (${employeeArray[0]}, ${employeeArray[1]}, ${employeeArray[2]})
        </h3>
        
    </body>
</html>