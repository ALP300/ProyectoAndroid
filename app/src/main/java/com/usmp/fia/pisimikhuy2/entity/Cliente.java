package com.usmp.fia.pisimikhuy2.entity;

import java.io.Serializable;
import java.util.ArrayList;

public class Cliente implements Serializable {
    private int id;
    private int idFoto;
    private int platosTotales;
    private int platosFaltantes;
    private int premio;
    private String documentoIdentidad, apellidoPaterno, apellidoMaterno, nombres, genero, fechaNacimiento, correo, contra,numeroDocumento;
    private ArrayList<Premio> listaPremios;
    private ArrayList<String> listaFacturas;

    public Cliente(int id, int idFoto, int platosTotales, int platosFaltantes, int premio, String documentoIdentidad, String apellidoPaterno, String apellidoMaterno, String nombres, String genero, String fechaNacimiento, String correo, String contra, String numeroDocumento) {
        this.id = id;
        this.idFoto = idFoto;
        this.platosTotales = platosTotales;
        this.platosFaltantes = platosFaltantes;
        this.premio = premio;
        this.documentoIdentidad = documentoIdentidad;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.nombres = nombres;
        this.genero = genero;
        this.fechaNacimiento = fechaNacimiento;
        this.correo = correo;
        this.contra = contra;
        this.numeroDocumento = numeroDocumento;
    }

    public Cliente(int idFoto, int platosTotales, int platosFaltantes, int premio, String documentoIdentidad, String apellidoPaterno, String apellidoMaterno, String nombres, String genero, String fechaNacimiento, String correo, String contra, String numeroDocumento) {
        this.idFoto = idFoto;
        this.platosTotales = platosTotales;
        this.platosFaltantes = platosFaltantes;
        this.premio = premio;
        this.documentoIdentidad = documentoIdentidad;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.nombres = nombres;
        this.genero = genero;
        this.fechaNacimiento = fechaNacimiento;
        this.correo = correo;
        this.contra = contra;
        this.numeroDocumento = numeroDocumento;
    }

    public Cliente(String apellidoPaterno, String apellidoMaterno, String nombres, String contra) {
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.nombres = nombres;
        this.contra = contra;
        this.numeroDocumento = numeroDocumento;
    }

    public Cliente() {
    }

    public ArrayList<String> getListaFacturas() {
        return listaFacturas;
    }

    public void setListaFacturas(ArrayList<String> listaFacturas) {
        this.listaFacturas = listaFacturas;
    }

    public ArrayList<Premio> getListaPremios() {
        return listaPremios;
    }

    public void setListaPremios(ArrayList<Premio> listaPremios) {
        this.listaPremios = listaPremios;
    }

    public int getIdFoto() {
        return idFoto;
    }

    public void setIdFoto(int idFoto) {
        this.idFoto = idFoto;
    }
    public int getPlatosTotales() {
        return platosTotales;
    }

    public void setPlatosTotales(int platosTotales) {
        this.platosTotales = platosTotales;
    }

    public int getPlatosFaltantes() {
        return platosFaltantes;
    }

    public void setPlatosFaltantes(int platosFaltantes) {
        this.platosFaltantes = platosFaltantes;
    }

    public int getPremio() {
        return premio;
    }

    public void setPremio(int premio) {
        this.premio = premio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDocumentoIdentidad() {
        return documentoIdentidad;
    }

    public void setDocumentoIdentidad(String documentoIdentidad) {
        this.documentoIdentidad = documentoIdentidad;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        nombres = nombres;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContra() {
        return contra;
    }

    public void setContra(String contra) {
        this.contra = contra;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }
}