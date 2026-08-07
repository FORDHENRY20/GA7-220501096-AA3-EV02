<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.mycompany.login.Producto" %>
<%@ page import="com.mycompany.login.ProductoDAO" %>
<%@ page import="com.mycompany.login.Usuario" %>
<%
Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");
%>

<!DOCTYPE html>
<html lang="es">

<head>

    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>Ready Car | Tienda Automotriz</title>

    <link rel="stylesheet" href="style.css?v=2">

</head>

<body>

    <!-- =========================
            ENCABEZADO
    ========================== -->

    <header class="header">

        <img src="images/bg.png" class="bg" alt="Fondo">

        <div class="menu container">

            <a href="index.jsp" class="logo">
                Ready Car
            </a>

            <input type="checkbox" id="menu">

            <label for="menu">
                <img src="images/menu.png"
                     class="menu-icono"
                     alt="Menú">
            </label>

            <nav class="navbar">

                <ul>

                    <li>
                        <a href="index.jsp">Inicio</a>
                    </li>

                    <li>
                        <a href="#productos-ancla">Productos</a>
                    </li>

                    <li>
                        <a href="#">Servicios</a>
                    </li>

                    <li>
                        <a href="#">Contacto</a>
                    </li>

                  <% if (usuario == null) { %>

                     <li>
                         <a href="login.jsp">
                            Iniciar sesión
                         </a>
                     </li>

                 <% } else { %>

                      <li>
                         <a href="#">
                           Hola, <%= usuario.getNombre() %>
                         </a>
                      </li>

                      <li>
                          <a href="HistorialComprasServlet">
                                Mis compras
                          </a>
                      </li>

                      <li>
                           <a href="LogoutServlet">
                                Cerrar sesión
                           </a>
                      </li>

                      <% } %>

                </ul>

            </nav>

            <!-- ==========================
                    CARRITO
            =========================== -->

            <div class="submenu">

                <img src="images/car.svg"
                     id="img-carrito"
                     alt="Carrito">

                <div id="carrito">

                    <table id="lista-carrito">

                        <thead>

                            <tr>

                                <th>Imagen</th>
                                <th>Producto</th>
                                <th>Precio</th>
                                <th>Cantidad</th>
                                <th></th>

                            </tr>

                        </thead>

                        <tbody>

                        </tbody>

                    </table>

                    <a href="#"
                       id="vaciar-carrito"
                       class="btn-3">

                        Vaciar carrito

                    </a>
                    
                    
                    <a href="FinalizarCompraServlet"
                          id="finalizar-compra"
                              class="btn-1">

                             Finalizar compra

                    </a>
                     
                    
                    
                </div>

            </div>

        </div>

        <div class="header-content container">

            <div class="header-txt">

                <h1>

                    <span>Ready Car</span>

                    tu aliado en repuestos
                    y mantenimiento

                </h1>

                <p>

                    Encuentra las mejores
                    autopartes y accesorios
                    para tu vehículo con
                    calidad garantizada.

                </p>

                <a href="#productos-ancla"
                   class="btn-1">

                    Ver catálogo

                </a>

            </div>

            <div class="header-img">

                <img src="images/coche.png"
                     alt="Automóvil">

            </div>

        </div>

    </header>

    <div id="productos-ancla"></div>
    
    <main class="products container">

    <h2>Catálogo de Productos</h2>

    <div class="box-container">

        <%

            ProductoDAO dao = new ProductoDAO();

            List<Producto> lista = dao.listarProductos();

            if(lista != null && !lista.isEmpty()){

                for(Producto p : lista){

        %>

        <div class="producto-card">

            <img src="images/<%= (p.getImagen()!=null && !p.getImagen().isEmpty()) ? p.getImagen() : "default.png" %>"
                 alt="<%= p.getNombre() %>">

            <h3><%= p.getNombre() %></h3>

            <p><%= p.getDescripcion() %></p>

            <p class="precio">

                $ <%= String.format("%,.0f", p.getPrecio()) %>

            </p>

            <% if(p.getStock() > 0){ %>

                <a href="#"
                   class="btn-1 agregar-carrito"
                   data-id="<%= p.getId() %>">

                    Agregar al carrito

                </a>

            <% }else{ %>

                <button class="btn-3" disabled>

                    Agotado

                </button>

            <% } %>

        </div>

        <%

                }

            }else{

        %>

            <p>No hay productos disponibles.</p>

        <%

            }

        %>

    </div>

</main>

<footer class="footer">

    <div class="footer-content container">

        <div class="link">

            <h3>Ready Car</h3>

            <ul>

                <li>

                    <a href="#">Sobre nosotros</a>

                </li>

                <li>

                    <a href="login.jsp">

                        Panel administrador

                    </a>

                </li>

            </ul>

        </div>

    </div>

</footer>

        <form id="formCompra"
      action="FinalizarCompraServlet"
      method="post"
      style="display:none;">

    <input type="hidden"
           name="carrito"
           id="carritoInput">

</form>
        
<script src="script.js"></script>

</body>
</html>