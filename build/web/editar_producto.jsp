<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.mycompany.login.Producto" %>
<%
    // Recuperamos el producto que el Servlet nos envía para editar
    Producto p = (Producto) request.getAttribute("producto");
    if (p == null) {
        response.sendRedirect("ProductoServlet?action=list");
        return;
    }
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Editar Producto - ReadyCar</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/@picocss/pico@2/css/pico.min.css">
    <style>
        body { padding-top: 40px; background-color: #f8f9fa; }
        .form-container { max-width: 600px; margin: 0 auto; background: white; padding: 30px; border-radius: 10px; box-shadow: 0 4px 6px rgba(0,0,0,0.1); }
    </style>
</head>
<body>

<main class="container form-container">
    <h2 style="text-align: center;">Editar Producto</h2>
    <hr>
    
    <!-- El formulario envía los datos a la acción "update" -->
    <form action="ProductoServlet?action=update" method="post">
        
        <!-- ⚠️ Muy importante: el ID oculto para que la BD sepa a quién actualizar -->
        <input type="hidden" name="id" value="<%= p.getId() %>">
        
        <label>Nombre del Repuesto/Producto</label>
        <input type="text" name="txtNombre" value="<%= p.getNombre() %>" required>

        <label>Descripción</label>
        <!-- En los textarea, el valor va entre las etiquetas, no en un atributo "value" -->
        <textarea name="txtDescripcion" required><%= p.getDescripcion() %></textarea>

        <div class="grid">
            <div>
                <label>Precio ($)</label>
                <input type="number" step="0.01" name="txtPrecio" value="<%= p.getPrecio() %>" required>
            </div>
            <div>
                <label>Stock (Cantidad en bodega)</label>
                <input type="number" name="txtStock" value="<%= p.getStock() %>" required>
            </div>
        </div>

        <label>URL de la Imagen (Opcional)</label>
        <input type="text" name="txtImagen" value="<%= p.getImagen() != null ? p.getImagen() : "" %>">

        <label>
            <!-- Si el producto ya era destacado, marcamos la casilla automáticamente -->
            <input type="checkbox" name="chkDestacado" value="true" <%= p.isDestacado() ? "checked" : "" %>>
            <strong>¿Destacar este producto?</strong>
        </label>

        <button type="submit" style="margin-top: 20px;">Guardar Cambios</button>
    </form>

    <div style="text-align: center; margin-top: 15px;">
        <a href="ProductoServlet?action=list" class="secondary">Cancelar y Volver al Inventario</a>
    </div>
</main>

</body>
</html>
