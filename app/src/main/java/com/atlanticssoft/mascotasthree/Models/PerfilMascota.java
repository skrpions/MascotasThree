package com.atlanticssoft.mascotasthree.Models;

public class PerfilMascota {

    private  int foto;
    private int contador;

    public PerfilMascota(int foto, int contador) {
        this.foto = foto;
        this.contador = contador;
    }

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }

    public int getContador() {
        return contador;
    }

    public void setContador(int contador) {
        this.contador = contador;
    }
}
