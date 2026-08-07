package com.mycompany.login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Clase encargada de establecer la conexión con la base de datos
 * MySQL del proyecto Ready Car.
 *
 * Autor: Henrry Daza López
 */

public class ConexionBD {

      // Datos de conexión a la base de datos
    private static final String URL = "jdbc:mysql://localhost:3307/readycardb?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "";

        /**
     * Crea y devuelve una conexión con la base de datos.
     *
     * @return Objeto Connection si la conexión es exitosa,
     *         o null en caso de error.
     */
    public static Connection conectar() {
        Connection conexion = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("✔ Conexión exitosa a la BD");
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("❌ Error al conectar a la BD:");
            e.printStackTrace();
        }
        return conexion;
    }
}