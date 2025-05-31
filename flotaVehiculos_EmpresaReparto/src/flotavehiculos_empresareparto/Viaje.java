package flotaVehiculos_EmpresaReparto;

/**
 *
 * @author LenovoCoreMK
 */
import java.time.LocalDate;

/**
 * Clase que representa un viaje realizado por un vehículo.
 * Almacena la información del viaje y calcula su costo total.
 */
public class Viaje {
    private double distancia;
    private LocalDate fecha;
    private Vehiculo vehiculo;
    private double costoTotal;

    /**
     * Constructor que inicializa un nuevo viaje y calcula su costo
     * @param vehiculo Vehículo que realiza el viaje
     * @param distancia Distancia del viaje en kilómetros
     * @param fecha Fecha en que se realiza el viaje
     * @param precioCombustible Precio actual del combustible por litro
     */
    public Viaje(Vehiculo vehiculo, double distancia, LocalDate fecha, double precioCombustible) {
        this.vehiculo = vehiculo;
        this.distancia = distancia;
        this.fecha = fecha;
        this.costoTotal = vehiculo.calcularCostoOperativo(distancia, precioCombustible);
    }

    // Getters
    public double getDistancia() { return distancia; }
    public LocalDate getFecha() { return fecha; }
    public Vehiculo getVehiculo() { return vehiculo; }
    public double getCostoTotal() { return costoTotal; }
}
