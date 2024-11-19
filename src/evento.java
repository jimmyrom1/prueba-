import java.util.Random;

public class evento {
    private String tipoEvento;
    private int mejoraAtaque;
    private int mejoraVida;
    public evento (String tipoEvento, int mejoraAtaque, int mejoraVida){
        this.tipoEvento=tipoEvento;
        this.mejoraAtaque=mejoraAtaque;
        this.mejoraVida=mejoraVida;
    }
    public String getTipoEvento(){
        return tipoEvento;
    }
    public void aplicarMejoras(nave naves){
        if (mejoraAtaque>0){
            nave.aumentarAtaque (mejoraAtaque);
            System.out.println(naves.getNombre()+"obtiene"+mejoraAtaque+"puntos de ataque extra");

        }
        if (mejoraVida>0){
            nave.aumentarVida(mejoraVida);
            System.out.println(naves.getNombre()+"obtiene"+mejoraVida+"puntos de vida extra ");
        }
    }
    public int aplicarEvento(int posicionActual,nave naves, nave otroNave) {
        aplicarMejoras(naves);
        switch (tipoEvento){

            case "Agujero de gusano":
                Random r=new Random();
                int nuevaPosicion=r.nextInt(63)+1;
                System.out.println("caiste en un agujero de gusano te desplazas a la posicion "+nuevaPosicion);
                return nuevaPosicion;
            case "pelea":
                System.out.println("Ha empezado una pelea espacial !!!");
                peleaEntreNave(naves,otroNave);
                return naves.getPosicion();
            case "Cinturón de asteroides":
                System.out.println("Has entrado en un cinturón de asteroides, pierdes un turno ");
                return -1;
            default:
                return posicionActual;

        }

    }
    public void peleaEntreNave(nave nave1, nave nave2){
        Random r=new Random();
        nave1.setVelocidad(r.nextInt(10)+1);
        nave2.setVelocidad(r.nextInt(10)+1);
        System.out.println("pelea entre"+nave1.getNombre()+"y"+nave2.getNombre()+"!");
        System.out.println(nave1.getNombre() + " tiene " + nave1.getVelocidad() + " de velocidad.");
        System.out.println(nave2.getNombre() + " tiene " + nave2.getVelocidad() + " de velocidad.");
        nave primero,segundo;
        if (nave1.getVelocidad()>nave2.getVelocidad()){
            primero=nave1;
            segundo=nave2;
        }else {
            primero=nave2;
            segundo=nave1;
        }
        System.out.println(primero.getNombre()+"ataca primero por su velocidad ");
        segundo.recibirdanio(primero.Ataque);
        if (segundo.estaVivo()){
            System.out.println(segundo.getNombre()+"responde con su ataque");
            primero.recibirdanio(segundo.getAtaque());
        }
        System.out.println(primero.getNombre() + " tiene " + primero.getVida() + " puntos de vida.");
        System.out.println(segundo.getNombre() + " tiene " + segundo.getVida() + " puntos de vida.");
        if (!primero.estaVivo()&& !segundo.estaVivo()){
            System.out.println("Ambas naves han sido destruidas!!!");
        }else if (!segundo.estaVivo()){
            System.out.println(segundo.getNombre()+"ha sido derrotado");
            int nuevaPosicion=segundo.getPosicion()- 9;
            if (nuevaPosicion<1){
                nuevaPosicion=1;
            }
            primero.setPosicion(nuevaPosicion);
            System.out.println(primero.getNombre()+"retrocede 9 casillas");
        }else {
            System.out.println("ambas naves han sobrevivido");
        }


    }
    }

