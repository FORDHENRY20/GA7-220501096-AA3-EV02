<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Registrar Usuario</title>

    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/@picocss/pico@2/css/pico.min.css">

    <style>
        body {
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
            background-color: #f2f2f2;
        }

        .card {
            width: 100%;
            max-width: 500px;
            background: #fff;
            padding: 35px;
            border-radius: 15px;
            box-shadow: 0px 0px 15px rgba(0,0,0,0.1);
        }

        h2 {
            text-align: center;
            margin-bottom: 20px;
        }

        a {
            display: block;
            text-align: center;
            margin-top: 15px;
        }
    </style>
</head>

<body>

<article class="card">
    <h2>Registrar Usuario</h2>

    <form action="UsuarioServlet?action=insert" method="post">

        <label>Nombre Completo</label>
        <input type="text"
               name="txtNombre"
               placeholder="Juan Pérez"
               required>

        <label>Teléfono</label>
        <input type="text"
               name="txtTelefono"
               placeholder="3001234567">

        <label>Dirección</label>
        <input type="text"
               name="txtDireccion"
               placeholder="Calle 10 #20-30">

        <label>Correo Electrónico</label>
        <input type="email"
               name="txtEmail"
               placeholder="usuario@email.com"
               required>

        <label>Contraseña</label>
        <input type="password"
               name="txtPassword"
               placeholder="********"
               required>

        <button type="submit" style="width:100%;">
            Registrar
        </button>

    </form>

    <a href="UsuarioServlet?action=list">Volver</a>

</article>

</body>
</html>