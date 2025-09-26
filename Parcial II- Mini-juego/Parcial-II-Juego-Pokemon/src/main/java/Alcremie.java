import java.util.ArrayList;
import java.util.List;

//Pokemon No.0869

public class Alcremie extends Pokemon {

    public  Alcremie(int level){

        /* Se define la vida más el nivel que eliga el jugador más bonificación ( multiplicar por 5)
        también se define el ataque , la defensa y la velocidad máxima más el  nivel.
         */

        super(
                "Alcremie",
                70 + level * 5,
                45 + level,
                49 + level,
                20 + level, // --- debilidad de Alcremie = baja velocidad ---
                level,
                PokemonType.HADA,
                new ArrayList<>()
                );
        // --- Ataques ---
        attacks.add(new Attack("Brillo Mágico", 35, 0.9, (attacker, defender, attack) -> {
            int damage = (attack.getPower() * attacker.getLevel()) / 10;

            if(defender.getType() == PokemonType.DRAGON) {
                damage *= 1.5; //Efectivo contra DRAGON
            }
            return damage;
        }));

        attacks.add(new Attack("Rizo Cremoso", 25, 1.0, (attacker, defender, attack) -> {
            return (attack.getPower() * attacker.getLevel()) / 10; // --- Ataque simple ---
        }));

        attacks.add(new Attack("Explosion de Azúcar", 60, 0.8, (attacker, defender, attack) -> {
            int damage = (attack.getPower() * attacker.getLevel()) / 10;
            if (defender.getType() == PokemonType.DRAGON) {
                damage *= 1.3; // --- Bonus contra Dragón ---
            }
            return damage;
        }));
    }

}
