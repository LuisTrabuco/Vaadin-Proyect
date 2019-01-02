/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.comedor.modelo;

/**
 *
 * @author Ivan Florentín
 */
public class Mesa {
    private int idMesa;
    private int cantComensalMax;
    private String ubicacion;

    public Mesa() {
    }
  
    public Mesa(int idMesa, int cantComensalMax, String ubicacion) {
        this.idMesa = idMesa;
        this.cantComensalMax = cantComensalMax;
        this.ubicacion = ubicacion;
    }

    public int getIdMesa() {
        return idMesa;
    }

    public void setIdMesa(int idMesa) {
        this.idMesa = idMesa;
    }

    public int getCantComensalMax() {
        return cantComensalMax;
    }

    public void setCantComensalMax(int cantComensalMax) {
        this.cantComensalMax = cantComensalMax;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }
    
    
}
