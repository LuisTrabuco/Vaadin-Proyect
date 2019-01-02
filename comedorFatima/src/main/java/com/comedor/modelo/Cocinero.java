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
public class Cocinero {
    //atributos

    private int idcocinero;
    private Integer nroCI;
    private String Nombre;
    private String Apellido;
    private String Direccion;
    private String Telefono;
    private String Email;
    private String Sexo;
    private Estado EstadoCivil;

    public Cocinero() {
    }

    public Cocinero(int idcocinero, Integer nroCI, String Nombre, String Apellido, String Direccion, String Telefono, String Email, String Sexo, Estado EstadoCivil) {
        this.idcocinero = idcocinero;
        this.nroCI = nroCI;
        this.Nombre = Nombre;
        this.Apellido = Apellido;
        this.Direccion = Direccion;
        this.Telefono = Telefono;
        this.Email = Email;
        this.Sexo = Sexo;
        this.EstadoCivil = EstadoCivil;
    }

    public Cocinero(Integer nroCI, String Nombre, String Apellido, String Direccion, String Telefono, String Email, String Sexo, Estado EstadoCivil) {
        this.nroCI = nroCI;
        this.Nombre = Nombre;
        this.Apellido = Apellido;
        this.Direccion = Direccion;
        this.Telefono = Telefono;
        this.Email = Email;
        this.Sexo = Sexo;
        this.EstadoCivil = EstadoCivil;
    }
    
    

    public int getIdcocinero() {
        return idcocinero;
    }

    public void setIdcocinero(int idcocinero) {
        this.idcocinero = idcocinero;
    }

    public Integer getNroCI() {
        return nroCI;
    }

    public void setNroCI(Integer nroCI) {
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
    
    
}
