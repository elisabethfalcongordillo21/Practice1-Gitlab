package com.proyecto;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collector;
import java.util.stream.Collectors;


public class TorneoEsports {
// --------------------------------- atributos ------------------------------------
private String codigoTorneo;
/** Nombre del torneo. */
public String nombreTorneo;
private int jugadoresResgistrados;
private ArrayList<PersonajeRPG>listaJugadores;
private String servidorRegion;
private int poolPremios;
private int requiereNivelMinimo;
private boolean esRanked;

// ------------------------------ constructor vacio -------------------------------

    /**
     * Crea un torneo con valores por defecto y una lista de jugadores vacía.
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
     * Crea un torneo con los datos que se le pasan.
     * Los jugadores registrados se calculan con el tamaño de la lista.
     *
     * @param codigoTorneo código del torneo
     * @param nombreTorneo nombre del torneo
     * @param listaJugadores lista de personajes inscritos
     * @param servidorRegion servidor o región del torneo
     * @param poolPremios dinero total de premios
     * @param requiereNivelMinimo nivel mínimo para participar
     * @param esRanked si el torneo es ranked o no
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
// -------------------------------- gets y sets -----------------------------------

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
// ---------------------------------- funciones -----------------------------------

/**
 * Calcula el daño medio de los personajes de una clase.
 *
 * @param claseFiltro clase a buscar (de 1 a 4)
 * @return la media de puntos de daño, o 0.0 si no hay personajes de esa clase
 */
public double calcularDanioPromedioClase(int claseFiltro)
{
    return listaJugadores.stream()
    //filtrar por la clasePersonaje, si coincide, continua
    .filter(j->j.getClasePersonaje() == claseFiltro)
    //coge los valores de PuntosDanio
    .mapToDouble(j -> j.getPuntosDanio())
    //hace la media
    .average()
    //devuelve 0.0 si no hay personajes o si no tiene participantes
    .orElse(0.0);
}


/**
 * Cuenta los personajes legendarios que tienen una habilidad.
 *
 * @param habilidadBuscada nombre de la habilidad a buscar
 * @return cuántos legendarios tienen esa habilidad
 */

public int contarPersonajesLegendariosConHabilidad(String habilidadBuscada)
{
     return (int) listaJugadores.stream()
     //filtra por si es legendario, si lo es continua
     .filter(j-> j.isEsLegendario())
     //filtra las habilidades si contienen la habilidadBuscada
    .filter(j-> j.getHabilidades().contains(habilidadBuscada
        //las convierte en minuscula
        .toLowerCase()))
        //las cuenta
        .count();
}

/**
 * Devuelve los personajes con más vida.
 *
 * @param topN cuántos personajes quieres en el top
 * @return lista con los topN personajes de más a menos vida, o vacía si topN es 0 o menor
 */
public ArrayList<PersonajeRPG> obtenerTopPersonajesPorVida(int topN)
{
    //creamos un if poara que si no hay ningun jugador en el top devuelva un array vacio
    if (topN<=0) {
        return new ArrayList<>();
    }

    //si hay, se devuelve la lista de jugadores
    return listaJugadores.stream()
    //se comparan los puntos de vida del jugador 2 con el jugador 1
    .sorted((p1,p2) -> Integer.compare(p2.getPuntosVida(), p1.getPuntosVida()))
    // con limit limitamos la salida de los jugadores que queremos en el top(N)
    .limit(topN)
    //con collect creamos un nuevo array donde se guardaran estos jugadores del top
    .collect(Collectors.toCollection(ArrayList::new));

}

/**
 * Busca el personaje más fuerte de un gremio.
 * Gana el que más daño tiene; si empatan, el de más nivel.
 *
 * @param nombreGremio nombre del gremio (no distingue mayúsculas)
 * @return el personaje más fuerte, o {@code null} si no hay ninguno en ese gremio
 */
public PersonajeRPG buscarPersonajeMasFuerteDeGremio(String nombreGremio)
{
    return listaJugadores.stream()
    //se filtra por el nombre de gremio haciendo que sea sensible
    .filter(jugador->jugador.getGuildName().equalsIgnoreCase(nombreGremio))
    //con el max.comparator comparamos los puntos de daño del personaje cogiendo el mayor
    .max(Comparator.comparingDouble(PersonajeRPG::getPuntosDanio)
    //entonces comparamos el nivel de los personajes
    .thenComparing(PersonajeRPG::getNivel))
    //si no devuelve null
    .orElse(null);    
}

/**
 * Sube el nivel a todos los personajes (máximo 100) y elimina a los que
 * se quedan por debajo del nivel mínimo.
 *
 * @param incrementoNivel niveles que se suman a cada personaje
 * @param nivelMinimoSupervivencia nivel mínimo para no ser eliminado
 * @return true si se eliminó a algún personaje, false si no
 */
public boolean actualizarNivelesYEliminarDebiles(int incrementoNivel, int nivelMinimoSupervivencia)
{
    int antes =listaJugadores.size(); //guardamos cuantos habia al principio

    //subimos nivel a todos con el tope de 100
    for (PersonajeRPG p : listaJugadores) {
        int nuevoNivel = p.getNivel() + incrementoNivel;
        p.setNivel(Math.min(100, nuevoNivel));
    }
    //borramos los que no llegan al minimo
    listaJugadores.removeIf(p->p.getNivel()<nivelMinimoSupervivencia);
    //actualizar contador total
    this.jugadoresResgistrados=listaJugadores.size();
    //si el tamaño de ahora es menor que el de antes, es que hemos borrado a alguien
    return listaJugadores.size() <antes;
}

}