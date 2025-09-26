import java.util.ArrayList;

public class Dragonair extends Pokemon {

    public Dragonair(int level) {

        /* Se define la vida más el nivel que eliga el jugador más bonificación ( multiplicar por 5)
        también se define el ataque , la defensa y la velocidad máxima más el  nivel.
         */

        super(
                "Dragonair",
                61 + level * 5,
                84 + level,
                65 + level,
                70 + level,
                level,
                PokemonType.DRAGON,   // --- Tipo DRAGON ---
                new ArrayList<>()     // --- Lista de ataques vacía por ahora ---
        );

        // --- Ataques ---
        attacks.add(new Attack("Cola Dragón", 60, 0.9, (attacker, defender, attack) -> {
            int damage = (attack.getPower() * attacker.getLevel()) / 10;
            if (defender.getType() == PokemonType.DRAGON) {
                damage *= 0.5; // --- Débil contra Dragon ---
            }
            return damage;
        }));

        attacks.add(new Attack("Ataque Ala", 40, 1.0, (attacker, defender, attack) -> {
            return (attack.getPower() * attacker.getLevel()) / 10; // --- Ataque simple ---
        }));

        attacks.add(new Attack("Llama de Dragón", 70, 0.8, (attacker, defender, attack) -> {
            int damage = (attack.getPower() * attacker.getLevel()) / 10;
            if (defender.getType() == PokemonType.HADA) {
                damage *= 0.5; // --- Débil contra HADA ---
            }
            return damage;
        }));
    }
}
