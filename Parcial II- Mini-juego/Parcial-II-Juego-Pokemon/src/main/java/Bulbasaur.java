import java.util.ArrayList;

//Pokemon No.0001
public class Bulbasaur extends Pokemon {

    public Bulbasaur(int level) {

        /* Se define la vida más el nivel que eliga el jugador más bonificación ( multiplicar por 5)
        también se define el ataque , la defensa y la velocidad máxima más el  nivel.
         */

        super(
                "Bulbasuar",
                70 + level * 5,
                49 + level,
                49 + level,
               45 + level,
                level,
                PokemonType.PLANTA,          // --- el tipo de pokemon que es ---
                new ArrayList<>()           // --- Lista de ataques vacios ---
        );

        //--- Ataques ---
        attacks.add(new Attack("Látigo Cepa", 35, 0.9, (attacker, defender, attack) -> {
            int damage = (attack.getPower() * attacker.getLevel()) / 10;
            if(defender.getType() == PokemonType.AGUA) {
                damage *= 1.5; // --- Efectivo contra Agua ---
            }else if(defender.getType() == PokemonType.FUEGO) {
                damage *= 0.8; // --- Debil contra Fuego ---
            }
            return damage;
        }));
        attacks.add(new Attack("Placaje", 20, 1.0, (attacker, defender, attack) -> {
            return (attack.getPower() * attacker.getLevel()) / 10; // --- Ataque simple ---
        }));

        attacks.add(new Attack("Bomba Germen", 50, 0.8, (attacker, defender, attack) -> {
            int damage = (attack.getPower() * attacker.getLevel()) / 10;
            if (defender.getType() == PokemonType.AGUA) {
                damage *= 1.3; // --- Ataque semi-fuerte contra agua ---
            }
            return damage;
        }));
    }
}