package com.example.andree.minisega;

public class MateriasAlumnos {
    private int id;
    private int idAlumno;
    private int idMateria;
    private Integer falta;

    public MateriasAlumnos(int id, int idAlumno, int idMateria, Integer falta) {
        this.id = id;
        this.idAlumno = idAlumno;
        this.idMateria = idMateria;
        this.falta = falta;
    }

    @Override
    public String toString() {
        return "MateriasAlumnos{" +
                "id=" + id +
                ", idAlumno=" + idAlumno +
                ", idMateria=" + idMateria +
                ", falta=" + falta +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(int idAlumno) {
        this.idAlumno = idAlumno;
    }

    public int getIdMateria() {
        return idMateria;
    }

    public void setIdMateria(int idMateria) {
        this.idMateria = idMateria;
    }

    public Integer getFalta() {
        return falta;
    }

    public void setFalta(Integer falta) {
        this.falta = falta;
    }
}
