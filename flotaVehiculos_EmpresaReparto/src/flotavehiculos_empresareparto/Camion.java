package flotaVehiculos_EmpresaReparto;
/**
 *
 * @author Derick Vargas
 */
import java.time.LocalDate;

/**
 * Clase que representa un camión en la flota.
 * Extiende de Vehiculo y añade la característica de número de ejes.
 */
public class Camion extends Vehiculo {
    private int numeroEjes;

    public Camion(String placa, double capacidadCarga, double consumoCombustible, 
                  LocalDate fechaUltimoMantenimiento, int numeroEjes) {
        super(placa, capacidadCarga, consumoCombustible, fechaUltimoMantenimiento);
        this.numeroEjes = numeroEjes;
    }

    /**
     * Calcula el costo del viaje para un camión
     * @param distancia Distancia del viaje en kilómetros
     * @param precioCombustible Precio actual del combustible por litro
     * @return Costo base del viaje sin mantenimiento
     */
    @Override
    public double calcularCosto(double distancia, double precioCombustible) {
        return distancia * getConsumoCombustible() * precioCombustible;
    }

    public int getNumeroEjes() { return numeroEjes; }
}
