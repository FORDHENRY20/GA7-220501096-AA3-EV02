package com.mycompany.login;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/FinalizarCompraServlet")
public class FinalizarCompraServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        // ==========================================
        // VALIDAR SESIÓN
        // ==========================================
        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("usuarioLogueado") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");

        // ==========================================
        // RECIBIR EL CARRITO
        // ==========================================
        String carritoJSON = request.getParameter("carrito");

        Gson gson = new Gson();
        Type tipoLista = new TypeToken<List<CarritoItem>>() {
        }.getType();

        List<CarritoItem> carrito = gson.fromJson(carritoJSON, tipoLista);
        
        if (carrito == null || carrito.isEmpty()) {

    System.out.println("El carrito está vacío.");

    response.sendRedirect("index.jsp");

    return;

}

        // ==========================================
        // MOSTRAR PRODUCTOS RECIBIDOS
        // ==========================================
        System.out.println("========== PRODUCTOS ==========");

        for (CarritoItem item : carrito) {

            System.out.println(item.getNombre()
                    + " | Cantidad: " + item.getCantidad()
                    + " | Precio: " + item.getPrecio());

        }

        System.out.println("===============================");
        
             // ==========================================
             // VALIDAR STOCK DISPONIBLE
            // ==========================================

              ProductoDAO productoDAO = new ProductoDAO();

                 for (CarritoItem item : carrito) {

                 int stockDisponible = productoDAO.obtenerStock(item.getId());

                 if (item.getCantidad() > stockDisponible) {

                 System.out.println("Stock insuficiente para: " + item.getNombre());

                 response.sendRedirect("index.jsp");

                 return;

                 }

                 }
        

        // ==========================================
        // CREAR LA VENTA
        // ==========================================
        Venta venta = new Venta();
        venta.setIdUsuario(usuario.getId());
        venta.setTotal(0);

        VentaDAO ventaDAO = new VentaDAO();

        int idVenta = ventaDAO.guardarVenta(venta);

        if (idVenta <= 0) {

            System.out.println("No se pudo crear la venta.");

            response.sendRedirect("index.jsp");

            return;

        }

        System.out.println("================================");
System.out.println("VENTA CREADA CORRECTAMENTE");
System.out.println("ID VENTA: " + idVenta);
System.out.println("CLIENTE: " + usuario.getNombre());
System.out.println("================================");

        // ==========================================
        // GUARDAR DETALLE DE LA VENTA
        // ==========================================
        DetalleVentaDAO detalleDAO = new DetalleVentaDAO();
        

        double totalVenta = 0;

        for (CarritoItem item : carrito) {

            DetalleVenta detalle = new DetalleVenta();

            detalle.setIdVenta(idVenta);
            detalle.setIdProducto(item.getId());
            detalle.setCantidad(item.getCantidad());

            String precioTexto = item.getPrecio()
                    .replace("$", "")
                    .replace(".", "")
                    .replace(",", "")
                    .trim();

            double precio = Double.parseDouble(precioTexto);

            detalle.setPrecioUnitario(precio);

            totalVenta += precio * item.getCantidad();

          if (detalleDAO.guardarDetalle(detalle)) {

    productoDAO.descontarStock(item.getId(), item.getCantidad());

} else {

    System.out.println("No se pudo guardar el detalle del producto: "
            + item.getNombre());

}

        }

       System.out.println("================================");
System.out.println("TOTAL DE LA VENTA: $" + totalVenta);
System.out.println("================================");

// ==========================================
// ACTUALIZAR TOTAL DE LA VENTA
// ==========================================

if (ventaDAO.actualizarTotalVenta(idVenta, totalVenta)) {

    System.out.println("Total actualizado correctamente.");

} else {

    System.out.println("No se pudo actualizar el total de la venta.");

}

// ==========================================
// REDIRECCIONAR
// ==========================================

response.sendRedirect("compraExitosa.jsp");

    }

}