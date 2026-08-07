package com.mycompany.login;

import java.util.Date;

public class Venta {

    private int idVenta;
    private int idUsuario;
    private Date fecha;
    private double total;

    public Venta() {
    }

    public Venta(int idVenta, int idUsuario, Date fecha, double total) {
        this.idVenta = idVenta;
        this.idUsuario = idUsuario;
        this.fecha = fecha;
        this.total = total;
    }

    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}