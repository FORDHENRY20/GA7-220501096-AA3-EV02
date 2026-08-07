package com.mycompany.login;

public class Usuario {

    private int id;
    private String nombre;
    private String correo;
    private String clave;
    private String telefono;
    private String direccion;
    private String rol; 

    
    public Usuario() {
    }

    
    public Usuario(int id, String nombre, String correo, String clave,
                   String telefono, String direccion, String rol) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.clave = clave;
        this.telefono = telefono;
        this.direccion = direccion;
        this.rol = rol;
    }

    // Constructor sin ID (Para registrar usuarios nuevos.)
    public Usuario(String nombre, String correo, String clave,
                   String telefono, String direccion, String rol) {
        this.nombre = nombre;
        this.correo = correo;
        this.clave = clave;
        this.telefono = telefono;
        this.direccion = direccion;
        this.rol = rol;
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

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
}