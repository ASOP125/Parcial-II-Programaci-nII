
//Nota: No sé exactamente como hacer paquetes de excepciones asi que lo hice simple

public class AttackMissedException extends Exception {
    public AttackMissedException(String message) {
        super(message);
    }
}
