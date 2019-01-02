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
public enum Estado {
    SOLTERO("Soltero", 1),
    SOLTERA("Soltera", 2),
    CASADO("Casado", 3),
    CASADA("Casada", 4),
    DIVORCIADO("Divorciado", 5),
    DIVORCIADA("Divorciada", 6),
    VIUDO("Viudo", 7),
    VIUDA("Viuda", 8);

    //Campos tipo constante   
    private final String estado; //Nombre 
    private final int posicion; //Posicion

    private Estado(String estado, int posicion) {
        this.estado = estado;
        this.posicion = posicion;
    }

    public String getEstado() {
        return estado;
    }

    public int getPosicion() {
        return posicion;
    }

    public static Estado getSOLTERO() {
        return SOLTERO;
    }

    public static Estado getCASADO() {
        return CASADO;
    }

    public static Estado getDIVORCIADO() {
        return DIVORCIADO;
    }

    public static Estado getVIUDO() {
        return VIUDO;
    }
    
     public static Estado getSOLTERA() {
        return SOLTERA;
    }

    public static Estado getCASADA() {
        return CASADA;
    }

    public static Estado getDIVORCIADA() {
        return DIVORCIADA;
    }

    public static Estado getVIUDA() {
        return VIUDA;
    }

}
