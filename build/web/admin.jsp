<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Panel Admin - Ready Car</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/@picocss/pico@2/css/pico.min.css">
    <style>
        body { padding-top: 40px; }
        .container { background: white; padding: 30px; border-radius: 10px; box-shadow: 0 4px 6px rgba(0,0,0,0.1); }
    </style>
</head>
<body>

<main class="container">
    <h1>📊 Panel de Administración</h1>
    <a href="index.jsp">⬅️ Volver a la tienda</a>
    <hr>

    <h3>🆕 Registrar Nuevo Producto</h3>
    <form action="ProductoServlet?action=insert" method="post">
        <input type="text" name="txtNombre" placeholder="Nombre del producto" required>
        <input type="text" name="txtDescripcion" placeholder="Descripción">
        <input type="number" name="txtPrecio" step="0.01" placeholder="Precio" required>
        <input type="number" name="txtStock" placeholder="Stock Inicial" required>
        <input type="text" name="txtImagen" placeholder="URL Imagen (Ej: images/aceite.jpg)">
        
        <select name="chkDestacado">
            <option value="false">Normal</option>
            <option value="true">Destacado ⭐</option>
        </select>
        
        <button type="submit">Guardar Producto</button>
    </form>

    <hr>
    
    <a href="ProductoServlet?action=list" role="button" class="contrast">Ver Inventario Completo</a>
</main>

</body>
</html>