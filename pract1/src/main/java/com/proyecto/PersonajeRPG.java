package com.proyecto;

import java.util.ArrayList;

/**
 * Represents a character of a role-playing game (RPG).
 *
 * @author Eli
 * @version 1.0
 */
public class PersonajeRPG {

    // ------------------------------ constant attributes ------------------------------

    /** Warrior class. */
    public static final int CLASE_GUERRERO =1;
    /** Mage class. */
    public static final int CLASE_MAGO =2;
    /** Archer class. */
    public static final int CLASE_ARQUERO=3;
    /** Assassin class. */
    public static final int CLASE_ASESINO=4;

    // --------------------------------- attributes -----------------------------------

    private int idPersonaje;
    /** Name of the character. */
    public String nombrePersonaje;
    private int clasePersonaje;
    private int nivel;
    private int puntosVida;
    private double puntosDanio;
    private boolean esLegendario;
    private ArrayList<String> habilidades;
    private String guildName;

    // ------------------------------ empty constructor -------------------------------

    /**
     * Creates a character with random values.
     */
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

    // ----------------------------- full constructor ---------------------------------

    /**
     * Creates a character with the given data.
     * The id is always generated randomly.
     *
     * @param idPersonaje id (not used)
     * @param nombrePersonaje name of the character
     * @param clasePersonaje class of the character (from 1 to 4)
     * @param nivel level of the character
     * @param puntosVida life points
     * @param puntosDanio damage points
     * @param esLegendario whether it is legendary or not
     * @param habilidades list of skills
     * @param guildName name of the guild
     */
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

    // -------------------------------- getters and setters ---------------------------

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

    // ---------------------------------- toString ------------------------------------

    /**
     * Returns a text with the data of the character.
     *
     * @return the data of the character as text
     */
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