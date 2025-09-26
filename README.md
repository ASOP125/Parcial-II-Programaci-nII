# Parcial-II-Programacion-II
 Mi juego de se llama “ Pokemon Battle” es un mini-juego de consola desarrollado en Java que simula una batalla por turnos entre un jugador y la computadora.  
2.1 Objetivo
Derrotar al Pokémon de la CPU dejando sus puntos de vida (HP) en 0, usando los ataques de un pokemon que elegirá el jugador entre ellos: Pikachu, Bulbasaur, Charmander, Alcremie, Squirtle y Dragonair.

 
3.	Estructura de Carpetas y Breve Explicación de Diseño 

src
│ Parcial II- Mini-juego
├── Main.java
│   └── Clase principal que gestiona el flujo del juego:
│       - Bienvenida y selección de Pokémon.
│       - Generación de Pokémon CPU.
│       - Ciclo de batalla por turnos.
│
├── Saludar.java
│   └── Clase auxiliar con método estático:
│       - saludarJugador(String nombre).
│
├── Attack.java
│   └── Define un ataque:
│       - String name
│       - int power
│       - double accuracy
│       - DamageRule damageRule
│
├── AttackMissedException.java
│   └── Excepción personalizada lanzada cuando un ataque falla.
│
├── DamageRule.java
│   └── Interfaz que define el cálculo de daño:
│       - int calculate(Pokemon attacker, Pokemon defender, Attack attack)
│
├── DefaultDamageRule.java
│   └── Implementación de DamageRule:
│       - Considera precisión, variación ±10%, y golpes críticos.
│
├── Pokemon.java
│   └── Clase abstracta que define atributos y métodos comunes:
│       - String name
│       - int maxHp, currentHp
│       - int attack, maxDef, maxSpd
│       - int level
│       - PokemonType type
│       - List<Attack> attacks
│       - Métodos: calculateDamage, receiveDamage, isFainted
│
├── PokemonType.java
│   └── Enum que define los tipos de Pokémon:
│       - FUEGO, AGUA, PLANTA, ELECTRICO, HADA, HIELO, DRAGON, 
│
├── Charmander.java
├── Bulbasaur.java
├── Pikachu.java
├── Squirtle.java
├── Alcremie.java
├── Dragonair.java
│   └── Todas heredan de Pokemon y personalizan atributos/ataques.
│   └── README.md : Descripción breve del juego.Estructura de carpetas y breve explicación de diseño.


 
