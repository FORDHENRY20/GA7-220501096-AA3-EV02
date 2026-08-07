package com.mycompany.login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO encargado de gestionar las ventas.
 */
public class VentaDAO {

    private Connection conexion;

    public VentaDAO() {

        conexion = ConexionBD.conectar();

    }

    // =====================================================
    // GUARDAR UNA NUEVA VENTA
    // =====================================================
    public int guardarVenta(Venta venta) {

        int idVenta = 0;

        String sql = "INSERT INTO ventas(id_usuario, total) VALUES(?, ?)";

        try {

            PreparedStatement ps = conexion.prepareStatement(
                    sql,
                    PreparedStatement.RETURN_GENERATED_KEYS
            );

            ps.setInt(1, venta.getIdUsuario());
            ps.setDouble(2, venta.getTotal());

            int filas = ps.executeUpdate();

            if (filas > 0) {

                ResultSet rs = ps.getGeneratedKeys();

                if (rs.next()) {

                    idVenta = rs.getInt(1);

                }

                System.out.println("Venta registrada correctamente. ID: " + idVenta);

            }

        } catch (SQLException e) {

            System.out.println("Error al guardar la venta.");
            e.printStackTrace();

        }

        return idVenta;

    }

    // =====================================================
    // ACTUALIZAR EL TOTAL DE LA VENTA
    // =====================================================
    public boolean actualizarTotalVenta(int idVenta, double total) {

        String sql = "UPDATE ventas SET total = ? WHERE id_venta = ?";

        try {

            PreparedStatement ps = conexion.prepareStatement(sql);

            ps.setDouble(1, total);
            ps.setInt(2, idVenta);

            int filas = ps.executeUpdate();

            if (filas > 0) {

                System.out.println("Total de la venta actualizado.");

                return true;

            }

        } catch (SQLException e) {

            System.out.println("Error al actualizar el total.");
            e.printStackTrace();

        }

        return false;

    }

    // =====================================================
    // LISTAR LAS VENTAS DE UN USUARIO
    // =====================================================
    public List<Venta> listarVentasPorUsuario(int idUsuario) {

        List<Venta> lista = new ArrayList<>();

        String sql = "SELECT * FROM ventas WHERE id_usuario = ? ORDER BY fecha DESC";

        try {

            PreparedStatement ps = conexion.prepareStatement(sql);

            ps.setInt(1, idUsuario);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Venta venta = new Venta();

                venta.setIdVenta(rs.getInt("id_venta"));
                venta.setIdUsuario(rs.getInt("id_usuario"));
                venta.setFecha(rs.getTimestamp("fecha"));
                venta.setTotal(rs.getDouble("total"));

                lista.add(venta);

            }

        } catch (SQLException e) {

            System.out.println("Error al listar las ventas.");
            e.printStackTrace();

        }

        return lista;

    }

}