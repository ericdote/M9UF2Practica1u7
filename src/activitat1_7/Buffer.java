package activitat1_7;

public class Buffer {

    private int[] buffer = new int[10];
    int comptadorProductor = 0, comptadorConsumidor = 0;
    private boolean contenidorPle = Boolean.FALSE;

    /**
     * Metode que retira. En cas de poder retirar es queda en bucle esperant a
     * poder realitzar l'operacio, mentres sortira "Buffer ple".
     */
    public synchronized void retirar() {
        while (contenidorPle) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.err.println("Error en retirar ---> " + e.getMessage());
            }
        }
        contenidorPle = !contenidorPle;
        int posicio = comptadorProductor % 10;
        if (comptadorProductor >= comptadorConsumidor) {
            System.out.println("Buffer ple...");
        } else {
            buffer[posicio] = 0;
            if (comptadorConsumidor >= comptadorProductor + 1) {
                comptadorProductor++;
            }
        }
        System.out.print("Buffer [");
        for (int i = 0; i < buffer.length; i++) {
            System.out.print(buffer[i] + ",");
        }
        int posicioRetirar = posicio + 1;
        System.out.print("]" + "Posicio " + posicioRetirar + "\n");
        contenidorPle = !contenidorPle;
        notifyAll();
    }

    /**
     * Metode que li arriba per parametre un valor per realitzar un ingres
     * d'aqest. Si no es pot ingresar ficara un bucle amb el missatge "Buffer
     * Ple". En cas contrari s'agrega al array el numero.
     *
     * @param num
     */
    public synchronized void ingresar(int num) {
        while (contenidorPle) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.err.println("Error en ingresar -> " + e.getMessage());
            }
        }
        contenidorPle = !contenidorPle;
        int posicio = comptadorConsumidor % 10;
        if (comptadorConsumidor >= comptadorProductor + buffer.length) {
            System.out.println("Buffer ple...");
        } else {
            buffer[posicio] = num;
            comptadorConsumidor++;
        }
        System.out.print("Buffer [");
        for (int i = 0; i < buffer.length; i++) {
            System.out.print(buffer[i] + ",");
        }
        int posicioIngress = posicio + 1;
        System.out.print("]" + "Posicio: " + posicioIngress + "\n");
        contenidorPle = !contenidorPle;
        notifyAll();
    }
}
