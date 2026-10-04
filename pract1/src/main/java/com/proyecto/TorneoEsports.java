package com.proyecto;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class TorneoEsports {
/*********************atributos**************************************************/    
private String codigoTorneo;
public String nombreTorneo;
private int jugadoresResgistrados;
private ArrayList<PersonajeRPG>listaJugadores;
private String servidorRegion;
private int poolPremios;
private int requiereNivelMinimo;
private boolean esRanked;

/*******************constructor vacio************************************************************/

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

/*****************constructor****************************************************************************/    
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
/********************gets y sets******************************************************************************/    

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
/*****************funciones*********************************************************************************/

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
