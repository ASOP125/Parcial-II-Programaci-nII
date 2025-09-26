public class Ataque {
    private String nombre;
    private int poder;

    public Ataque(String nombre, int poder) {
        this.nombre = nombre;
        this.poder = poder;
    }

    public String getNombre() {return nombre;}
    public void setNombre(String nombre) {this.nombre = nombre;}
    public int getPoder() {return poder;}
    public void setPoder(int poder) {this.poder = poder;}

    @Override
    public String toString() {
        return nombre + "(Poder: " + poder + ")";
    }
}
