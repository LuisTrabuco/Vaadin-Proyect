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
public class DetalleFactura {
     private int idFactura;
     private int idDetalleFactura;
     private int idPlato;
     private Integer cantidad;
     private Double precio;

    public DetalleFactura() {
    }

    public DetalleFactura(int idFactura, int idDetalleFactura, int idPlato, Integer cantidad, Double precio) {
        this.idFactura = idFactura;
        this.idDetalleFactura = idDetalleFactura;
        this.idPlato = idPlato;
        this.cantidad = cantidad;
        this.precio = precio;
    }

    public DetalleFactura(int idFactura, int idPlato, Integer cantidad, Double precio) {
        this.idFactura = idFactura;
        this.idPlato = idPlato;
        this.cantidad = cantidad;
        this.precio = precio;
    }

    public int getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(int idFactura) {
        this.idFactura = idFactura;
    }

    public int getIdDetalleFactura() {
        return idDetalleFactura;
    }

    public void setIdDetalleFactura(int idDetalleFactura) {
        this.idDetalleFactura = idDetalleFactura;
    }

    public int getIdPlato() {
        return idPlato;
    }

    public void setIdPlato(int idPlato) {
        this.idPlato = idPlato;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 17 * hash + this.idFactura;
        hash = 17 * hash + this.idDetalleFactura;
        hash = 17 * hash + this.idPlato;
        hash = 17 * hash + Objects.hashCode(this.cantidad);
        hash = 17 * hash + Objects.hashCode(this.precio);
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
        final DetalleFactura other = (DetalleFactura) obj;
        if (this.idFactura != other.idFactura) {
            return false;
        }
        if (this.idDetalleFactura != other.idDetalleFactura) {
            return false;
        }
        if (this.idPlato != other.idPlato) {
            return false;
        }
        if (!Objects.equals(this.cantidad, other.cantidad)) {
            return false;
        }
        if (!Objects.equals(this.precio, other.precio)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DetalleFactura con nro. Factura=" + this.getIdFactura() + ", nro. DetalleFactura=" + this.getIdDetalleFactura() + ", cod. Plato=" + this.getIdPlato() + ", cantidad=" + this.getCantidad() + ", precio=" + this.getPrecio();
    }
     
     
   
}
