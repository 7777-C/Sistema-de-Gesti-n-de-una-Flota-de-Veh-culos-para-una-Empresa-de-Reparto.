package flotaVehiculos_EmpresaReparto;

/**
 *
 * @author Mark Gonzalez
 */
import java.util.ArrayList;
import java.util.List;

/**
 * Clase principal que gestiona la flota de vehículos.
 * Implementa el patrón Singleton para asegurar una única instancia.
 * Mantiene el registro de vehículos y el precio global del combustible.
 */
public class GestorFlota {
    private static GestorFlota instancia;
    private List<Vehiculo> listaVehiculos;
    private static double precioCombustible;

    /**
     * Constructor privado para el patrón Singleton
     * Inicializa la lista de vehículos y establece el precio del combustible
     */
    private GestorFlota() {
        listaVehiculos = new ArrayList<>();
        precioCombustible = 1.5; // Valor por defecto
    }

    /**
     * Obtiene la única instancia de GestorFlota
     * @return Instancia única de GestorFlota
     */
    public static GestorFlota getInstance() {
        if (instancia == null) {
            instancia = new GestorFlota();
        }
        return instancia;
    }

    public void registrarVehiculo(Vehiculo vehiculo) {
        listaVehiculos.add(vehiculo);
    }

    public List<Vehiculo> listarVehiculos() {
        return new ArrayList<>(listaVehiculos);
    }

    public Vehiculo buscarVehiculo(String placa) {
        return listaVehiculos.stream()
                .filter(v -> v.getPlaca().equals(placa))
                .findFirst()
                .orElse(null);
    }

    public static void setPrecioCombustible(double precio) {
        precioCombustible = precio;
    }

    public static double getPrecioCombustible() {
        return precioCombustible;
    }
}
