# Proyecto Torneo RPG

## Description

This project is a Java program that simulates a role-playing video game tournament. It has two classes:

- PersonajeRPG: a character of the game. It stores its id, name, class (warrior, mage, archer or assassin), level, life, damage, skills and guild.
- TorneoEsports: a tournament with a list of characters. It is used to calculate the average damage of a class, count legendary characters with a skill, get the top characters with the most life, find the strongest character of a guild, and raise the level of everyone while removing the weakest ones.

The code is documented with Javadoc.

## Prerequisites

You need to have installed:

- Java JDK 17 or higher (check the exact version in the `pom.xml`)
- Maven
- Git

## Step-by-step installation

1. Clone the repository:

       git clone https://github.com/elisabethfalcongordillo21/Practice1-Gitlab

2. Go into the project folder:

       cd Practice1-Gitlab/pract1

3. Compile the project:

       mvn clean compile

4. Generate the Javadoc documentation:

       mvn javadoc:javadoc

5. Open the file `target/site/apidocs/index.html` in your browser.