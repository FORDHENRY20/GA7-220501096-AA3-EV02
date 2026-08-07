 package com.mycompany.login;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ProductoServlet")
public class ProductoServlet extends HttpServlet {

    private ProductoDAO productoDAO;

    @Override
    public void init() throws ServletException {
        productoDAO = new ProductoDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if (action == null) {
            action = "list";
        }

        try {
            switch (action) {
                case "new":
                    // Manda a la pantalla para crear un producto nuevo
                    request.getRequestDispatcher("nuevo_producto.jsp").forward(request, response);
                    break;

                case "insert":
                    insertarProducto(request, response);
                    break;

                case "delete":
                    eliminarProducto(request, response);
                    break;

                case "edit":
                    mostrarEditar(request, response);
                    break;

                case "update":
                    actualizarProducto(request, response);
                    break;

                default:
                    listar(request, response);
                    break;
            }

        } catch (Exception e) {
            throw new ServletException("Error en ProductoServlet", e);
        }
    }

    private void listar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Producto> lista = productoDAO.listarProductos();
        request.setAttribute("listaProductos", lista);
        request.getRequestDispatcher("productos.jsp").forward(request, response);
    }

    private void insertarProducto(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        
        
        Producto p = new Producto(
                request.getParameter("txtNombre"),
                request.getParameter("txtDescripcion"),
                Double.parseDouble(request.getParameter("txtPrecio")),
                Integer.parseInt(request.getParameter("txtStock")),
                request.getParameter("txtImagen"),
                request.getParameter("chkDestacado") != null // Si el checkbox está marcado, es true
        );

        productoDAO.insertarProducto(p);
        response.sendRedirect("ProductoServlet?action=list");
    }

    private void eliminarProducto(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        productoDAO.eliminarProducto(id);
        response.sendRedirect("ProductoServlet?action=list");
    }

    private void mostrarEditar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Producto p = productoDAO.obtenerProductoPorId(id);

        request.setAttribute("producto", p);
        request.getRequestDispatcher("editar_producto.jsp").forward(request, response);
    }

    private void actualizarProducto(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        Producto p = new Producto(
                Integer.parseInt(request.getParameter("id")),
                request.getParameter("txtNombre"),
                request.getParameter("txtDescripcion"),
                Double.parseDouble(request.getParameter("txtPrecio")),
                Integer.parseInt(request.getParameter("txtStock")),
                request.getParameter("txtImagen"),
                request.getParameter("chkDestacado") != null
        );

        productoDAO.actualizarProducto(p);
        response.sendRedirect("ProductoServlet?action=list");
    }
}