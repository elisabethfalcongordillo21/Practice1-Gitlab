package com.proyecto;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Represents an esports tournament with a list of registered RPG characters.
 *
 * @author Eli
 * @version 1.0
 */
public class TorneoEsports {
// --------------------------------- attributes -----------------------------------
private String codigoTorneo;
/** Name of the tournament. */
public String nombreTorneo;
private int jugadoresResgistrados;
private ArrayList<PersonajeRPG>listaJugadores;
private String servidorRegion;
private int poolPremios;
private int requiereNivelMinimo;
private boolean esRanked;

// ------------------------------ empty constructor -------------------------------

    /**
     * Creates a tournament with default values and an empty list of players.
     */
    public TorneoEsports() 
    {
        this.codigoTorneo="";
        this.nombreTorneo="";
        this.jugadoresResgistrados=(int)(Math.random()*100);
        this.listaJugadores= new ArrayList<>();
        this.servidorRegion="";
        this.poolPremios=0;
        this.requiereNivelMinimo=1;
        this.esRanked=false;
    }

// -------------------------------- constructor -----------------------------------

    /**
     * Creates a tournament with the given data.
     * The number of registered players is calculated from the size of the list.
     *
     * @param codigoTorneo code of the tournament
     * @param nombreTorneo name of the tournament
     * @param listaJugadores list of registered characters
     * @param servidorRegion server or region of the tournament
     * @param poolPremios total prize money
     * @param requiereNivelMinimo minimum level to take part
     * @param esRanked whether the tournament is ranked or not
     */
    public TorneoEsports(String codigoTorneo, String nombreTorneo, ArrayList<PersonajeRPG> listaJugadores, String servidorRegion, int poolPremios, int requiereNivelMinimo, boolean esRanked) {
        this.codigoTorneo = codigoTorneo;
        this.nombreTorneo = nombreTorneo;
        this.jugadoresResgistrados = listaJugadores.size();
        this.listaJugadores = listaJugadores;
        this.servidorRegion = servidorRegion;
        this.poolPremios = poolPremios;
        this.requiereNivelMinimo = requiereNivelMinimo;
        this.esRanked = esRanked;
    }
// -------------------------------- getters and setters ---------------------------

    public String getCodigoTorneo() {
        return this.codigoTorneo;
    }

    public void setCodigoTorneo(String codigoTorneo) {
        this.codigoTorneo = codigoTorneo;
    }

    public String getNombreTorneo() {
        return this.nombreTorneo;
    }

    public void setNombreTorneo(String nombreTorneo) {
        this.nombreTorneo = nombreTorneo;
    }

    public int getJugadoresResgistrados() {
        return this.jugadoresResgistrados;
    }

    public void setJugadoresResgistrados(int jugadoresResgistrados) {
        this.jugadoresResgistrados = jugadoresResgistrados;
    }

    public ArrayList<PersonajeRPG> getListaJugadores() {
        return this.listaJugadores;
    }

    public void setListaJugadores(ArrayList<PersonajeRPG> listaJugadores) {
        this.listaJugadores = listaJugadores;
    }

    public String getServidorRegion() {
        return this.servidorRegion;
    }

    public void setServidorRegion(String servidorRegion) {
        this.servidorRegion = servidorRegion;
    }

    public int getPoolPremios() {
        return this.poolPremios;
    }

    public void setPoolPremios(int poolPremios) {
        this.poolPremios = poolPremios;
    }

    public int getRequiereNivelMinimo() {
        return this.requiereNivelMinimo;
    }

    public void setRequiereNivelMinimo(int requiereNivelMinimo) {
        this.requiereNivelMinimo = requiereNivelMinimo;
    }

    public boolean isEsRanked() {
        return this.esRanked;
    }

    public void setEsRanked(boolean esRanked) {
        this.esRanked = esRanked;
    }
// ---------------------------------- functions -----------------------------------

/**
 * Calculates the average damage of the characters of a class.
 *
 * @param claseFiltro class to look for (from 1 to 4)
 * @return the average damage points, or 0.0 if there are no characters of that class
 */
public double calcularDanioPromedioClase(int claseFiltro)
{
    return listaJugadores.stream()
    //filter by clasePersonaje, if it matches, continue
    .filter(j->j.getClasePersonaje() == claseFiltro)
    //get the values of PuntosDanio
    .mapToDouble(j -> j.getPuntosDanio())
    //calculate the average
    .average()
    //returns 0.0 if there are no characters or no participants
    .orElse(0.0);
}


/**
 * Counts the legendary characters that have a skill.
 *
 * @param habilidadBuscada name of the skill to look for
 * @return how many legendary characters have that skill
 */
public int contarPersonajesLegendariosConHabilidad(String habilidadBuscada)
{
     return (int) listaJugadores.stream()
     //filter by legendary, if it is, continue
     .filter(j-> j.isEsLegendario())
     //filter the skills, keep those that contain habilidadBuscada
    .filter(j-> j.getHabilidades().contains(habilidadBuscada
        //converts them to lowercase
        .toLowerCase()))
        //counts them
        .count();
}

/**
 * Returns the characters with the most life.
 *
 * @param topN how many characters you want in the top
 * @return list with the top N characters from most to least life, or empty if topN is 0 or less
 */
public ArrayList<PersonajeRPG> obtenerTopPersonajesPorVida(int topN)
{
    //if there is nobody in the top, return an empty array
    if (topN<=0) {
        return new ArrayList<>();
    }

    //otherwise, return the list of players
    return listaJugadores.stream()
    //compare the life points of player 2 with player 1
    .sorted((p1,p2) -> Integer.compare(p2.getPuntosVida(), p1.getPuntosVida()))
    // limit the output to the number of players we want in the top (N)
    .limit(topN)
    //collect creates a new array where these top players are stored
    .collect(Collectors.toCollection(ArrayList::new));

}

/**
 * Finds the strongest character of a guild.
 * The one with the most damage wins; if there is a tie, the one with the highest level.
 *
 * @param nombreGremio name of the guild (not case sensitive)
 * @return the strongest character, or {@code null} if there is nobody in that guild
 */
public PersonajeRPG buscarPersonajeMasFuerteDeGremio(String nombreGremio)
{
    return listaJugadores.stream()
    //filter by guild name, ignoring upper and lower case
    .filter(jugador->jugador.getGuildName().equalsIgnoreCase(nombreGremio))
    //with max and comparator we compare the damage points and take the highest
    .max(Comparator.comparingDouble(PersonajeRPG::getPuntosDanio)
    //then we compare the level of the characters
    .thenComparing(PersonajeRPG::getNivel))
    //if there is none, return null
    .orElse(null);    
}

/**
 * Raises the level of all characters (maximum 100) and removes those
 * that stay below the minimum level.
 *
 * @param incrementoNivel levels added to each character
 * @param nivelMinimoSupervivencia minimum level to avoid being removed
 * @return {@code true} if any character was removed, {@code false} if not
 */
public boolean actualizarNivelesYEliminarDebiles(int incrementoNivel, int nivelMinimoSupervivencia)
{
    int antes =listaJugadores.size(); //we save how many there were at the start

    //raise the level of everyone with a cap of 100
    for (PersonajeRPG p : listaJugadores) {
        int nuevoNivel = p.getNivel() + incrementoNivel;
        p.setNivel(Math.min(100, nuevoNivel));
    }
    //remove those that do not reach the minimum
    listaJugadores.removeIf(p->p.getNivel()<nivelMinimoSupervivencia);
    //update the total counter
    this.jugadoresResgistrados=listaJugadores.size();
    //if the current size is smaller than before, someone has been removed
    return listaJugadores.size() <antes;
}

}