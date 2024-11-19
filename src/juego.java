import java.util.Random;
import java.util.Scanner;

public class juego {
    static Scanner scn = new Scanner(System.in);
    static Random R = new Random();

    public static void main(String[] args) {

        menu(scn, R);
    }

    private static void menu(Scanner scn, Random R) {
        while (true) {
            System.out.println("\n--- Menú del Juego de la Oca Galagtica ---");
            System.out.println("1. Jugar");
            System.out.println("2. Instrucciones");
            System.out.println("3. Salir");
            System.out.print("Elige una opción: ");
            int opcion = scn.nextInt();
            switch (opcion) {
                case 1:
                    jugar(scn, R);
                    break;
                case 2:
                    mostrarInstrucciones();
                    break;
                case 3:
                    System.out.println("Gracias por jugar. ¡Hasta luego!");
                    return; //
                default:
                    System.out.println("Opción no válida, por favor elige nuevamente.");
            }


        }
    }

    private static void jugar(Scanner scn,Random R){
        nave[] naves={
                new nave("Millenium Falcom  ",120,15),
                new nave("USS Enterprise  ",100,20),
                new nave("Planet Express  ",90,25),
                new nave("Battlestar Galactica  ",130,10)
        };
        System.out.println("Elige tu nave espacial: ");
        for (int i =0;i< naves.length;i++){
            System.out.println(i+1+". "+naves[i].getNombre()+"vida: "+naves[i].getVida()+" y Ataque; "+naves[i].getAtaque());
        }
        int seleccion= scn.nextInt()-1;
        nave nave1=naves[seleccion];
        System.out.println("has elegido a:"+nave1.getNombre());

        nave nave2;
        do{
            nave2=naves[R.nextInt(naves.length)];
        }while(nave2==nave1);
        System.out.println("Tu oponente será"+nave2.getNombre());
        evento[]tablero=new evento[64];
        boolean[] ocupada=new boolean[64];
        for (int i=0;i< tablero.length;i++){
            tablero[i]=new evento("normal",0,0);
        }
        String[]eventos={ "Agujero de gusano", "pelea", "Cinturón de asteroides"};
        for (int i=0;i<64;i++){
            if (R.nextInt(10) < 9 && !ocupada[i]) { // 90% de probabilidad para eventos especiales
                String tipoEvento = eventos[R.nextInt(eventos.length)];
                tablero[i] = new evento(tipoEvento, R.nextInt(10), R.nextInt(10));
                ocupada[i] = true;
            }
        }

        while (nave1.getPosicion() < 64 && nave2.getPosicion() < 64) {
            // Turno del jugador
            if (nave1.getTurnosPerdidos() > 0) {
                System.out.println(nave1.getNombre() + " está perdiendo un turno.");
                nave1.setTurnosPerdidos(nave1.getTurnosPerdidos() - 1);
            } else {
                System.out.println(nave1.getNombre() + " está en la posición " + nave1.getPosicion() + ".");
                System.out.print("Lanza el dado (presiona Enter para continuar)... ");
                scn.nextLine();
                int dado = R.nextInt(6) + 1; // Dado de 6 caras
                System.out.println("Tiraste un " + dado + "!");
                nave1.mover(dado);
                nave1.setPosicion(tablero[nave1.getPosicion() - 1].aplicarEvento(nave1.getPosicion(), nave1, nave2));
                if (nave1.getPosicion() == -1) {
                    nave1.setTurnosPerdidos(1);
                }
            }


            if (nave2.getTurnosPerdidos() > 0) {
                System.out.println(nave2.getNombre() + " está perdiendo un turno.");
                nave2.setTurnosPerdidos(nave2.getTurnosPerdidos() - 1);
            } else {
                System.out.println(nave2.getNombre() + " está en la posición " + nave2.getPosicion() + ".");
                int dado = R.nextInt(6) + 1; // Dado de 6 caras
                System.out.println(nave2.getNombre() + " tiró un " + dado + "!");
                nave2.mover(dado);
                nave2.setPosicion(tablero[nave2.getPosicion() - 1].aplicarEvento(nave2.getPosicion(), nave2, nave1));
                if (nave2.getPosicion() == -1) {
                    nave2.setTurnosPerdidos(1);
                }
            }

            if (nave1.getPosicion() >= 64) {
                System.out.println(nave1.getNombre() + " ha llegado a la meta. ¡Has ganado!");
                break;
            } else if (nave2.getPosicion() >= 64) {
                System.out.println(nave2.getNombre() + " ha llegado a la meta. ¡Has perdido!");
                break;
            }
        }
    }

    private static void mostrarInstrucciones() {
        System.out.println("Instrucciones del Juego de la Oca galagtica :");
        System.out.println("Cada jugador elige una nave espacial y avanza en un tablero de 64 casillas.");
        System.out.println("Lanza el dado y avanza el número de casillas indicado.");
        System.out.println("Caer en ciertos eventos puede causar peleas o pérdida de turnos.");
        System.out.println("El primero en llegar a la casilla 64 gana.");
        }
    }




