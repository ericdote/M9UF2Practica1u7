package activitat1_7;

import java.util.Random;

public class Productor implements Runnable {

    private final Buffer buffer;
    private final Random rand;
    private final int wait = 1000;

    /**
     * Constructor de la clase productor.
     *
     * @param contenedor
     * @param productor
     */
    public Productor(Buffer contenedor, int productor) {
        rand = new Random();
        this.buffer = contenedor;
    }

    /**
     * Implementació del run() on es crida al metode retirar.
     */
    @Override
    public void run() {
        while (Boolean.TRUE) {

            buffer.retirar();
            try {
                Thread.sleep(wait);
            } catch (InterruptedException e) {
                System.err.println("Error en run (productor) ---> " + e.getMessage());
            }
        }
    }
}
