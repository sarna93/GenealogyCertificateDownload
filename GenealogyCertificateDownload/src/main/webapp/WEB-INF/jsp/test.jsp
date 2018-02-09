<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
    <head>
    </head>
    <body>
        <h3>Welcome, Enter The Employee Details</h3>
        <form:form method="POST" action="/szukajwarchiwach/download" modelAttribute="downloadForm">
             <table>
                <tr>
                    <td><form:label path="downloadType">Name</form:label></td>
                    <td><form:input path="downloadType"/></td>
                </tr>
                <tr>
                    <td><form:label path="linkToCertificate">Id</form:label></td>
                    <td><form:input path="linkToCertificate"/></td>
                </tr>
                <tr>
                    <td><input type="submit" value="Submit"/></td>
                </tr>
            </table>
        </form:form>
    </body>
</html>