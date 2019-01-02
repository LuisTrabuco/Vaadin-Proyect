/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.comedor.controlador.DAO;

import com.comedor.controlador.ConexionBD.PostgresSQL;
import com.comedor.controlador.Intefaz.ICajeroDao;
import com.comedor.modelo.Cajero;
import com.comedor.modelo.Estado;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 *
 * @author alumno-201
 */
public class CajeroDao implements ICajeroDao{
    
     //lista de tipo cliente
    List<Cajero> cajeros;
    PostgresSQL bd;
    Connection con;
    Cajero cajero = new Cajero();
    Estado estadoC;

    public CajeroDao(PostgresSQL bd) throws SQLException {
        this.bd=bd;
    }

    public Connection getCon() {
        return con;
    }
    
    
    @Override
    public List<Cajero> obtenerCamarero() {
        try {
            con = bd.establecer();
            String sql = "SELECT \"cajeroID\", \"NroCI\", \"Nombre\", \"Apellido\", \"Direccion\", \"Telefono\", \"Email\", \"Sexo\", \"EstadoCivil\" FROM public.\"cajero\" order by \"cajeroID\"";
            Statement st = con.createStatement();
            ResultSet resultado = st.executeQuery(sql);
            int estado = 0;
            while (resultado.next()) {
                cajero.setIdcajero(resultado.getInt("\"CocineroID\""));
                cajero.setNroCI(resultado.getInt("\"NroCI\""));
                cajero.setNombre(resultado.getString("\"Nombre\""));
                cajero.setApellido(resultado.getString("\"Apellido\""));
                cajero.setDireccion(resultado.getString("\"Direccion\""));
                cajero.setTelefono(resultado.getString("\"Telefono\""));
                cajero.setEmail(resultado.getString("\"Email\""));
                cajero.setEmail(resultado.getString("\"Sexo\""));
                estado = resultado.getInt("\"EstadoCivil\"");
                cajero.setEstadoCivil(obtenerEstadoCivilCajero(estado));
                cajeros.add(cajero);
            }
            st.close();
            con.close();
        } catch (SQLException ex) {
            // Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
            cajeros = null;
        } finally {
            try {
                bd.cerrarConeccionJDBC();
                con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return cajeros;

    }

    @Override
    public Cajero obtenerCamarero(int id) {
        try {
            con = bd.establecer();
            String sql = "SELECT \"cajeroID\", \"NroCI\", \"Nombre\", \"Apellido\", \"Direccion\", \"Telefono\", \"Email\", \"Sexo\", \"EstadoCivil\" FROM public.\"cajero\"";
            sql="where \"cajeroID\"="+id;
            Statement st = con.createStatement();
            ResultSet resultado = st.executeQuery(sql);
            int estado = 0;
            while (resultado.next()) {
                cajero.setIdcajero(resultado.getInt("\"CocineroID\""));
                cajero.setNroCI(resultado.getInt("\"NroCI\""));
                cajero.setNombre(resultado.getString("\"Nombre\""));
                cajero.setApellido(resultado.getString("\"Apellido\""));
                cajero.setDireccion(resultado.getString("\"Direccion\""));
                cajero.setTelefono(resultado.getString("\"Telefono\""));
                cajero.setEmail(resultado.getString("\"Email\""));
                cajero.setEmail(resultado.getString("\"Sexo\""));
                estado = resultado.getInt("\"EstadoCivil\"");
                cajero.setEstadoCivil(obtenerEstadoCivilCajero(estado));
            }
            st.close();
            con.close();
        } catch (SQLException ex) {
            // Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
            cajero= null;
        } finally {
            try {
                bd.cerrarConeccionJDBC();
                con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return cajero;

    }

    @Override
    public int actualizarCamarero(Cajero cajero, int id) {
         int result;
        try {
            String query = null;
            con = bd.establecer();
            query = "UPDATE \"Cliente\"";
            query = query + "   SET \"NroCI\"=?, \"Nombre\"=?, \"Apellido\"=?, \"Direccion\"=?, \n";
            query = query + "\"Telefono\"=?, \"Email\"=?, \"Sexo\"=?, \"EstadoCivil\"=?";
            query = query + " WHERE \"ClienteID\"= ?";
            PreparedStatement st = con.prepareStatement(query);
            st.setInt(1, cajero.getNroCI());
            st.setString(2, cajero.getNombre().trim());
            st.setString(3, cajero.getApellido().trim());
            st.setString(4, cajero.getDireccion().trim());
            st.setString(5, cajero.getTelefono().trim());
            st.setString(6, cajero.getEmail().trim());
            st.setString(7, Character.toString(cajero.getSexo()));
            int indice = cajero.getEstadoCivil().getPosicion();
            estadoC = obtenerEstadoCivilCajero(indice);
            cajero.setEstadoCivil(estadoC);
            st.setInt(8, cajero.getEstadoCivil().getPosicion());
            if (id == cajero.getIdcajero()) {
                st.setInt(9, id);
            } else {
                cajero.setIdcajero(id);
                st.setInt(9, cajero.getIdcajero());
            }

            result = st.executeUpdate();
            return result;
        } catch (SQLException ex) {

            ex.printStackTrace();
            result = 0;
        } finally {
            try {
                bd.cerrarConeccionJDBC();
                con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }

    @Override
    public int eliminarCamarero(Cajero cajero) {
           int result;
        try {
            String query = null;
            con = bd.establecer();
            query = "DELETE FROM \"Cocinero\"";
            query = query + "WHERE \"CocineroID\" = ?";
            PreparedStatement std = con.prepareStatement(query);
            std.setInt(1, cajero.getIdcajero());
            result = std.executeUpdate();
        } catch (SQLException ex) {
            //ex.printStackTrace();
            result = 0;
         } finally {
            try {
                bd.cerrarConeccionJDBC();
                con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return result;
   }

    @Override
    public int insertarCamarero(Cajero cajero) {
         int result;
          String query = null;
        try {
            
            con = bd.establecer();
            query = "INSERT INTO public.\"Cocinero\"";
            query = query + " (\"NroCI\", \"Nombre\", \"Apellido\", \"Direccion\", \"Telefono\", \"Email\", \"Sexo\", \"EstadoCivil\")";
            query = query + " VALUES ( ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement st = con.prepareStatement(query);
            st.setInt(1, cajero.getNroCI());
            st.setString(2, cajero.getNombre().trim());
            st.setString(3, cajero.getApellido().trim());
            st.setString(4, cajero.getDireccion().trim());
            st.setString(5, cajero.getTelefono().trim());
            st.setString(6, cajero.getEmail().trim());
            st.setString(7, Character.toString(cajero.getSexo()));
            int indice = cajero.getEstadoCivil().getPosicion();
            st.setInt(8, indice);
            result = st.executeUpdate();
            return result;
        } catch (SQLException ex) {
            //ex.printStackTrace();
            result = 0;
        } finally {
            try {
                bd.cerrarConeccionJDBC();
                con.close();
            } catch (SQLException ex) {
               // ex.printStackTrace();
            }
        }
        return result;
    }
    
     private Estado obtenerEstadoCivilCajero(int estado) {
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
