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
public class Cliente {

    private int idcliente;
    private int nroCI;
    private String Nombre;
    private String Apellido;
    private String Direccion;
    private String Telefono;
    private String Email;
    private String Sexo;
    private Estado EstadoCivil;
    private String celular;

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public Cliente() {
    }

    public Cliente(int idcliente, int nroCI, String Nombre, String Apellido, String Direccion, String Telefono, String Email, String Sexo, Estado EstadoCivil, String celular) {
        this.idcliente = idcliente;
        this.nroCI = nroCI;
        this.Nombre = Nombre;
        this.Apellido = Apellido;
        this.Direccion = Direccion;
        this.Telefono = Telefono;
        this.Email = Email;
        this.Sexo = Sexo;
        this.EstadoCivil = EstadoCivil;
        this.celular = celular;
    }

    public Cliente(int nroCI, String Nombre, String Apellido, String Direccion, String Telefono, String Email, String Sexo, Estado EstadoCivil, String celular) {
        this.nroCI = nroCI;
        this.Nombre = Nombre;
        this.Apellido = Apellido;
        this.Direccion = Direccion;
        this.Telefono = Telefono;
        this.Email = Email;
        this.Sexo = Sexo;
        this.EstadoCivil = EstadoCivil;
        this.celular = celular;
    }

    public int getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(int idcliente) {
        this.idcliente = idcliente;
    }

    public int getNroCI() {
        return nroCI;
    }

    public void setNroCI(int nroCI) {
        this.nroCI = nroCI;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public void setApellido(String Apellido) {
        this.Apellido = Apellido;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String Direccion) {
        this.Direccion = Direccion;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String Telefono) {
        this.Telefono = Telefono;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public char getSexo() {
        Sexo.toUpperCase().trim();
        return Sexo.charAt(0);
    }

    public void setSexo(String Sexo) {
        this.Sexo = Sexo;
    }

    public Estado getEstadoCivil() {
        return EstadoCivil;
    }

    public void setEstadoCivil(Estado EstadoCivil) {
        this.EstadoCivil = EstadoCivil;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + this.idcliente;
        hash = 71 * hash + this.nroCI;
        hash = 71 * hash + Objects.hashCode(this.Nombre);
        hash = 71 * hash + Objects.hashCode(this.Apellido);
        hash = 71 * hash + Objects.hashCode(this.Direccion);
        hash = 71 * hash + Objects.hashCode(this.Telefono);
        hash = 71 * hash + Objects.hashCode(this.Email);
        hash = 71 * hash + Objects.hashCode(this.Sexo);
        hash = 71 * hash + Objects.hashCode(this.EstadoCivil);
        hash = 71 * hash + Objects.hashCode(this.celular);
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
        final Cliente other = (Cliente) obj;
        if (this.idcliente != other.idcliente) {
            return false;
        }
        if (this.nroCI != other.nroCI) {
            return false;
        }
        if (!Objects.equals(this.Nombre, other.Nombre)) {
            return false;
        }
        if (!Objects.equals(this.Apellido, other.Apellido)) {
            return false;
        }
        if (!Objects.equals(this.Direccion, other.Direccion)) {
            return false;
        }
        if (!Objects.equals(this.Telefono, other.Telefono)) {
            return false;
        }
        if (!Objects.equals(this.Email, other.Email)) {
            return false;
        }
        if (!Objects.equals(this.Sexo, other.Sexo)) {
            return false;
        }
        if (!Objects.equals(this.celular, other.celular)) {
            return false;
        }
        if (this.EstadoCivil != other.EstadoCivil) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        String cadena = "";
        if (this.getSexo() == 'F') {
            cadena = "Cliente con nro C.I. es: " + this.getNroCI() + ", su nombre es: " + this.getNombre() + ", su apellido es: " + this.getApellido() + ", con dirección en: " + this.getDireccion() + ",\nel cuál su teléfono es: " + this.getTelefono() + ", su e-mail es: " + this.getEmail() + ", con sexo (" + this.getSexo() + ")emenino, del cuál su estado Civil es: " + this.getEstadoCivil().getEstado() + ", con celular igual a: " + celular;
        } else if (this.getSexo() == 'M') {
            cadena = "Cliente con nro C.I. es: " + this.getNroCI() + ", su nombre es: " + this.getNombre() + ", su apellido es: " + this.getApellido() + ", con dirección en: " + this.getDireccion() + ", \nel cuál su teléfono es: " + this.getTelefono() + ", su e-mail es: " + this.getEmail() + ", con sexo (" + this.getSexo() + ")asculino, del cuál su estado Civil es: " + this.getEstadoCivil().getEstado() + ", con celular igual a: " + celular;

        }
        return cadena;
    }

}
