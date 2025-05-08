package com.problema.ciudades;

import java.util.Objects;



public class Ciudad {
    private String nombre;
    private String pais;
    private int habitantes;

    public Ciudad(String nombre, String pais, int habitantes) {
        this.nombre = nombre;
        this.pais = pais;
        this.habitantes = habitantes;
    }

    public String getNombre() {
        return nombre;
    }

    public String getPais() {
        return pais;
    }

    public int getHabitantes() {
        return habitantes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ciudad)) return false;
        Ciudad ciudad = (Ciudad) o;
        return nombre.equalsIgnoreCase(ciudad.nombre)
                && pais.equalsIgnoreCase(ciudad.pais);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre.toLowerCase(), pais.toLowerCase());
    }

    @Override
    public String toString() {
        return nombre + " (" + pais + ") - " + habitantes + " habitantes";
    }
}
