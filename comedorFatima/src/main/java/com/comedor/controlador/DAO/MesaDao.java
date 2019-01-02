/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.comedor.controlador.DAO;

import com.comedor.controlador.ConexionBD.PostgresSQL;
import com.comedor.controlador.Intefaz.IMesaDao;
import com.comedor.modelo.Mesa;
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

public class MesaDao implements IMesaDao{

    //lista de tipo cliente
    List<Mesa> mesas;
    PostgresSQL bd = new PostgresSQL();
    Connection conn = null;
    Mesa mesa = new Mesa();
    
    @Override
    public List<Mesa> obtenerMesas() {
        int idcocinero = 0;
        try {
            conn = bd.establecer();
            String sql = "SELECT \"MesaID\", \"NumMaxComensal\", \"Ubicacion\" FROM public.\"Mesa\" ORDER BY \"MesaID\";";
            Statement st = conn.createStatement();
            ResultSet resultado = st.executeQuery(sql);
            while (resultado.next()) {
                mesa.setIdMesa(resultado.getInt("\"MesaID\""));
                mesa.setUbicacion(resultado.getString("\"Ubicacion\""));
                mesa.setCantComensalMax(resultado.getInt("\"NumMaxComensal\""));
                mesas.add(mesa);
            }
            st.close();
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            mesas = null;
        } finally {
            try {
                bd.cerrarConeccionJDBC();
                conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return mesas;
    }

    @Override
    public Mesa obtenerMesa(int id) {
         int idcocinero = 0;
        try {
            conn = bd.establecer();
            String sql = "SELECT \"MesaID\", \"NumMaxComensal\", \"Ubicacion\" FROM public.\"Mesa\" ORDER BY \"MesaID\";";
            sql += " where \"MesaID\"=" + id;
            Statement st = conn.createStatement();
            ResultSet resultado = st.executeQuery(sql);
            while (resultado.next()) {
                mesa.setIdMesa(resultado.getInt("\"MesaID\""));
                mesa.setUbicacion(resultado.getString("\"Ubicacion\""));
                mesa.setCantComensalMax(resultado.getInt("\"NumMaxComensal\""));
            }
            st.close();
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            mesa = null;
        } finally {
            try {
                bd.cerrarConeccionJDBC();
                conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return mesa;
    }

    @Override
    public int actualizarMesa(Mesa mesa, int id) {
         int result;
        try {
            String query = null;
            conn = bd.establecer();
            query = "UPDATE \"Plato\"";
            query = query + "   SET  \"Descripcion\"=?,\"IDCocinero\"=?";
            query = query + " WHERE \"PlatoID\"= ?";
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, mesa.getUbicacion().trim());
            st.setInt(2, mesa.getCantComensalMax());
            if (id == mesa.getIdMesa()) {
                st.setInt(9, id);
            } else {
                mesa.setIdMesa(id);
                st.setInt(9, mesa.getIdMesa());
            }
            result = st.executeUpdate();
        } catch (SQLException ex) {
          
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
    public int eliminarMesa(Mesa mesa) {
         int result;
        String query = null;
        try {
            conn = bd.establecer();
            query = "DELETE FROM \"Mesa\"";
            query = query + "WHERE \"MesaID\" = ?";
            PreparedStatement std = conn.prepareStatement(query);
            std.setInt(1, mesa.getIdMesa());
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
    public int insertarMesa(Mesa mesa) {
      //INSERT INTO public."Mesa"("NumMaxComensal", "Ubicacion") VALUES (?, ?)
      
      int result;
        try {
            String query = null;
            conn = bd.establecer();
            query = "INSERT INTO public.\"Mesa\"(\"NumMaxComensal\", \"Ubicacion\") VALUES (?, ?)";
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(2, mesa.getUbicacion().trim());
            st.setInt(1, mesa.getCantComensalMax());
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
    
}
