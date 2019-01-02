/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.comedor.controlador.DAO;

import com.comedor.controlador.ConexionBD.PostgresSQL;
import com.comedor.controlador.Intefaz.IClienteDao;
import com.comedor.modelo.Cliente;
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
public class ClienteDao implements IClienteDao {

    //lista de tipo cliente
    List<Cliente> clientes;
    PostgresSQL bd;
    Connection con;
    Cliente cliente = new Cliente();
    Estado estadoC;

     public ClienteDao(PostgresSQL bd) throws SQLException {
        this.bd=bd;
        //this.con = this.bd.establecer();
    }

    public Connection getCon() {
        return con;
    }

    
    @Override
    public List<Cliente> obtenerClientes() {
        try {
            con = bd.establecer();
            String sql = "SELECT \"ClienteID\", \"NroCI\", \"Nombre\", \"Apellido\", \"Direccion\", \"Telefono\", \"Email\", \"Sexo\", \"EstadoCivil\", \"celular\" FROM public.\"Cliente\" order by \"ClienteID\"";
            Statement st = con.createStatement();
            ResultSet resultado = st.executeQuery(sql);
            int estado = 0;
            while (resultado.next()) {
                cliente.setIdcliente(resultado.getInt("\"ClienteID\""));
                cliente.setNroCI(resultado.getInt("\"NroCI\""));
                cliente.setNombre(resultado.getString("\"Nombre\""));
                cliente.setApellido(resultado.getString("\"Apellido\""));
                cliente.setDireccion(resultado.getString("\"Direccion\""));
                cliente.setTelefono(resultado.getString("\"Telefono\""));
                cliente.setEmail(resultado.getString("\"Email\""));
                cliente.setEmail(resultado.getString("\"Sexo\""));
                estado = resultado.getInt("\"EstadoCivil\"");
                obtenerEstadoCivilCliente(estado);
                cliente.setCelular(resultado.getString("\"celular\""));
                clientes.add(cliente);
            }
            st.close();
            con.close();
        } catch (SQLException ex) {
            // Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
            clientes = null;
        } finally {
            try {
                bd.cerrarConeccionJDBC();
                con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return clientes;

    }

    @Override
    public Cliente obtenerCliente(int id) {
        try {
            con = bd.establecer();
            String sql = "SELECT \"ClienteID\", \"NroCI\", \"Nombre\", \"Apellido\", \"Direccion\", \"Telefono\", \"Email\", \"Sexo\", \"EstadoCivil\" , \"celular\" FROM public.\"Cliente\"";
            sql += " where \"ClienteID\"=" + id;
            Statement st = con.createStatement();
            ResultSet resultado = st.executeQuery(sql);
            int estado = 0;
            while (resultado.next()) {
                cliente.setIdcliente(resultado.getInt("\"ClienteID\""));
                cliente.setNroCI(resultado.getInt("\"NroCI\""));
                cliente.setNombre(resultado.getString("\"Nombre\""));
                cliente.setApellido(resultado.getString("\"Apellido\""));
                cliente.setDireccion(resultado.getString("\"Direccion\""));
                cliente.setTelefono(resultado.getString("\"Telefono\""));
                cliente.setEmail(resultado.getString("\"Email\""));
                cliente.setEmail(resultado.getString("\"Sexo\""));
                estado = resultado.getInt("\"EstadoCivil\"");
                estadoC = obtenerEstadoCivilCliente(estado);
                cliente.setEstadoCivil(estadoC);
                cliente.setCelular(resultado.getString("\"celular\""));
                //clientes.add(cliente);
            }
            st.close();
            con.close();
        } catch (SQLException ex) {

            ex.printStackTrace();
            cliente = null;
        } finally {
            try {
                bd.cerrarConeccionJDBC();
                con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return cliente;
    }

    @Override
    public int actualizarCliente(Cliente cliente, int id) {
        int result;
        try {
            String query = null;
            con = bd.establecer();
            query = "UPDATE \"Cliente\"";
            query = query + "   SET \"NroCI\"=?, \"Nombre\"=?, \"Apellido\"=?, \"Direccion\"=?, \n";
            query = query + "\"Telefono\"=?, \"Email\"=?, \"Sexo\"=?, \"EstadoCivil\"=?";
            query = query + " WHERE \"ClienteID\"= ?";
            PreparedStatement st = con.prepareStatement(query);
            st.setInt(1, cliente.getNroCI());
            st.setString(2, cliente.getNombre().trim());
            st.setString(3, cliente.getApellido().trim());
            st.setString(4, cliente.getDireccion().trim());
            st.setString(5, cliente.getTelefono().trim());
            st.setString(6, cliente.getEmail().trim());
            st.setString(7, Character.toString(cliente.getSexo()));
            int indice = cliente.getEstadoCivil().getPosicion();
            estadoC = obtenerEstadoCivilCliente(indice);
            cliente.setEstadoCivil(estadoC);
            st.setInt(8, cliente.getEstadoCivil().getPosicion());
            if (id == cliente.getIdcliente()) {
                st.setInt(9, id);
            } else {
                cliente.setIdcliente(id);
                st.setInt(9, cliente.getIdcliente());
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
    public int eliminarCliente(Cliente cliente) {
        int result;
        try {
            String query = null;
           con = bd.establecer();
            query = "DELETE FROM \"Cliente\"";
            query = query + "WHERE \"ClienteID\" = ?";
            PreparedStatement std = con.prepareStatement(query);
            std.setInt(1, cliente.getIdcliente());
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

    private Estado obtenerEstadoCivilCliente(int estado) {
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

    @Override
    public int insertarCliente(Cliente cliente) {
        int result;
        try {
            String query = null;
            //con = bd.establecer();
            query = "INSERT INTO public.\"Cliente\"";
            query = query + " (\"NroCI\", \"Nombre\", \"Apellido\", \"Direccion\", \"Telefono\", \"Email\", \"Sexo\", \"EstadoCivil\", \"celular\")";
            query = query + " VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement st = con.prepareStatement(query);
            st.setInt(1, cliente.getNroCI());
            st.setString(2, cliente.getNombre().trim());
            st.setString(3, cliente.getApellido().trim());
            st.setString(4, cliente.getDireccion().trim());
            st.setString(5, cliente.getTelefono().trim());
            st.setString(6, cliente.getEmail().trim());
            st.setString(7, Character.toString(cliente.getSexo()));
            int indice = cliente.getEstadoCivil().getPosicion();
            st.setInt(8, indice);
            st.setString(9, cliente.getCelular());

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

}
