/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.comedor.controlador.Intefaz;

import com.comedor.modelo.Cajero;
import java.util.List;

/**
 *
 * @author Melisa Ruiz
 */

public interface ICajeroDao {
    
     public List<Cajero> obtenerCamarero();

    public Cajero obtenerCamarero(int id);

    public int actualizarCamarero(Cajero cajero, int id);

    public int eliminarCamarero(Cajero cajero);

    public int insertarCamarero(Cajero cajero);
}
