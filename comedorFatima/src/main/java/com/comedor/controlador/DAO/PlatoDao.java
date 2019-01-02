/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.comedor.controlador.DAO;

import com.comedor.controlador.ConexionBD.PostgresSQL;
import com.comedor.controlador.Intefaz.IPlatoDao;
import com.comedor.modelo.Cocinero;
import com.comedor.modelo.Plato;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Rafael Bobadilla
 */
public class PlatoDao implements IPlatoDao {

    //lista de tipo cliente
    List<Plato> platos;
    PostgresSQL bd = new PostgresSQL();
    Connection conn = null;
    Plato plato = new Plato();
    Cocinero cocinero;
    CocineroDao cocineroBD = new CocineroDao();

    @Override
    public List<Plato> obtenerPlatos() {
        int idcocinero = 0;
        try {
            conn = bd.establecer();
            String sql = "SELECT \"PlatoID\", \"Descripcion\", \"IDCocinero\" FROM public.\"Plato\" ORDER BY \"PlatoID\"";
            Statement st = conn.createStatement();
            ResultSet resultado = st.executeQuery(sql);
            while (resultado.next()) {
                plato.setIdplato(resultado.getInt("\"PlatoID\""));
                plato.setDescripcion(resultado.getString("\"Descripcion\""));
                plato.setIdcocinero(resultado.getInt("\"IDCocinero\""));
                idcocinero = plato.getIdcocinero();
                agregarCocinero(idcocinero);
                platos.add(plato);
            }
            st.close();
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            platos = null;
        } finally {
            try {
                bd.cerrarConeccionJDBC();
                conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return platos;
    }

    @Override
    public Plato obtenerPlato(int id) {
        int idcocinero = 0;
        try {
            conn = bd.establecer();
            String sql = "SELECT \"PlatoID\", \"Descripcion\", \"IDCocinero\" FROM public.\"Plato\" ORDER BY \"PlatoID\"";
            sql += " where \"PlatoID\"=" + id;
            Statement st = conn.createStatement();
            ResultSet resultado = st.executeQuery(sql);
            while (resultado.next()) {
                plato.setIdplato(resultado.getInt("\"PlatoID\""));
                plato.setDescripcion(resultado.getString("\"Descripcion\""));
                plato.setIdcocinero(resultado.getInt("\"IDCocinero\""));
                idcocinero = plato.getIdcocinero();
                buscarCocinero(idcocinero);
            }
            st.close();
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            plato = null;
        } finally {
            try {
                bd.cerrarConeccionJDBC();
                conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return plato;
    }

    @Override
    public int actualizarPlato(Plato plato, int id) {
        int result;
        try {
            String query = null;
            conn = bd.establecer();
            query = "UPDATE \"Plato\"";
            query = query + "   SET  \"Descripcion\"=?,\"IDCocinero\"=?";
            query = query + " WHERE \"PlatoID\"= ?";
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, plato.getDescripcion().trim());
            st.setInt(2, plato.getIdcocinero());
            if (id == plato.getIdplato()) {
                st.setInt(9, id);
            } else {
                plato.setIdplato(id);
                st.setInt(9, plato.getIdplato());
            }
            result = st.executeUpdate();
        } catch (SQLException ex) {
            // Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
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

    @Override
    public int eliminarPlato(Plato plato) {
        int result;
        String query = null;
        try {
            conn = bd.establecer();
            query = "DELETE FROM \"Plato\"";
            query = query + "WHERE \"PlatoID\" = ?";
            PreparedStatement std = conn.prepareStatement(query);
            std.setInt(1, plato.getIdplato());
            result = std.executeUpdate();
        } catch (SQLException ex) {
            // Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
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

    @Override
    public int insertarPlato(Plato plato) {
         int result;
        try {
            String query = null;
            conn = bd.establecer();
            query = "INSERT INTO public.\"Plato\"(\"Descripcion\", \"IDCocinero\") VALUES (?, ?)";
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, plato.getDescripcion().trim());
            st.setInt(2, plato.getIdcocinero());
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

    private void agregarCocinero(int idcocinero) {
        List<Cocinero> listaCocinero = new ArrayList<Cocinero>();
        cocinero = cocineroBD.obtenerCocinero(idcocinero);
        listaCocinero.add(cocinero);
    }

    private void buscarCocinero(int idcocinero) {
        cocinero = cocineroBD.obtenerCocinero(idcocinero);
    }
}
