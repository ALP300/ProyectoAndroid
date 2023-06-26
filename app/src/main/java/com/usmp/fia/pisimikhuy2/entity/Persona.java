package com.usmp.fia.pisimikhuy2.entity;

public class Persona {
    int id;
    String nom, edad, carrera, cargo;
    int idImg;


    public Persona(int id, String nom, String edad, String carrera, String cargo, int idImg) {
        this.id = id;
        this.nom = nom;
        this.edad = edad;
        this.carrera = carrera;
        this.cargo = cargo;
        this.idImg = idImg;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public int getIdImg() {
        return idImg;
    }

    public void setIdImg(int idImg) {
        this.idImg = idImg;
    }
}