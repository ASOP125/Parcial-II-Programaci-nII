import java.util.Random;

public class DefaultDamageRule implements DamageRule {
    private Random rand = new Random();

    @Override
    public int calculate(Pokemon attacker, Pokemon defender, Attack attack) {
        // --- Probabilidad de fallo ---
        if (rand.nextDouble() > attack.getAccuracy()) {
            System.out.println(attacker.getName() + " falló su ataque " + attack.getName());
            return 0; // falló el ataque
        }

        // --- Daño base ---
        int baseDamage = (attack.getPower() * attacker.getLevel()) / 10;

        // --- Variación ±10% ---
        int variation = (int)(baseDamage * (rand.nextDouble() * 0.2 - 0.1));
        baseDamage += variation;

        // --- Crítico (10% chance) ---
        if (rand.nextInt(100) < 10) {
            baseDamage *= 2;
            System.out.println("¡Golpe crítico!");
        }

        return Math.max(baseDamage, 1); // nunca menos de 1
    }
}