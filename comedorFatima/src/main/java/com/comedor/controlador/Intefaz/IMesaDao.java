/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.comedor.controlador.Intefaz;

import com.comedor.modelo.Mesa;
import java.util.List;

/**
 *
 * @author Melisa Ruiz
 */
public interface IMesaDao {
    
    public List<Mesa> obtenerMesas();

    public Mesa obtenerMesa(int id);

    public int actualizarMesa(Mesa mesa, int id);

    public int eliminarMesa(Mesa mesa);
    
     public int insertarMesa(Mesa mesa);
}
