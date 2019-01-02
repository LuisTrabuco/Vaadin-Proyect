/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.comedor.modelo;

import java.util.Objects;

/**
 *
 * @author Ivan Florentín
 */
public class Plato {

    private int idplato;
    private String descripcion;
    private int idcocinero;

    public Plato() {
    }

    public Plato(int idplato, String descripcion, int idcocinero) {
        this.idplato = idplato;
        this.descripcion = descripcion;
        this.idcocinero = idcocinero;
    }

    public int getIdplato() {
        return idplato;
    }

    public void setIdplato(int idplato) {
        this.idplato = idplato;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getIdcocinero() {
        return idcocinero;
    }

    public void setIdcocinero(int idcocinero) {
        this.idcocinero = idcocinero;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + this.idplato;
        hash = 29 * hash + Objects.hashCode(this.descripcion);
        hash = 29 * hash + this.idcocinero;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Plato other = (Plato) obj;
        if (this.idplato != other.idplato) {
            return false;
        }
        if (this.idcocinero != other.idcocinero) {
            return false;
        }
        if (!Objects.equals(this.descripcion, other.descripcion)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Plato con código=" + this.getIdplato() + ", descripcion=" + this.getDescripcion() + ", cocinero nro=" + this.getIdcocinero();
    }

    
    
}
