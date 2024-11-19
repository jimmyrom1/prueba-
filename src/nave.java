public class nave {
    private String nombre;
    private static int vida;
    private int posicion;
    private int turnosPerdidos;
    private int velocidad;
    public static int Ataque;
    nave(String nombre, int vida, int Ataque){
        this.nombre = nombre;
        this.posicion = 1;
        this.turnosPerdidos = 0;
        this.vida = vida;
        this.Ataque = Ataque;
        this.velocidad = 0;
    }
    public String getNombre(){
        return nombre;
    }
    public int getVida(){
        return vida;
    }
    public int getVelocidad(){
        return velocidad;
    }
    public int getAtaque(){
        return Ataque;
    }
    public int getPosicion(){
        return posicion;
    }
    public void setPosicion(int nuevaPosicion){
        this.posicion=nuevaPosicion;
    }

    public void setTurnosPerdidos(int turnos){
        this.turnosPerdidos=turnos;
    }
    public void setVelocidad(int velocidad){
        this.velocidad=velocidad;
    }
    public void recibirdanio(int cantidad){
        this.vida-=cantidad;
        System.out.println(nombre+"recibe"+cantidad+"puntos de daño");
    }
    public boolean estaVivo(){
        return vida>0;
    }
    public void mover(int cantidad){
        this.posicion+=cantidad;
    }
    public static void aumentarVida(int cantidad){
        vida+=cantidad;
    }
    public static void aumentarAtaque(int cantidad) {
        Ataque += cantidad;}
    public int getTurnosPerdidos() {
        return turnosPerdidos;}
}
