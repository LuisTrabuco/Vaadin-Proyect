/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.comedor.controlador.Intefaz;

import com.comedor.modelo.Cocinero;
import java.util.List;

/**
 *
 * @author Melisa Ruiz
 */
public interface ICocineroDao {

    public List<Cocinero> obtenerCocineros();

    public Cocinero obtenerCocinero(int id);

    public int actualizarCocinero(Cocinero cocinero, int id);

    public int eliminarCocinero(Cocinero cocinero);
    
     public int insertarCocinero(Cocinero cocinero);
}
