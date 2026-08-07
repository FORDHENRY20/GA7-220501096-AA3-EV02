package com.mycompany.login;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession; 

/**
 * Servlet encargado de administrar todas las operaciones
 * relacionadas con los usuarios del sistema Ready Car.
 *
 * Funciones:
 * - Inicio de sesión
 * - Registro de usuarios
 * - Listado de usuarios
 * - Actualización de datos
 * - Eliminación de usuarios
 *
 * Autor: Henrry Daza López
 */

@WebServlet("/UsuarioServlet")
public class UsuarioServlet extends HttpServlet {

      // Objeto encargado de acceder a la base de datos de usuarios
    
    private UsuarioDAO usuarioDAO;

    @Override
    public void init() throws ServletException {
        
         // Inicializa el DAO al iniciar el servlet
        
        usuarioDAO = new UsuarioDAO();
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

    // Procesa todas las solicitudes GET y POST 
    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if (action == null) {
            action = "list";
        }

        try {
            switch (action) {
                case "login":
                    iniciarSesion(request, response);
                    break;

                case "new":
                    request.getRequestDispatcher("registro.jsp").forward(request, response);
                    break;

                case "insert":
                    insertarUsuario(request, response);
                    break;

                case "delete":
                    eliminarUsuario(request, response);
                    break;

                case "edit":
                    mostrarEditar(request, response);
                    break;

                case "update":
                    actualizarUsuario(request, response);
                    break;

                default:
                    listar(request, response);
                    break;
            }

        } catch (Exception e) {
            throw new ServletException("Error en UsuarioServlet", e);
        }
    }

    // Valida las credenciales del usuario e inicia la sesión
    private void iniciarSesion(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String correo = request.getParameter("txtEmail");
        String password = request.getParameter("txtPassword");

        Usuario u = usuarioDAO.validarLogin(correo, password);

        if (u != null) {
            HttpSession session = request.getSession();
            session.setAttribute("usuarioLogueado", u);

            if ("admin".equals(u.getRol())) {
                response.sendRedirect("UsuarioServlet?action=list");
            } else {
               
                response.sendRedirect("index.jsp"); 
            }
        } else {
            
            request.setAttribute("errorMensaje", "Correo o contraseña incorrectos");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }

    
    // Obtiene todos los usuarios registrados
    private void listar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {

        List<Usuario> lista = usuarioDAO.listarUsuarios();
        request.setAttribute("listaUsuario", lista);
        request.getRequestDispatcher("usuarios.jsp").forward(request, response);
    }

    // Registra un nuevo usuario en la base de datos
    private void insertarUsuario(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        Usuario u = new Usuario(
                request.getParameter("txtNombre"),
                request.getParameter("txtEmail"),
                request.getParameter("txtPassword"), 
                request.getParameter("txtTelefono"),
                request.getParameter("txtDireccion"),
                "cliente" 
        );

        usuarioDAO.insertarUsuario(u);
        response.sendRedirect("login.jsp"); // Redirigimos al login después de registrarse
    }

    // Elimina un usuario por su identificador
    private void eliminarUsuario(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        usuarioDAO.eliminarUsuario(id);
        response.sendRedirect("UsuarioServlet?action=list");
    }

    private void mostrarEditar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {

        int id = Integer.parseInt(request.getParameter("id"));
        Usuario u = usuarioDAO.obtenerUsuarioPorId(id);

        request.setAttribute("usuario", u);
        request.getRequestDispatcher("editar_usuario.jsp").forward(request, response);
    }

    // Actualiza la información del usuario seleccionado
    private void actualizarUsuario(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        Usuario u = new Usuario(
                Integer.parseInt(request.getParameter("id")),
                request.getParameter("txtNombre"),
                request.getParameter("txtEmail"),
                request.getParameter("txtPassword"),
                request.getParameter("txtTelefono"),
                request.getParameter("txtDireccion"),
                request.getParameter("txtRol")
        );

        usuarioDAO.actualizarUsuario(u);
        response.sendRedirect("UsuarioServlet?action=list");
    }
}