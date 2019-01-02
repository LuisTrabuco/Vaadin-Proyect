/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.comedor.controlador.DAO;

import com.comedor.controlador.ConexionBD.PostgresSQL;
import com.comedor.controlador.Intefaz.ICocineroDao;
import com.comedor.modelo.Cocinero;
import com.comedor.modelo.Estado;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;


/**
 *
 * @author Rafael Bobadilla
 */

public class CocineroDao implements ICocineroDao{
    
    //lista de tipo cliente
    List<Cocinero> cocineros;
    PostgresSQL bd = new PostgresSQL();
    Connection conn = null;
    Cocinero cocinero = new Cocinero();
    Estado estadoC;


    @Override
    public List<Cocinero> obtenerCocineros() {
        try {
            conn = bd.establecer();
            String sql = "SELECT \"CocineroID\", \"NroCI\", \"Nombre\", \"Apellido\", \"Direccion\", \"Telefono\", \"Email\", \"Sexo\", \"EstadoCivil\" FROM public.\"Cocinero\" order by \"ClienteID\"";
            Statement st = conn.createStatement();
            ResultSet resultado = st.executeQuery(sql);
            int estado = 0;
            while (resultado.next()) {
                cocinero.setIdcocinero(resultado.getInt("\"CocineroID\""));
                cocinero.setNroCI(resultado.getInt("\"NroCI\""));
                cocinero.setNombre(resultado.getString("\"Nombre\""));
                cocinero.setApellido(resultado.getString("\"Apellido\""));
                cocinero.setDireccion(resultado.getString("\"Direccion\""));
                cocinero.setTelefono(resultado.getString("\"Telefono\""));
                cocinero.setEmail(resultado.getString("\"Email\""));
                cocinero.setEmail(resultado.getString("\"Sexo\""));
                estado = resultado.getInt("\"EstadoCivil\"");
                cocinero.setEstadoCivil(obtenerEstadoCivilCocinero(estado));
                cocineros.add(cocinero);
            }
            st.close();
            conn.close();
        } catch (SQLException ex) {
            // Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
            cocineros = null;
        } finally {
            try {
                bd.cerrarConeccionJDBC();
                conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return cocineros;
    }

    @Override
    public Cocinero obtenerCocinero(int id) {
   try {
            conn = bd.establecer();
            String sql = "SELECT \"CocineroID\", \"NroCI\", \"Nombre\", \"Apellido\", \"Direccion\", \"Telefono\", \"Email\", \"Sexo\", \"EstadoCivil\" FROM public.\"Cocinero\" order by \"ClienteID\"";
            sql += " where \"CocineroID\"=" + id;
            Statement st = conn.createStatement();
            ResultSet resultado = st.executeQuery(sql);
            int estado = 0;
            while (resultado.next()) {
                cocinero.setIdcocinero(resultado.getInt("\"CocineroID\""));
                cocinero.setNroCI(resultado.getInt("\"NroCI\""));
                cocinero.setNombre(resultado.getString("\"Nombre\""));
                cocinero.setApellido(resultado.getString("\"Apellido\""));
                cocinero.setDireccion(resultado.getString("\"Direccion\""));
                cocinero.setTelefono(resultado.getString("\"Telefono\""));
                cocinero.setEmail(resultado.getString("\"Email\""));
                cocinero.setEmail(resultado.getString("\"Sexo\""));
                estado = resultado.getInt("\"EstadoCivil\"");
                estadoC = obtenerEstadoCivilCocinero(estado);
                cocinero.setEstadoCivil(estadoC);
                cocineros.add(cocinero);
            }
            st.close();
            conn.close();
        } catch (SQLException ex) {
            // Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
            cocinero = null;
        } finally {
            try {
                bd.cerrarConeccionJDBC();
                conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return cocinero;
    }

    @Override
    public int actualizarCocinero(Cocinero cocinero, int id) {
         int result;
        try {
            String query = null;
            conn = bd.establecer();
            query = "UPDATE \"Cocinero\"";
            query = query + "   SET \"NroCI\"=?, \"Nombre\"=?, \"Apellido\"=?, \"Direccion\"=?, \n";
            query = query + "\"Telefono\"=?, \"Email\"=?, \"Sexo\"=?, \"EstadoCivil\"=?";
            query = query + " WHERE \"CocineroID\"= ?";
            PreparedStatement st = conn.prepareStatement(query);
            st.setInt(1, cocinero.getNroCI());
            st.setString(2, cocinero.getNombre().trim());
            st.setString(3, cocinero.getApellido().trim());
            st.setString(4, cocinero.getDireccion().trim());
            st.setString(5, cocinero.getTelefono().trim());
            st.setString(6, cocinero.getEmail().trim());
            st.setString(7, Character.toString(cocinero.getSexo()));
            int indice = cocinero.getEstadoCivil().getPosicion();
            estadoC = obtenerEstadoCivilCocinero(indice);
            cocinero.setEstadoCivil(estadoC);
            st.setInt(8, cocinero.getEstadoCivil().getPosicion());
            if (id == cocinero.getIdcocinero()) {
                st.setInt(9, id);
            } else {
                cocinero.setIdcocinero(id);
                st.setInt(9, cocinero.getIdcocinero());
            }

            result = st.executeUpdate();
            return result;
        } catch (SQLException ex) {

            ex.printStackTrace();
            result = 0;
        } finally {
            try {
                bd.cerrarConeccionJDBC();
                conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }

    @Override
    public int eliminarCocinero(Cocinero cocinero) {
         int result;
        try {
            String query = null;
            conn = bd.establecer();
            query = "DELETE FROM \"Cocinero\"";
            query = query + "WHERE \"CocineroID\" = ?";
            PreparedStatement std = conn.prepareStatement(query);
            std.setInt(1, cocinero.getIdcocinero());
            result = std.executeUpdate();
        } catch (SQLException ex) {
            //ex.printStackTrace();
            result = 0;
         } finally {
            try {
                bd.cerrarConeccionJDBC();
                conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }

    @Override
    public int insertarCocinero(Cocinero cocinero) {
          int result;
          String query = null;
        try {
            
            conn = bd.establecer();
            query = "INSERT INTO public.\"Cocinero\"";
            query = query + " (\"NroCI\", \"Nombre\", \"Apellido\", \"Direccion\", \"Telefono\", \"Email\", \"Sexo\", \"EstadoCivil\")";
            query = query + " VALUES ( ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement st = conn.prepareStatement(query);
            st.setInt(1, cocinero.getNroCI());
            st.setString(2, cocinero.getNombre().trim());
            st.setString(3, cocinero.getApellido().trim());
            st.setString(4, cocinero.getDireccion().trim());
            st.setString(5, cocinero.getTelefono().trim());
            st.setString(6, cocinero.getEmail().trim());
            st.setString(7, Character.toString(cocinero.getSexo()));
            int indice = cocinero.getEstadoCivil().getPosicion();
            st.setInt(8, indice);
            result = st.executeUpdate();
            return result;
        } catch (SQLException ex) {
            //ex.printStackTrace();
            result = 0;
        } finally {
            try {
                bd.cerrarConeccionJDBC();
                conn.close();
            } catch (SQLException ex) {
               // ex.printStackTrace();
            }
        }
        return result;
    }
    
     private Estado obtenerEstadoCivilCocinero(int estado) {
        Estado estadoCivil;
        switch (estado) {
            case 1:
                estadoCivil = Estado.getSOLTERO();
                break;
            case 2:
                estadoCivil = Estado.getSOLTERA();
                break;
            case 3:
                estadoCivil = Estado.getCASADO();
                break;
            case 4:
                estadoCivil = Estado.getCASADA();
                break;
            case 5:
                estadoCivil = Estado.getDIVORCIADO();
                break;
            case 6:
                estadoCivil = Estado.getDIVORCIADA();
                break;
            case 7:
                estadoCivil = Estado.getVIUDO();
                break;
            case 8:
                estadoCivil = Estado.getVIUDA();
                break;
            default:
                estadoCivil = null;
                break;
        }

        return estadoCivil;

    }
}
