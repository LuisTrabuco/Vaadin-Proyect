/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.comedor.controlador.Intefaz;

import com.comedor.modelo.Camarero;
import java.util.List;

/**
 *
 * @author Melisa Ruiz
 */
public interface ICamareroDao {

    public List<Camarero> obtenerCamarero();

    public Camarero obtenerCamarero(int id);

    public int actualizarCamarero(Camarero camarero, int id);

    public int eliminarCamarero(Camarero camarero);

    public int insertarCamarero(Camarero camarero);

}
