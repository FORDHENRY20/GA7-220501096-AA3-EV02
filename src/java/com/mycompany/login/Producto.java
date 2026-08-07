package com.mycompany.login; 

public class Producto {
    
    private int id;
    private String nombre;
    private String descripcion;
    private double precio;
    private int stock;
    private String imagen;
    private boolean destacado;

    // Constructor vacío (Obligatorio para la base de datos)
    public Producto() {
    }

    // (Para consultar productos de la base de datos)
    public Producto(int id, String nombre, String descripcion, double precio, int stock, String imagen, boolean destacado) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.stock = stock;
        this.imagen = imagen;
        this.destacado = destacado;
    }

    // Constructor sin ID (Para insertar productos nuevos)
    public Producto(String nombre, String descripcion, double precio, int stock, String imagen, boolean destacado) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.stock = stock;
        this.imagen = imagen;
        this.destacado = destacado;
    }

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public boolean isDestacado() {
        return destacado;
    }

    public void setDestacado(boolean destacado) {
        this.destacado = destacado;
    }
}