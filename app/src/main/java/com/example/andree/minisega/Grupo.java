package com.example.andree.minisega;

import java.util.List;

public class Grupo {
    private String clave;
    private List<Materia> materiasList;
    private int id;

    public Grupo(String clave, List<Materia> materiasList, int _id) {
        this.clave = clave;
        this.materiasList = materiasList;
        this.id = _id;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public List<Materia> getMateriasList() {
        return materiasList;
    }

    public void setMateriasList(List<Materia> materiasList) {
        this.materiasList = materiasList;
    }
}
