<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="./css/style.css" />
</head>
<body>
    <header>
        <a href="${pageContext.request.contextPath}/index.jsp">Home</a>
        <a href="${pageContext.request.contextPath}/area_secretaria.jsp">Secretaria</a>
        <a href="${pageContext.request.contextPath}/area_aluno.jsp">Aluno</a>
        <a href="${pageContext.request.contextPath}/area_professor.jsp">Professor</a>
    </header>
</body>
</html>
