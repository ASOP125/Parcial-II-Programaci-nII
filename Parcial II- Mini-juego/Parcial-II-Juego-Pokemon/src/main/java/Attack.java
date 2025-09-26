

import java.util.Random;

public class Attack {
    private String name;
    private int power;
    private double accuracy;
    private DamageRule damageRule;

    // Constructor
    public Attack(String name, int power, double accuracy, DamageRule damageRule){
        this.name = name;
        this.power = power;
        this.accuracy = accuracy;
        this.damageRule = damageRule;
    }

    // --- Getters y Setters ---
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getPower() { return power; }
    public void setPower(int power) { this.power = power; }

    public double getAccuracy() { return accuracy; }
    public void setAccuracy(double accuracy) { this.accuracy = accuracy; }

    public DamageRule getDamageRule() { return damageRule; }
    public void setDamageRule(DamageRule damageRule) { this.damageRule = damageRule; }
}
