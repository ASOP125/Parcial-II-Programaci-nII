import java.util.List;


public abstract class Pokemon {
    protected String name;       // Nombre del Pokémon
    protected int maxHp;         // Vida máxima
    protected int currentHp;     // Vida actual
    protected int attack;        // Ataque base
    protected int maxDef;        // Defensa
    protected int maxSpd;        // Velocidad
    protected int level;         // Nivel de evolución
    protected PokemonType type;  // Tipo de Pokémon (FIRE, WATER, ELECTRIC, etc.)
    protected List<Attack> attacks; // Lista de ataques

    // Constructor completo
    public Pokemon(String name, int maxHp, int attack, int maxDef, int maxSpd, int level,
                   PokemonType type, List<Attack> attacks) {
        this.name = name;
        this.maxHp = maxHp;
        this.currentHp = maxHp;
        this.attack = attack;
        this.maxDef = maxDef;
        this.maxSpd = maxSpd;
        this.level = level;
        this.type = type;
        this.attacks = attacks;
    }

    // --- Getters y Setters ---
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getMaxHp() { return maxHp; }
    public void setMaxHp(int maxHp) { this.maxHp = maxHp; }

    public int getCurrentHp() { return currentHp; }
    public void setCurrentHp(int currentHp) { this.currentHp = currentHp; }

    public int getAttack() { return attack; }
    public void setAttack(int attack) { this.attack = attack; }

    public int getMaxDef() { return maxDef; }
    public void setMaxDef(int maxDef) { this.maxDef = maxDef; }

    public int getMaxSpd() { return maxSpd; }
    public void setMaxSpd(int maxSpd) { this.maxSpd = maxSpd; }

    public List<Attack> getAttacks() { return attacks; }
    public void setAttacks(List<Attack> attacks) { this.attacks = attacks; }

    public int getLevel() { return level; }
    public void setLevel(int level) { this.level = level; }

    public PokemonType getType() { return type; }
    public void setType(PokemonType type) { this.type = type; }

    // Cálculo del daño con excepción
    public int calculateDamage(Attack attack, Pokemon defender) throws AttackMissedException {
        // Primero revisamos si el ataque acierta
        double rand = Math.random(); // 0.0 a 1.0
        if (rand > attack.getAccuracy()) {
            throw new AttackMissedException(this.getName() + " falló el ataque " + attack.getName() + "!");
        }

        // Si acierta, usamos la regla de daño del ataque
        if (attack.getDamageRule() != null) {
            return attack.getDamageRule().calculate(this, defender, attack);
        }

        // Si no hay regla personalizada, usamos la fórmula básica
        int baseDamage = (this.attack * this.level) / 10;

        // Modificador por tipo (ejemplo simple)
        switch (this.type) {
            case FUEGO:     baseDamage *= 1.2; break;
            case AGUA:      baseDamage *= 1.4; break;
            case DRAGON:    baseDamage *= 1.5; break;
            case ELECTRICO: baseDamage *= 1.3; break;
            case HADA:      baseDamage *= 1.4; break;
            case PLANTA:    baseDamage *= 1.0; break;
            case HIELO:     baseDamage *= 1.2; break;
            default: break;
        }

        return baseDamage;
    }

    // Método para recibir daño
    public void receiveDamage(int damage) {
        this.currentHp -= damage;
        if (this.currentHp < 0) this.currentHp = 0;
    }

    // Verificar si está debilitado
    public boolean isFainted() {
        return this.currentHp <= 0;
    }
}
