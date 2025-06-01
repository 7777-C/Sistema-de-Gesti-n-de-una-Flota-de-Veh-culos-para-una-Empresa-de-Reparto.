package flotaVehiculos_EmpresaReparto;

/**
 *
 * @author Joseph Aguilar
 */
import java.util.Scanner;

/**
 * Clase principal que inicia la aplicación.
 * Crea el menú y mantiene el ciclo principal del programa.
 */
public class Main {
    /**
     * Punto de entrada principal de la aplicación
     * @param args Argumentos de línea de comando (no utilizados)
     */
    public static void main(String[] args) {
        Menu menu = new Menu();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            menu.mostrarMenu();
            int opcion = scanner.nextInt();
            menu.procesarOpcion(opcion);
        }
    }
}
