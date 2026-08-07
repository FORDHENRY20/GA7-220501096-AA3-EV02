<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.mycompany.login.Producto" %>
<%
    // Recuperamos la lista de productos que nos envía el Servlet
    List<Producto> productos = (List<Producto>) request.getAttribute("listaProductos");
    
    // Si la lista está vacía o nula, redireccionamos al Servlet para que la cargue
    if (productos == null) {
        response.sendRedirect("ProductoServlet?action=list");
        return;
    }
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Inventario - ReadyCar</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/@picocss/pico@2/css/pico.min.css">
    <style>
        body { padding-top: 20px; }
        .header-actions { display: flex; justify-content: space-between; align-items: center; margin-bottom: 20px; }
    </style>
</head>
<body>

<main class="container">
    <div class="header-actions">
        <h2>Inventario de Productos</h2>
        <a href="ProductoServlet?action=new" role="button">➕ Agregar Producto</a>
    </div>
    
    <hr>

    <figure>
        <table class="striped">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Nombre</th>
                    <th>Precio</th>
                    <th>Stock</th>
                    <th>Acciones</th>
                </tr>
            </thead>
            <tbody>
                <% for (Producto p : productos) { %>
                <tr>
                    <td><%= p.getId() %></td>
                    <td><%= p.getNombre() %></td>
                    <td>$<%= p.getPrecio() %></td>
                    <td><%= p.getStock() %> unids.</td>
                    <td>
                        <a href="ProductoServlet?action=edit&id=<%= p.getId() %>" role="button" class="outline secondary" style="padding: 5px 10px; font-size: 0.8em;">Editar</a>
                        <a href="ProductoServlet?action=delete&id=<%= p.getId() %>" role="button" class="outline contrast" style="padding: 5px 10px; font-size: 0.8em;" onclick="return confirm('¿Seguro que deseas eliminar este producto?');">Eliminar</a>
                    </td>
                </tr>
                <% } %>
            </tbody>
        </table>
    </figure>

    <a href="UsuarioServlet?action=list">⬅ Volver al Panel de Usuarios</a>
</main>

</body>
</html>e