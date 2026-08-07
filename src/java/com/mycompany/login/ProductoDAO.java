package com.mycompany.login;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAO {

    // =====================================================
    // LISTAR TODOS LOS PRODUCTOS
    // =====================================================
    public List<Producto> listarProductos() {

        List<Producto> lista = new ArrayList<>();

        String sql = "SELECT * FROM productos ORDER BY id DESC";

        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Producto p = new Producto(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("descripcion"),
                        rs.getDouble("precio"),
                        rs.getInt("stock"),
                        rs.getString("imagen"),
                        rs.getBoolean("destacado")
                );

                lista.add(p);

            }

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return lista;

    }

    // =====================================================
    // INSERTAR PRODUCTO
    // =====================================================
    public boolean insertarProducto(Producto p) {

        String sql = "INSERT INTO productos(nombre, descripcion, precio, stock, imagen, destacado) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, p.getNombre());
            ps.setString(2, p.getDescripcion());
            ps.setDouble(3, p.getPrecio());
            ps.setInt(4, p.getStock());
            ps.setString(5, p.getImagen());
            ps.setBoolean(6, p.isDestacado());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return false;

    }

    // =====================================================
    // OBTENER PRODUCTO POR ID
    // =====================================================
    public Producto obtenerProductoPorId(int id) {

        String sql = "SELECT * FROM productos WHERE id = ?";

        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {

                    return new Producto(
                            rs.getInt("id"),
                            rs.getString("nombre"),
                            rs.getString("descripcion"),
                            rs.getDouble("precio"),
                            rs.getInt("stock"),
                            rs.getString("imagen"),
                            rs.getBoolean("destacado")
                    );

                }

            }

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return null;

    }

    // =====================================================
    // ACTUALIZAR PRODUCTO
    // =====================================================
    public boolean actualizarProducto(Producto p) {

        String sql = "UPDATE productos SET nombre=?, descripcion=?, precio=?, stock=?, imagen=?, destacado=? WHERE id=?";

        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, p.getNombre());
            ps.setString(2, p.getDescripcion());
            ps.setDouble(3, p.getPrecio());
            ps.setInt(4, p.getStock());
            ps.setString(5, p.getImagen());
            ps.setBoolean(6, p.isDestacado());
            ps.setInt(7, p.getId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return false;

    }

    // =====================================================
    // ELIMINAR PRODUCTO
    // =====================================================
    public boolean eliminarProducto(int id) {

        String sql = "DELETE FROM productos WHERE id = ?";

        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return false;

    }

    // =====================================================
    // DESCONTAR STOCK
    // =====================================================
    public boolean descontarStock(int idProducto, int cantidad) {

        String sql = "UPDATE productos SET stock = stock - ? WHERE id = ?";

        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, cantidad);
            ps.setInt(2, idProducto);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return false;

    }

    // =====================================================
    // OBTENER STOCK ACTUAL
    // =====================================================
    public int obtenerStock(int idProducto) {

        String sql = "SELECT stock FROM productos WHERE id = ?";

        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idProducto);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                return rs.getInt("stock");

            }

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return 0;

    }

}