<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>

    <meta charset="UTF-8">

    <title>Compra realizada</title>

    <style>

        body{
            font-family: Arial, Helvetica, sans-serif;
            background:#f5f5f5;
            display:flex;
            justify-content:center;
            align-items:center;
            height:100vh;
        }

        .card{
            background:white;
            padding:40px;
            border-radius:15px;
            box-shadow:0px 0px 15px rgba(0,0,0,.2);
            text-align:center;
            width:400px;
        }

        h1{
            color:#27ae60;
        }

        p{
            color:#555;
        }

        a{
            display:inline-block;
            margin-top:20px;
            text-decoration:none;
            background:#ff9000;
            color:white;
            padding:12px 25px;
            border-radius:8px;
        }

        a:hover{
            background:#e67e00;
        }

    </style>

</head>

<body>

<div class="card">

    <h1>✅ Compra realizada correctamente</h1>

    <p>
        Gracias por comprar en <strong>Ready Car</strong>.
    </p>

    <a href="index.jsp">
        Volver a la tienda
    </a>

</div>

<script>

    // Vaciar el carrito después de una compra exitosa
    sessionStorage.removeItem("carritoReadyCar");

</script>

</body>

</html>