package activitat1_7;

public class Test {

    private static Buffer buffer;
    private static Thread[] consumidor;
    private static Thread[] productores;
    private static final int PRODUCTORS = 5;
    private static final int CONSUMIDORS = 5;

    /**
     * Metode principal (main) que crea els fils per començar a fer tots els
     * calculs.
     *
     * @param args
     */
    public static void main(String[] args) {
        buffer = new Buffer();

        consumidor = new Thread[CONSUMIDORS];
        productores = new Thread[PRODUCTORS];
        for (int i = 0; i < PRODUCTORS; i++) {
            productores[i] = new Thread(new Productor(buffer, i));
            productores[i].start();
        }
        for (int j = 0; j < CONSUMIDORS; j++) {
            consumidor[j] = new Thread(new Consumidor(buffer, j));
            consumidor[j].start();
        }

    }

}
