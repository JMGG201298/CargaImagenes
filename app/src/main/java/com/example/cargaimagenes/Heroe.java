package com.example.cargaimagenes;

public class Heroe {
    private String foto;
    private String nombre;

    public Heroe(String foto, String nombre) {
        this.foto = foto;
        this.nombre = nombre;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
