/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.comedor.controlador.ConexionBD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Luis Trabuco
 */
public class PostgresSQL {

    public static Connection conexion = null;

    public PostgresSQL() {
    }

    public Connection establecer() throws SQLException {
        String host = "jdbc:postgresql://localhost:5432/Deliguay";
        String user = "postgres";
        //String password = "aszx1992";
        String password = "@lumno123";
        try {
            Class.forName("org.postgresql.Driver");
            conexion = DriverManager.getConnection(host, user, password);
            return conexion;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PostgresSQL.class.getName()).log(Level.SEVERE, null, ex);

        }
        return null;
    }

    public void cerrarConeccionJDBC() {

        try {
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(PostgresSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean VerificarConexionBD() throws SQLException {
        boolean valor = true;
        if (this.establecer() == null) {
            valor = false;
        }
        return valor;
    }
}
