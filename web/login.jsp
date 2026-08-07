<%@ page contentType="text/html;charset=UTF-8" %>
<%
    // Detectar si viene de cierre de sesión
    String logout = request.getParameter("logout");
    boolean mostrarLogout = logout != null && logout.equals("1");
%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Login ReadyCar</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/@picocss/pico@2/css/pico.min.css">

    <style>
        body {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            background: linear-gradient(to right, #667eea, #764ba2);
            font-family: Arial, sans-serif;
            margin: 0;
        }

        .card {
            max-width: 400px;
            width: 100%;
            padding: 40px;
            border-radius: 20px;
            background-color: #ffffffee;
            box-shadow: 0px 8px 25px rgba(0,0,0,0.3);
            text-align: center;
        }

        h2 {
            margin-bottom: 25px;
            color: #333;
        }

        input[type="email"], input[type="password"] {
            width: 100%;
            padding: 12px 10px;
            margin: 10px 0;
            border: 1px solid #ccc;
            border-radius: 10px;
            box-sizing: border-box;
        }

        button {
            margin-top: 15px;
            background-color: #667eea;
            color: white;
            padding: 12px 20px;
            border: none;
            border-radius: 10px;
            cursor: pointer;
            width: 100%;
            font-size: 16px;
        }

        button:hover {
            background-color: #556cd6;
        }

        .error {
            color: red;
            margin-top: 10px;
            font-weight: bold;
        }

        /* ---------- MODAL DE CIERRE DE SESION ---------- */

        .modal-overlay {
            position: fixed;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            background: rgba(0,0,0,0.45);
            display: flex;
            justify-content: center;
            align-items: center;
            animation: fadeIn 0.3s ease;
        }

        .modal-window {
            background: white;
            padding: 25px;
            width: 350px;
            border-radius: 12px;
            text-align: center;
            box-shadow: 0px 5px 20px rgba(0,0,0,0.3);
            animation: popUp 0.35s ease;
        }

        .modal-window h3 {
            color: #28a745;
            margin-bottom: 10px;
        }

        .modal-window button {
            margin-top: 15px;
            background: #28a745;
            color: white;
            border: none;
            padding: 10px 20px;
            border-radius: 8px;
            cursor: pointer;
        }

        @keyframes fadeIn {
            from { opacity: 0; }
            to { opacity: 1; }
        }

        @keyframes popUp {
            from { transform: scale(0.7); opacity: 0; }
            to { transform: scale(1); opacity: 1; }
        }

    </style>

</head>
<body>

<article class="card">
    <h2>Iniciar Sesión ReadyCar</h2>

    <form action="UsuarioServlet?action=login" method="post">
        <input type="email" name="txtEmail" placeholder="Correo electrónico" required>
        <input type="password" name="txtPassword" placeholder="Contraseña" required>
        <button type="submit">Ingresar</button>
    </form>

    <div class="error">
        ${errorMensaje != null ? errorMensaje : ""}
    </div>

    <p>¿No tienes cuenta? 
        <a href="registro.jsp">Crear una</a>
    </p>
</article>

<% if (mostrarLogout) { %>
<div id="modalLogout" class="modal-overlay">
    <div class="modal-window">
        <h3>¡Sesión cerrada!</h3>
        <p>Has salido correctamente de tu cuenta.</p>
        <button onclick="cerrarModal()">Aceptar</button>
    </div>
</div>

<script>
    function cerrarModal() {
        document.getElementById("modalLogout").style.display = "none";
    }

    // Cierre automático en 2 segundos
    setTimeout(() => {
        cerrarModal();
    }, 2000);
</script>
<% } %>

</body>
</html>