package com.proyecto;

import java.util.ArrayList;

public class PersonajeRPG {
    
/***************************atributos constantes***********************************************************/
public static final int CLASE_GUERRERO =1;
public static final int CLASE_MAGO =2;
public static final int CLASE_ARQUERO=3;
public static final int CLASE_ASESINO=4;

/****************************atributos******************************************************************************/
private int idPersonaje;
public String nombrePersonaje;
private int clasePersonaje;
private int nivel;
private int puntosVida;
private double puntosDanio;
private boolean esLegendario;
private ArrayList<String> habilidades;
private String guildName;
/**************************constructor vacio****************************************************************************************/

    public PersonajeRPG() 
    {
        this.idPersonaje= (int)(Math.random()*99999)+10000;
        this.nombrePersonaje="";
        this.clasePersonaje=(int)(Math.random()*4)+1;
        this.nivel=(int)(Math.random()*50)+1;
        this.puntosVida=(int)(Math.random()*5000)+1000;
        this.puntosDanio=(Math.random()*250.0)+50.0;
        this.esLegendario=false;
        this.habilidades= new ArrayList<>();
        this.guildName="";
    }
 /************************constructor completo*********************************************************************************************************/   

    public PersonajeRPG(int idPersonaje, String nombrePersonaje, int clasePersonaje, int nivel, int puntosVida, double puntosDanio, boolean esLegendario, ArrayList<String> habilidades, String guildName) {
        this.idPersonaje = (int)(Math.random()*99999)+10000;
        this.nombrePersonaje = nombrePersonaje;
        this.clasePersonaje = clasePersonaje;
        this.nivel = nivel;
        this.puntosVida = puntosVida;
        this.puntosDanio = puntosDanio;
        this.esLegendario = esLegendario;
        this.habilidades = habilidades;
        this.guildName = guildName;
    }

/***********************gets y sets*****************************************************************************************************************************************/

    public int getIdPersonaje() {
        return this.idPersonaje;
    }

    public void setIdPersonaje(int idPersonaje) {
        this.idPersonaje = idPersonaje;
    }

    public String getNombrePersonaje() {
        return this.nombrePersonaje;
    }

    public void setNombrePersonaje(String nombrePersonaje) {
        this.nombrePersonaje = nombrePersonaje;
    }

    public int getClasePersonaje() {
        return this.clasePersonaje;
    }

    public void setClasePersonaje(int clasePersonaje) {
        this.clasePersonaje = clasePersonaje;
    }

    public int getNivel() {
        return this.nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public int getPuntosVida() {
        return this.puntosVida;
    }

    public void setPuntosVida(int puntosVida) {
        this.puntosVida = puntosVida;
    }

    public double getPuntosDanio() {
        return this.puntosDanio;
    }

    public void setPuntosDanio(double puntosDanio) {
        this.puntosDanio = puntosDanio;
    }

    public boolean isEsLegendario() {
        return this.esLegendario;
    }

    public void setEsLegendario(boolean esLegendario) {
        this.esLegendario = esLegendario;
    }

    public ArrayList<String> getHabilidades() {
        return this.habilidades;
    }

    public void setHabilidades(ArrayList<String> habilidades) {
        this.habilidades = habilidades;
    }

    public String getGuildName() {
        return this.guildName;
    }

    public void setGuildName(String guildName) {
        this.guildName = guildName;
    }

/********************toString**************************************************************************************************************/

    @Override
    public String toString() {
        String clasePersonaje;
        switch (this.clasePersonaje) {
            case CLASE_GUERRERO:
                    clasePersonaje="CLASE_GUERRERO";
                break;
             case CLASE_MAGO:
                    clasePersonaje="CLASE_MAGO";
                break;
            case CLASE_ARQUERO:
                    clasePersonaje="CLASE_ARQUERO";
                break;
            case CLASE_ASESINO:
                    clasePersonaje="CLASE_ASESINO";
                break;
            default:clasePersonaje="inválido";
                break;
        }
        return "===Personaje RPG===" +
            " idPersonaje='" + getIdPersonaje() + "'" +
            ", nombrePersonaje='" + getNombrePersonaje() + "'" +
            ", clasePersonaje='" + clasePersonaje + "'" +
            ", nivel='" + getNivel() + "'" +
            ", puntosVida='" + getPuntosVida() + "'" +
            ", puntosDanio='" + getPuntosDanio() + "'" +
            ", esLegendario='" + isEsLegendario() + "'" +
            ", habilidades='" + getHabilidades() + "'" +
            ", guildName='" + getGuildName() + "'" +
            "}";
    }




}

