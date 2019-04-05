package com.example.andree.minisega;

public class Materia {
    private int creditos;
    private String clave;
    private String nombre;
    private int id;

    public Materia(Integer creditos, String clave, String nombre, int id) {
        this.creditos = creditos;
        this.clave = clave;
        this.nombre = nombre;
        this.id = id;
    }

    @Override
    public String toString() {
        return "Materia:" + " " + nombre + " " +
                "Clave:" + " " + clave;
    }

    public Integer getCreditos() {
        return creditos;
    }

    public void setCreditos(Integer creditos) {
        this.creditos = creditos;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
