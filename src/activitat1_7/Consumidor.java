package activitat1_7;

import java.util.Random;

public class Consumidor implements Runnable {

    private final Random rand;
    private final Buffer buffer;
    private final int wait = 1000;

    /**
     * Constructor de la clase Consumidor.
     *
     * @param contenedor
     * @param consumidor
     */
    public Consumidor(Buffer contenedor, int consumidor) {
        rand = new Random();
        this.buffer = contenedor;
    }

    /**
     * Implementació del run() on calculem un random i es crida al metode
     * ingresar.
     */
    @Override
    public void run() {
        while (Boolean.TRUE) {
            int ingessar = rand.nextInt(10);
            buffer.ingresar(ingessar);
            try {
                Thread.sleep(wait);
            } catch (InterruptedException e) {
                System.err.println("Error en el run(consumidor) ---> " + e.getMessage());
            }
        }
    }
}
