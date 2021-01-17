package com.atlanticssoft.mascotasthree.Models;

public class Mascota {

    private int idmascota;
    private int foto;
    private String nombre;
    private int contador;

    // Contructor vacio
    public Mascota() {

    }

    // No es necesario colocar el idmascota en el contructor ya que se generará automáticamente, sin embargo lo colocaré
    // Contructor: Se utiliza para crear tablas de clases virtuales y poder así
    // desarrollar el polimorfismo, una de las herramientas de la programación orientada a objetos. ...
    public Mascota(int idmascota, int foto, String nombre, int contador) {
        this.idmascota = idmascota;
        this.nombre = nombre;
        this.foto = foto;
        this.contador = contador;
    }

    public int getIdmascota() {
        return idmascota;
    }

    public void setIdmascota(int idmascota) {
        this.idmascota = idmascota;
    }

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getContador() {
        return contador;
    }

    public void setContador(int contador) {
        this.contador = contador;
    }



}
