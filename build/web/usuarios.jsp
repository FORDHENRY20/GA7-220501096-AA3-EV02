<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.mycompany.login.Usuario" %>

<html>
<head>
    <title>Usuarios</title>
    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/@picocss/pico@2/css/pico.min.css">
</head>

<body>

<article class="container">
    <h2>Usuarios Registrados</h2>

    <div style="display: flex; gap: 15px; margin-bottom: 20px;">
        <a href="UsuarioServlet?action=new">
            <button>Nuevo Usuario</button>
        </a>
        
        <a href="ProductoServlet?action=list">
            <button class="contrast">📦 Ir al Inventario de Productos</button>
        </a>
    </div>

    <table>
        <thead>
        <tr>
            <th>ID</th>
            <th>Nombre</th>
            <th>Correo</th>
            <th>Clave</th>
            <th>Acciones</th>
        </tr>
        </thead>

        <tbody>
        <%
            List<Usuario> lista = (List<Usuario>) request.getAttribute("listaUsuario");
            if (lista != null) {
                for (Usuario u : lista) {
        %>
        <tr>
            <td><%= u.getId() %></td>
            <td><%= u.getNombre() %></td>
            <td><%= u.getCorreo() %></td>
            <td><%= u.getClave() %></td>

            <td>
                <a href="UsuarioServlet?action=edit&id=<%= u.getId() %>">Editar</a> |
                <a href="UsuarioServlet?action=delete&id=<%= u.getId() %>"
                   onclick="return confirm('¿Eliminar usuario?')">Eliminar</a>
            </td>
        </tr>
        <%      }
            }
        %>
        </tbody>
    </table>

    <a href="inicio.jsp">Volver al inicio</a>
</article>

</body>
</html>