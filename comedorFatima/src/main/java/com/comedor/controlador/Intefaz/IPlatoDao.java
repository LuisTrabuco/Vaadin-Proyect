/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.comedor.controlador.Intefaz;

import com.comedor.modelo.Plato;
import java.util.List;

/**
 *
 * @author Melisa Ruiz
 */

public interface IPlatoDao {
     public List<Plato> obtenerPlatos();

    public Plato obtenerPlato(int id);

    public int actualizarPlato(Plato plato, int id);

    public int eliminarPlato(Plato plato);
    
     public int insertarPlato(Plato plato);
}
