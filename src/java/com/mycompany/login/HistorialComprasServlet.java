package com.mycompany.login;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/HistorialComprasServlet")
public class HistorialComprasServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        // ==============================
        // VALIDAR SESIÓN
        // ==============================
        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("usuarioLogueado") == null) {

            response.sendRedirect("login.jsp");
            return;

        }

        // ==============================
        // OBTENER USUARIO
        // ==============================
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");

        // ==============================
        // CONSULTAR SUS COMPRAS
        // ==============================
        VentaDAO ventaDAO = new VentaDAO();

        List<Venta> listaVentas = ventaDAO.listarVentasPorUsuario(usuario.getId());

        // ==============================
        // ENVIAR LA LISTA AL JSP
        // ==============================
        request.setAttribute("listaVentas", listaVentas);

        request.getRequestDispatcher("historialCompras.jsp")
               .forward(request, response);

    }

}