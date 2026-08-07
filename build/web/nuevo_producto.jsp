<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Nuevo Producto - ReadyCar</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/@picocss/pico@2/css/pico.min.css">
    <style>
        body { padding-top: 40px; background-color: #f8f9fa; }
        .form-container { max-width: 600px; margin: 0 auto; background: white; padding: 30px; border-radius: 10px; box-shadow: 0 4px 6px rgba(0,0,0,0.1); }
    </style>
</head>
<body>

<main class="container form-container">
    <h2 style="text-align: center;">Agregar Nuevo Producto</h2>
    <hr>
    
    <form action="ProductoServlet?action=insert" method="post">
        
        <label>Nombre del Repuesto/Producto</label>
        <input type="text" name="txtNombre" placeholder="Ej: Aceite Motor 5W30" required>

        <label>Descripción</label>
        <textarea name="txtDescripcion" placeholder="Detalles del producto..." required></textarea>

        <div class="grid">
            <div>
                <label>Precio ($)</label>
                <input type="number" step="0.01" name="txtPrecio" placeholder="Ej: 150000" required>
            </div>
            <div>
                <label>Stock (Cantidad en bodega)</label>
                <input type="number" name="txtStock" placeholder="Ej: 10" required>
            </div>
        </div>

        <label>URL de la Imagen (Opcional)</label>
        <input type="text" name="txtImagen" placeholder="https://ejemplo.com/imagen.jpg">

        <label>
            <input type="checkbox" name="chkDestacado" value="true">
            <strong>¿Destacar este producto?</strong> (Aparecerá en la vitrina principal de la tienda)
        </label>

        <button type="submit" style="margin-top: 20px;">Guardar Producto</button>
    </form>

    <div style="text-align: center; margin-top: 15px;">
        <a href="ProductoServlet?action=list" class="secondary">Cancelar y Volver al Inventario</a>
    </div>
</main>

</body>
</html>