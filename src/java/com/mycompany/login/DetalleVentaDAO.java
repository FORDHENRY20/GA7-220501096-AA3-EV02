package com.mycompany.login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * DAO encargado de guardar el detalle de cada venta.
 */
public class DetalleVentaDAO {

    /**
     * Guarda un producto vendido en la tabla detalle_venta.
     *
     * @param detalle Información del producto vendido.
     * @return true si se guardó correctamente.
     */
    public boolean guardarDetalle(DetalleVenta detalle) {

        String sql = "INSERT INTO detalle_venta "
                + "(id_venta, id_producto, cantidad, precio_unitario) "
                + "VALUES (?, ?, ?, ?)";

        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, detalle.getIdVenta());
            ps.setInt(2, detalle.getIdProducto());
            ps.setInt(3, detalle.getCantidad());
            ps.setDouble(4, detalle.getPrecioUnitario());

            int filas = ps.executeUpdate();

            if (filas > 0) {

                System.out.println(
                        "Detalle guardado correctamente. "
                        + "Producto ID: " + detalle.getIdProducto());

                return true;

            }

        } catch (SQLException e) {

            System.out.println("Error al guardar el detalle de la venta.");
            e.printStackTrace();

        }

        return false;

    }

}