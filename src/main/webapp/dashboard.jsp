<%@ page contentType="text/html;charset=UTF-8" %>
<%
    Object usuario = session.getAttribute("user");
    Object rol = session.getAttribute("role");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Dashboard - VulnPort</title>
</head>
<body>
<h1>Dashboard VulnPort</h1>
<p>Usuario: <%= usuario %></p>
<p>Rol: <%= rol %></p>

<ul>
    <li><a href="index.jsp">Inicio</a></li>
    <li><a href="admin">Panel administrativo</a></li>
    <li><a href="profile?id=1">Perfil por ID</a></li>
    <li><a href="debug?number=test">Debug</a></li>
</ul>
</body>
</html>
