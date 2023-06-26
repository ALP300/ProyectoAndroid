package com.usmp.fia.pisimikhuy2.entity;

import java.io.Serializable;

public class Premio implements Serializable {
    private int id;
    private double descuento;
    private String msj;


    public Premio(int id, double descuento,String msj) {
        this.id = id;
        this.descuento = descuento;
    }
    public Premio(){

    }

    public String getMsj() {
        return msj;
    }

    public void setMsj(String msj) {
        this.msj = msj;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getDescuento() {
        return descuento;
    }

    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }
}
