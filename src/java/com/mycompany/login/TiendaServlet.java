package com.mycompany.login;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/TiendaServlet")
public class TiendaServlet extends HttpServlet {

    private ProductoDAO productoDAO;

    @Override
    public void init() throws ServletException {
       
        productoDAO = new ProductoDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // 1. Obtenemos la lista de productos disponibles desde MySQL
        List<Producto> listaProductos = productoDAO.listarProductos();
        
        // 2. Adjuntamos la lista a la petición para que la página web pueda leerla
        request.setAttribute("productosTienda", listaProductos);
        
        // 3. Redirigimos al usuario a la pantalla visual de la tienda
        request.getRequestDispatcher("tienda.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}