import java.util.ArrayList;
import java.util.List;

//Pokemon No.0004

public class Charmander extends Pokemon {

    public Charmander(int level) {
        /* Se define la vida más el nivel que eliga el jugador más bonificación ( multiplicar por 5)
        también se define el ataque , la defensa y la velocidad máxima más el  nivel.
         */

        super(
                "Charmander",
                39 + level * 5,
                52 + level,
                43 + level,
                65 + level,
                level,
                PokemonType.FUEGO, // --- Tipo de pokemon ---
                new ArrayList<>() // --- Lista de ataques vacía por ahora ---
        );

        // --- Ataques ---
        attacks.add(new Attack("Nitrocarga", 50, 0.9, (attacker, defender, attack) -> {
            int damage = (int)(attack.getPower() * attacker.getLevel() / 10.0);

            //  --- Bonus si el defensor es de tipo HIELO ---
            if (defender.getType() == PokemonType.HIELO) {
                damage *= 1.5;
            }
            if (defender.getType() == PokemonType.AGUA) {
                damage *= 0.9;  // --- Ataque debil ante el tipo Agua ---
            }
            return damage;
        }));

        attacks.add(new Attack("Arañazo", 20, 1.0, (attacker, defender, attack) -> {
            // --- Ataque simple sin bonus por tipo ---
            return (attack.getPower() * attacker.getLevel()) / 10;
        }));

        attacks.add(new Attack("Pirotecnia", 70, 1.2, (attacker, defender, attack) -> {
            // --- Ataque simple sin bonus por tipo ---
            return (attack.getPower() * attacker.getLevel()) / 10;
        }));
    }
}
