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
public class Camarero {
    
    private int idcamarero;
    private Integer nroCI;
    private String Nombre;
    private String Apellido;
    private String Direccion;
    private String Telefono;
    private String Email;
    private String Sexo;
    private Estado EstadoCivil;
    
     public Camarero() {
    }

    public Camarero(int idcamarero, int nroCI, String Nombre, String Apellido, String Direccion, String Telefono, String Email, String Sexo, Estado EstadoCivil,String celular) {
        this.idcamarero = idcamarero;
        this.nroCI = nroCI;
        this.Nombre = Nombre;
        this.Apellido = Apellido;
        this.Direccion = Direccion;
        this.Telefono = Telefono;
        this.Email = Email;
        this.Sexo = Sexo;
        this.EstadoCivil = EstadoCivil;
       
    }

    public Camarero(Integer nroCI, String Nombre, String Apellido, String Direccion, String Telefono, String Email, String Sexo, Estado EstadoCivil) {
        this.nroCI = nroCI;
        this.Nombre = Nombre;
        this.Apellido = Apellido;
        this.Direccion = Direccion;
        this.Telefono = Telefono;
        this.Email = Email;
        this.Sexo = Sexo;
        this.EstadoCivil = EstadoCivil;
      
    }

    public int getIdcamarero() {
        return idcamarero;
    }

    public void setIdcamarero(int idcamarero) {
        this.idcamarero = idcamarero;
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
        hash = 97 * hash + this.idcamarero;
        hash = 97 * hash + this.nroCI;
        hash = 97 * hash + Objects.hashCode(this.Nombre);
        hash = 97 * hash + Objects.hashCode(this.Apellido);
        hash = 97 * hash + Objects.hashCode(this.Direccion);
        hash = 97 * hash + Objects.hashCode(this.Telefono);
        hash = 97 * hash + Objects.hashCode(this.Email);
        hash = 97 * hash + Objects.hashCode(this.Sexo);
        hash = 97 * hash + Objects.hashCode(this.EstadoCivil);
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
        final Camarero other = (Camarero) obj;
        if (this.idcamarero != other.idcamarero) {
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
        if (this.EstadoCivil != other.EstadoCivil) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Camarero con Cod.=" + this.getIdcamarero() + ", nroCI=" + this.getNroCI() + ", Nombre=" + this.getNombre() + ", Apellido=" + this.getApellido() + ", Direccion=" + this.getDireccion() + ", Telefono=" + this.getTelefono() + ", Email=" + this.getEmail() + ", Sexo=" + this.getSexo() + ", EstadoCivil=" + this.getEstadoCivil().getEstado();
    }
     
     
}
