# Proyecto Torneo RPG

## Descripción

Este proyecto es un programa en Java que simula un torneo de un videojuego de rol. Tiene dos clases:

- PersonajeRPG: es un personaje del juego. Guarda su id, nombre, clase (guerrero, mago, arquero o asesino), nivel, vida, daño, habilidades y gremio.
- TorneoEsports: es un torneo con una lista de personajes. Sirve para calcular el daño medio de una clase, contar legendarios con una habilidad, sacar el top de personajes con más vida, buscar el más fuerte de un gremio y subir de nivel a todos eliminando a los más débiles.

El código está documentado con Javadoc.

## Requisitos previos

Hay que tener instalado:

- Java JDK 17 o superior (mira la versión exacta en el `pom.xml`)
- Maven
- Git

## Instalación paso a paso

1. Clona el repositorio:

       git clone https://github.com/TU-USUARIO/NOMBRE-REPO.git

2. Entra en la carpeta del proyecto:

       cd NOMBRE-REPO

3. Compila el proyecto:

       mvn clean compile

4. Genera la documentación Javadoc:

       mvn javadoc:javadoc

5. Abre en el navegador el archivo `target/site/apidocs/index.html`.

