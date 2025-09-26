import java.util.ArrayList;

// Pokemon No.0007

public class Squirtle extends Pokemon {
    public Squirtle(int level) {

        /* Se define la vida más el nivel que eliga el jugador más bonificación ( multiplicar por 5)
        también se define el ataque , la defensa y la velocidad máxima más el  nivel.
         */

        super(
                "Squirtle",
                77 + level * 5,
                48 + level,
                64 + level,
                43 + level,
                level,
                PokemonType.AGUA,
                new ArrayList<>()
        );
        // --- Ataques de Squirtle ---
        attacks.add(new Attack("Pistola de Agua", 65, 0.95,(attacker,defender,attack) ->{
            int damage = (int)(attack.getPower() * attacker.getLevel() / 10.0);

            // --- Agua fuerte frente al tipo Fuego ---
            if(defender.getType() == PokemonType.FUEGO) {
                damage *= 1.3;
            }
            // --- Ataque debil contra electrico ---
            if(defender.getType() == PokemonType.ELECTRICO) {
                damage *= 0.9;
            }
            return(damage);

        }));

        attacks.add(new Attack("Burbuja", 30, 1.2, (attacker, defender, attack)->{
            return(attack.getPower() * attacker.getLevel() / 10);
        }));

        attacks.add(new Attack("Giro Rapido", 25, 1.0, (attacker, defender, attack)->{
            return(attack.getPower() * attacker.getLevel() / 10);
        }));


    }


}
