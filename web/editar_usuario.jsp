<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.mycompany.login.Usuario" %>

<%
    Usuario u = (Usuario) request.getAttribute("usuario");
    if (u == null) {
        response.sendRedirect("UsuarioServlet?action=list");
        return;
    }
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Editar Usuario</title>
    <link rel="stylesheet" 
          href="https://cdn.jsdelivr.net/npm/@picocss/pico@2/css/pico.min.css">
</head>

<body>

<article class="container">
    <h2>Editar Usuario</h2>

    <form action="UsuarioServlet?action=update" method="post">
        <input type="hidden" name="id" value="<%= u.getId() %>">

        <label>Nombre</label>
        <input type="text" name="txtNombre" value="<%= u.getNombre() %>" required>

        <label>Correo</label>
        <input type="email" name="txtEmail" value="<%= u.getCorreo() %>" required>

        <label>Clave</label>
        <input type="text" name="txtPassword" value="<%= u.getClave() %>" required>

        <label>Teléfono</label>
        <input type="text" name="txtTelefono" value="<%= u.getTelefono() %>">

        <label>Dirección</label>
        <input type="text" name="txtDireccion" value="<%= u.getDireccion() %>">

        <label>Rol (admin o cliente)</label>
        <input type="text" name="txtRol" value="<%= u.getRol() %>" required>

        <button type="submit">Guardar Cambios</button>
    </form>

    <a href="UsuarioServlet?action=list">Cancelar</a>
</article>

</body>
</html>