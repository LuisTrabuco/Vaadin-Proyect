/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.comedor.controlador.Intefaz;

import com.comedor.modelo.Cliente;
import java.util.List;

/**
 *
 * @author Melisa Ruiz
 */

public interface IClienteDao {
        //declaración de métodos para acceder a la base de datos
	public List<Cliente> obtenerClientes();
        
	public Cliente obtenerCliente(int id);
        
	public int actualizarCliente(Cliente cliente,int id);
        
	public int eliminarCliente(Cliente cliente);
        
        public int insertarCliente(Cliente cliente);
}
