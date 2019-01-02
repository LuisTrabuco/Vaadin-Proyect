/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.comedor.controlador.DAO;

import com.comedor.controlador.ConexionBD.PostgresSQL;
import com.comedor.controlador.Intefaz.ICamareroDao;
import com.comedor.modelo.Camarero;
import java.util.List;
import com.comedor.modelo.Estado;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Rafael Bobadilla
 */
public class CamareroDao implements ICamareroDao {

    //lista de tipo cliente
    List<Camarero> camareros;
    PostgresSQL bd;
    Connection con;
    Camarero camarero = new Camarero();
    Estado estadoC;

    public CamareroDao(PostgresSQL bd) throws SQLException {
        this.bd = bd;
       
    }

    @Override
    public List<Camarero> obtenerCamarero() {
        try {
            con = bd.establecer();
            String sql = "SELECT \"CamareroID\", \"NroCI\", \"Nombre\", \"Apellido\", \"Direccion\", \"Telefono\", \"Email\", \"Sexo\", \"EstadoCivil\" FROM public.\"Camarero\" order by \"CamareroID\"";
            Statement st = con.createStatement();
            ResultSet resultado = st.executeQuery(sql);
            int estado = 0;
            while (resultado.next()) {
                camarero.setIdcamarero(resultado.getInt("\"ClienteID\""));
                camarero.setNroCI(resultado.getInt("\"NroCI\""));
                camarero.setNombre(resultado.getString("\"Nombre\""));
                camarero.setApellido(resultado.getString("\"Apellido\""));
                camarero.setDireccion(resultado.getString("\"Direccion\""));
                camarero.setTelefono(resultado.getString("\"Telefono\""));
                camarero.setEmail(resultado.getString("\"Email\""));
                camarero.setSexo(resultado.getString("\"Sexo\""));
                estado = resultado.getInt("\"EstadoCivil\"");
                estadoC = obtenerEstadoCivilCamarero(estado);
                camarero.setEstadoCivil(estadoC);
                camareros.add(camarero);
            }
            st.close();
            con.close();
        } catch (SQLException ex) {

            ex.printStackTrace();
            camareros = null;
        } finally {
            try {
                bd.cerrarConeccionJDBC();
                con.close();
            } catch (SQLException ex) {
                // ex.printStackTrace();
            }
        }
        return camareros;

    }

    @Override
    public Camarero obtenerCamarero(int id) {
        try {
            con = bd.establecer();
            String sql = "SELECT \"CamareroID\", \"NroCI\", \"Nombre\", \"Apellido\", \"Direccion\", \"Telefono\", \"Email\", \"Sexo\", \"EstadoCivil\" FROM public.\"Camarero\"";
            sql += " where \"CamareroID\"=" + id;
            Statement st = con.createStatement();
            ResultSet resultado = st.executeQuery(sql);
            int estado = 0;
            while (resultado.next()) {
                camarero.setIdcamarero(resultado.getInt("\"ClienteID\""));
                camarero.setNroCI(resultado.getInt("\"NroCI\""));
                camarero.setNombre(resultado.getString("\"Nombre\""));
                camarero.setApellido(resultado.getString("\"Apellido\""));
                camarero.setDireccion(resultado.getString("\"Direccion\""));
                camarero.setTelefono(resultado.getString("\"Telefono\""));
                camarero.setEmail(resultado.getString("\"Email\""));
                camarero.setSexo(resultado.getString("\"Sexo\""));
                estado = resultado.getInt("\"EstadoCivil\"");
                estadoC = obtenerEstadoCivilCamarero(estado);
                camarero.setEstadoCivil(estadoC);
            }
            st.close();
            con.close();
        } catch (SQLException ex) {
            // Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
            camarero = null;
        } finally {
            try {
                bd.cerrarConeccionJDBC();
                con.close();
            } catch (SQLException ex) {
                // ex.printStackTrace();
            }
        }
        return camarero;
    }

    @Override
    public int actualizarCamarero(Camarero camarero, int id) {
        int result;
        try {
            String query = "";
            con = bd.establecer();
            query = "UPDATE \"Camarero\"";
            query = query + "   SET \"NroCI\"=?, \"Nombre\"=?, \"Apellido\"=?, \"Direccion\"=?, \n";
            query = query + "\"Telefono\"=?, \"Email\"=?, \"Sexo\"=?, \"EstadoCivil\"=?";
            query = query + " WHERE \"CamareroID\"= ?";
            PreparedStatement st = con.prepareStatement(query);
            st.setInt(1, camarero.getNroCI());
            st.setString(2, camarero.getNombre().trim());
            st.setString(3, camarero.getApellido().trim());
            st.setString(4, camarero.getDireccion().trim());
            st.setString(5, camarero.getTelefono().trim());
            st.setString(6, camarero.getEmail().trim());
            st.setString(7, Character.toString(camarero.getSexo()));
            int indice = camarero.getEstadoCivil().getPosicion();
            estadoC = obtenerEstadoCivilCamarero(indice);
            camarero.setEstadoCivil(estadoC);
            st.setInt(8, camarero.getEstadoCivil().getPosicion());
            if (id == camarero.getIdcamarero()) {
                st.setInt(9, id);
            } else {
                camarero.setIdcamarero(id);
                st.setInt(9, camarero.getIdcamarero());
            }

            result = st.executeUpdate();
        } catch (SQLException ex) {

            ex.printStackTrace();
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

    @Override
    public int eliminarCamarero(Camarero camarero) {
        int result;
        try {
            String query = null;
            con = bd.establecer();
            query = "DELETE FROM \"Camarero\"";
            query = query + "WHERE \"CamareroID\" = ?";
            PreparedStatement std = con.prepareStatement(query);
            std.setInt(1, camarero.getIdcamarero());
            result = std.executeUpdate();
        } catch (SQLException ex) {
            // Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
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

    @Override
    public int insertarCamarero(Camarero camarero) {
        int result;
        String query = "";
        try {

            con = bd.establecer();
            query = "INSERT INTO public.\"Camarero\"";
            query = query + " (\"NroCI\", \"Nombre\", \"Apellido\", \"Direccion\", \"Telefono\", \"Email\", \"Sexo\", \"EstadoCivil\")";
            query = query + " VALUES ( ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement st = con.prepareStatement(query);
            st.setInt(1, camarero.getNroCI());
            st.setString(2, camarero.getNombre().trim());
            st.setString(3, camarero.getApellido().trim());
            st.setString(4, camarero.getDireccion().trim());
            st.setString(5, camarero.getTelefono().trim());
            st.setString(6, camarero.getEmail().trim());
            st.setString(7, Character.toString(camarero.getSexo()));
            int indice = camarero.getEstadoCivil().getPosicion();
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

    private Estado obtenerEstadoCivilCamarero(int estado) {
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
