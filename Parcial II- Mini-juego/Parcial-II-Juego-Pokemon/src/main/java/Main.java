import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();

        // --- Bienvenida ---
        System.out.println("-------- Bienvenid@ a Pokemon Battle -------");
        System.out.print("Ingresa tu nombre: ");
        String playerName = sc.nextLine();
        Saludar.saludarJugador(playerName);

        // --- Pokemones disponibles ---
        List<String> pokemonNames = Arrays.asList("Charmander", "Bulbasaur", "Pikachu", "Squirtle", "Alcremie", "Dragonair");

        // --- Elección del Pokémon del jugador ---
        System.out.println("Elige tu Pokémon:");
        for (int i = 0; i < pokemonNames.size(); i++) {
            System.out.println((i + 1) + ". " + pokemonNames.get(i));
        }

        int choice;
        do {
            System.out.print("Número de tu elección: ");
            choice = sc.nextInt();
        } while (choice <= 0 || choice > pokemonNames.size());

        System.out.println("Elige el nivel de tu Pokémon (1 al 100): ");
        int playerLevel = sc.nextInt();

        // --- Crear Pokémon del jugador ---
        Pokemon playerPokemon = switch (choice) {
            case 1 -> new Charmander(playerLevel);
            case 2 -> new Bulbasaur(playerLevel);
            case 3 -> new Pikachu(playerLevel);
            case 4 -> new Squirtle(playerLevel);
            case 5 -> new Alcremie(playerLevel);
            case 6 -> new Dragonair(playerLevel);
            default -> throw new IllegalArgumentException("Opción inválida");
        };

        // --- CPU elige Pokémon distinto ---
        List<Integer> cpuOptions = new ArrayList<>();
        for (int i = 0; i < pokemonNames.size(); i++) {
            if (i + 1 != choice) cpuOptions.add(i);
        }
        int cpuChoice = cpuOptions.get(rand.nextInt(cpuOptions.size()));
        int cpuLevel = rand.nextInt(100) + 1;

        Pokemon cpuPokemon = switch (cpuChoice) {
            case 0 -> new Charmander(cpuLevel);
            case 1 -> new Bulbasaur(cpuLevel);
            case 2 -> new Pikachu(cpuLevel);
            case 3 -> new Squirtle(cpuLevel);
            case 4 -> new Alcremie(cpuLevel);
            case 5 -> new Dragonair(cpuLevel);
            default -> throw new IllegalArgumentException("Opción inválida");
        };

        // --- Estado inicial ---
        System.out.println("\n--- Estado inicial ---");
        System.out.println("Jugador: " + playerPokemon.getName() +
                " | Nivel " + playerPokemon.getLevel() +
                " | HP " + playerPokemon.getCurrentHp() + "/" + playerPokemon.getMaxHp());
        System.out.println("CPU: " + cpuPokemon.getName() +
                " | Nivel " + cpuPokemon.getLevel() +
                " | HP " + cpuPokemon.getCurrentHp() + "/" + cpuPokemon.getMaxHp());

        // --- Variables para estadísticas ---
        Map<String, Integer> totalDamageByActor = new HashMap<>();
        Map<String, Integer> attacksByActor = new HashMap<>();
        Map<String, Integer> missedByActor = new HashMap<>();
        List<String> battleLog = new ArrayList<>();

        sc.nextLine(); // limpiar buffer

        // --- Batalla por turnos ---
        while (!playerPokemon.isFainted() && !cpuPokemon.isFainted()) {
            // Turno del jugador
            System.out.println("\nTus ataques:");
            List<Attack> attacks = playerPokemon.getAttacks();
            for (int i = 0; i < attacks.size(); i++) {
                System.out.println((i + 1) + ". " + attacks.get(i).getName() +
                        " (Power: " + attacks.get(i).getPower() + ")");
            }

            int atkChoice;
            do {
                System.out.print("Selecciona ataque: ");
                atkChoice = sc.nextInt();
            } while (atkChoice <= 0 || atkChoice > attacks.size());

            Attack playerAttack = attacks.get(atkChoice - 1);

            try {
                int damage = playerPokemon.calculateDamage(playerAttack, cpuPokemon);
                cpuPokemon.receiveDamage(damage);
                battleLog.add(playerPokemon.getName() + " usó " + playerAttack.getName() +
                        " y causó " + damage + " de daño");
                System.out.println(playerPokemon.getName() + " usó " + playerAttack.getName() +
                        " y causó " + damage + " de daño a " + cpuPokemon.getName());

                // Estadísticas
                totalDamageByActor.merge(playerPokemon.getName(), damage, Integer::sum);
                attacksByActor.merge(playerPokemon.getName(), 1, Integer::sum);
            } catch (AttackMissedException e) {
                battleLog.add(e.getMessage());
                System.out.println(e.getMessage());
                missedByActor.merge(playerPokemon.getName(), 1, Integer::sum);
            }

            if (cpuPokemon.isFainted()) break;

            // Turno de la CPU
            Attack cpuAttack = cpuPokemon.getAttacks().get(rand.nextInt(cpuPokemon.getAttacks().size()));
            try {
                int damage = cpuPokemon.calculateDamage(cpuAttack, playerPokemon);
                playerPokemon.receiveDamage(damage);
                battleLog.add(cpuPokemon.getName() + " usó " + cpuAttack.getName() +
                        " y causó " + damage + " de daño");
                System.out.println(cpuPokemon.getName() + " usó " + cpuAttack.getName() +
                        " y causó " + damage + " de daño a " + playerPokemon.getName());

                // Estadísticas
                totalDamageByActor.merge(cpuPokemon.getName(), damage, Integer::sum);
                attacksByActor.merge(cpuPokemon.getName(), 1, Integer::sum);
            } catch (AttackMissedException e) {
                battleLog.add(e.getMessage());
                System.out.println(e.getMessage());
                missedByActor.merge(cpuPokemon.getName(), 1, Integer::sum);
            }

            // --- Estado después del turno ---
            System.out.println("\n--- Estado después del turno ---");
            System.out.println("Jugador: " + playerPokemon.getName() +
                    " | Nivel " + playerPokemon.getLevel() +
                    " | HP " + playerPokemon.getCurrentHp() + "/" + playerPokemon.getMaxHp());
            System.out.println("CPU: " + cpuPokemon.getName() +
                    " | Nivel " + cpuPokemon.getLevel() +
                    " | HP " + cpuPokemon.getCurrentHp() + "/" + cpuPokemon.getMaxHp());
        }

        // --- Resultado final ---
        System.out.println("\n--- ¡Batalla finalizada! ---");
        if (playerPokemon.isFainted() && cpuPokemon.isFainted()) {
            System.out.println("¡Empate!");
        } else if (playerPokemon.isFainted()) {
            System.out.println("¡Ganó la CPU!");
        } else {
            System.out.println("¡Ganaste, " + playerName + "!");
        }

        // --- Resumen ---
        System.out.println("\n--- Resumen de la batalla ---");
        System.out.println("Daño total por Pokémon: " + totalDamageByActor);
        System.out.println("Número de ataques usados: " + attacksByActor);
        System.out.println("Número de ataques fallidos: " + missedByActor);

        System.out.println("\nDetalle de Ataques:");
        battleLog.forEach(System.out::println);
    }
}
