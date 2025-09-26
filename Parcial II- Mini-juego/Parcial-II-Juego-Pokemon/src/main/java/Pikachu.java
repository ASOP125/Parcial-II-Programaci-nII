import java.util.ArrayList;
import java.util.List;

//--- Pokemon No. 0025 ---

public class Pikachu extends Pokemon {
    public Pikachu(int level) {
        /* Se define la vida más el nivel que eliga el jugador más bonificación ( multiplicar por 5)
        también se define el ataque , la defensa y la velocidad máxima más el  nivel.
         */
        super(
                "Pikachu",

                60 + level * 5,
                49 + level,
                40 + level,
                90 + level,
                level,
                PokemonType.ELECTRICO,
                new ArrayList<>()
        );

        //--- Ataques ---

        attacks.add(new Attack("Rayo",75, 1.0,(attacker, defender, attack)->{
            int damage = (int)(attack.getPower() * attacker.getLevel() / 10);

            // --- Se usa un if para definir un ataque mas fuerte (Electrico es fuerte frente a tipo Agua) ---
            if(defender.getType() == PokemonType.AGUA){
                damage *= 1.5;
            }
            if(defender.getType() == PokemonType.DRAGON){
                damage *= 1.0; // --- Dragon es mas fuerte que eléctrico el daño se reduce ---
            }
            return(damage);
        } ));
        // ---  Ataque físico neutro ---
        attacks.add(new Attack("Ataque Rápido", 30, 1.0, (attacker, defender, attack) -> {
            return (attack.getPower() * attacker.getLevel()) / 10;
        }));

        // --- Ataque especial más fuerte pero menos preciso ---
        attacks.add(new Attack("Impactrueno", 60, 0.8, (attacker, defender, attack) -> {
            int damage = (int)(attack.getPower() * attacker.getLevel() / 9.0);

            if (defender.getType() == PokemonType.AGUA) {
                damage *= 1.5; // --- Muy fuerte contra Agua ---
            }

            return damage;
        }));
    }
}
