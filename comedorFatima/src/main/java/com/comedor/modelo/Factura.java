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

public class Factura {

    private int idFactura;
    private int codCliente;
    private int codCamarero;
    private int codCajero;
    private int codMesa;
    private String fecha;

    public Factura() {
    }

    public Factura(int idFactura, int cliente, int camarero,int cajero, int mesa, String fecha) {
        this.idFactura = idFactura;
        this.codCliente = cliente;
        this.codCamarero = camarero;
        this.codCajero=cajero;
        this.codMesa = mesa;
        this.fecha = fecha;
    }

    public int getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(int idFactura) {
        this.idFactura = idFactura;
    }

    public int getCodCliente() {
        return codCliente;
    }

    public void setCodCliente(int cliente) {
        this.codCliente = cliente;
    }

    public int getCodCamarero() {
        return codCamarero;
    }

    public void setCodCamarero(int camarero) {
        this.codCamarero = camarero;
    }

    public int getCodMesa() {
        return codMesa;
    }

    public void setCodMesa(int mesa) {
        this.codMesa = mesa;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    } 

    public int getCodCajero() {
        return codCajero;
    }

    public void setCodCajero(int codCajero) {
        this.codCajero = codCajero;
    }
    
    
}
