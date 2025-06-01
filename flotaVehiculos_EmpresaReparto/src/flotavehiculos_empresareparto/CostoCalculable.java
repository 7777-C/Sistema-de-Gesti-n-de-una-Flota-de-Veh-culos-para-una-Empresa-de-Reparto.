package flotaVehiculos_EmpresaReparto;

/**
 *
 * @author Ethan Soto
 */

/**
 * Interfaz que define el comportamiento para calcular costos de viajes.
 * Implementada por la clase Vehiculo y heredada por sus subclases.
 */
public interface CostoCalculable {
    /**
     * Calcula el costo base de un viaje sin incluir el mantenimiento.
     * @param distancia Distancia del viaje en kilómetros
     * @param precioCombustible Precio actual del combustible por litro
     * @return Costo del viaje en la moneda actual
     */
    double calcularCosto(double distancia, double precioCombustible);
}
