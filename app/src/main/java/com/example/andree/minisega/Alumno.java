package com.example.andree.minisega;

public class Alumno {
    private String nombre;
    private String aPaterno;
    private String carrera;
    private int id;


    public Alumno(String nombre, String aPaterno, String carrera, int id) {
        this.nombre = nombre;
        this.aPaterno = aPaterno;
        this.carrera = carrera;
        this.id = id;
    }

    @Override
    public String toString() {
        return "Alumno:" + nombre.toUpperCase() + ' ' + aPaterno.toUpperCase();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getaPaterno() {
        return aPaterno;
    }

    public void setaPaterno(String aPaterno) {
        this.aPaterno = aPaterno;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
