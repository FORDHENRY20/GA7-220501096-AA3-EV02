package com.mycompany.login;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    // INSERTAR USUARIO
    public boolean insertarUsuario(Usuario u) {

        String sql = "INSERT INTO usuarios(nombre, correo, password, telefono, direccion, rol) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, u.getNombre());
            ps.setString(2, u.getCorreo());
            ps.setString(3, u.getClave());
            ps.setString(4, u.getTelefono());
            ps.setString(5, u.getDireccion());
            ps.setString(6, u.getRol());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // LISTAR USUARIOS
    public List<Usuario> listarUsuarios() {

        List<Usuario> lista = new ArrayList<>();
        String sql = "SELECT * FROM usuarios ORDER BY id DESC";

        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Usuario u = new Usuario(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("correo"),
                        rs.getString("password"),
                        rs.getString("telefono"),
                        rs.getString("direccion"),
                        rs.getString("rol")
                );

                lista.add(u);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    // OBTENER USUARIO POR ID
    public Usuario obtenerUsuarioPorId(int id) {

        String sql = "SELECT * FROM usuarios WHERE id = ?";

        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {

                    return new Usuario(
                            rs.getInt("id"),
                            rs.getString("nombre"),
                            rs.getString("correo"),
                            rs.getString("password"),
                            rs.getString("telefono"),
                            rs.getString("direccion"),
                            rs.getString("rol")
                    );
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    // ACTUALIZAR USUARIO
    public boolean actualizarUsuario(Usuario u) {

        String sql = "UPDATE usuarios SET nombre=?, correo=?, password=?, telefono=?, direccion=?, rol=? WHERE id=?";

        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, u.getNombre());
            ps.setString(2, u.getCorreo());
            ps.setString(3, u.getClave());
            ps.setString(4, u.getTelefono());
            ps.setString(5, u.getDireccion());
            ps.setString(6, u.getRol());
            ps.setInt(7, u.getId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // ELIMINAR USUARIO
    public boolean eliminarUsuario(int id) {

        String sql = "DELETE FROM usuarios WHERE id=?";

        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
    
    //  MÉTODO NUEVO PARA VALIDAR EL INICIO DE SESIÓN
public Usuario validarLogin(String correo, String password) {
    String sql = "SELECT * FROM usuarios WHERE correo = ? AND password = ?";
    
    try (Connection con = ConexionBD.conectar();
         PreparedStatement ps = con.prepareStatement(sql)) {
        
        ps.setString(1, correo);
        ps.setString(2, password);
        
        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return new Usuario(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("correo"),
                        rs.getString("password"),
                        rs.getString("telefono"),
                        rs.getString("direccion"),
                        rs.getString("rol")
                );
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return null; // Retorna null si el usuario o contraseña son incorrectos
}
    
}