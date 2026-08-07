package com.mycompany.login;

/**
 * Modelo que representa el detalle de una venta.
 * Cada registro corresponde a un producto comprado.
 */
public class DetalleVenta {

    // ==========================
    // ATRIBUTOS
    // ==========================

    private int idDetalle;
    private int idVenta;
    private int idProducto;
    private int cantidad;
    private double precioUnitario;

    // ==========================
    // CONSTRUCTORES
    // ==========================

    public DetalleVenta() {
    }

    public DetalleVenta(int idDetalle,
                        int idVenta,
                        int idProducto,
                        int cantidad,
                        double precioUnitario) {

        this.idDetalle = idDetalle;
        this.idVenta = idVenta;
        this.idProducto = idProducto;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;

    }

    // ==========================
    // GETTERS Y SETTERS
    // ==========================

    public int getIdDetalle() {
        return idDetalle;
    }

    public void setIdDetalle(int idDetalle) {
        this.idDetalle = idDetalle;
    }

    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

}