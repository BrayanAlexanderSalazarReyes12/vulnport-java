<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>VulnPort Java</title>
    <style>
        body { font-family: Arial, sans-serif; max-width: 980px; margin: 30px auto; padding: 0 20px; background:#f5f7fa; color:#1f2937; }
        .card { background:white; border:1px solid #dbe3ea; border-radius:10px; padding:18px; margin-bottom:16px; }
        input, button { padding:9px; margin:4px 0; }
        a { color:#075985; }
        code { background:#eef2f7; padding:2px 5px; }
        .warning { background:#fff3cd; border:1px solid #ffe69c; padding:12px; border-radius:8px; }
    </style>
</head>
<body>
<h1>VulnPort Java</h1>
<div class="warning">
    Laboratorio intencionalmente vulnerable para pruebas del Auditor. No usar en producción.
</div>

<div class="card">
    <h2>Login</h2>
    <form method="post" action="login">
        <input name="username" placeholder="Usuario" value="user">
        <input name="password" type="password" placeholder="Contraseña" value="user123">
        <input name="sessionId" placeholder="Session ID externo">
        <button type="submit">Entrar</button>
    </form>
</div>

<div class="card">
    <h2>Búsqueda</h2>
    <form method="get" action="search">
        <input name="q" placeholder="Producto o cliente">
        <button type="submit">Buscar</button>
    </form>
</div>

<div class="card">
    <h2>Perfil</h2>
    <p><a href="profile?id=1">Ver perfil 1</a> · <a href="profile?id=2">Ver perfil 2</a> · <a href="profile?id=3">Ver perfil 3</a></p>
</div>

<div class="card">
    <h2>Archivos</h2>
    <p><a href="download?file=manual.txt">Descargar manual.txt</a></p>
    <form method="post" action="upload" enctype="multipart/form-data">
        <input type="file" name="file">
        <button type="submit">Subir archivo</button>
    </form>
</div>

<div class="card">
    <h2>Diagnóstico y red</h2>
    <form method="get" action="diagnostic">
        <input name="host" value="127.0.0.1">
        <button type="submit">Ping</button>
    </form>
    <form method="get" action="fetch">
        <input name="url" value="http://example.com" style="width:360px">
        <button type="submit">Fetch URL</button>
    </form>
</div>

<div class="card">
    <h2>Otros endpoints</h2>
    <p>
        <a href="admin">Admin</a> ·
        <a href="debug?number=no-es-numero">Debug</a> ·
        <a href="go?next=index.jsp">Redirect</a>
    </p>
    <p>XML: POST a <code>/xml-import</code></p>
</div>
</body>
</html>
