package flotaVehiculos_EmpresaReparto;
/**
 *
 * @author Derick Vargas
 */
import java.time.LocalDate;

/**
 * Clase que representa una camioneta en la flota.
 * Extiende de Vehiculo y añade la característica de tracción 4x4.
 * Los vehículos con tracción 4x4 tienen un 20% adicional en el costo.
 */
public class Camioneta extends Vehiculo {
    private boolean traccion4x4;
    private static final double INCREMENTO_4X4 = 1.2; // 20% más de costo si es 4x4

    public Camioneta(String placa, double capacidadCarga, double consumoCombustible, 
                     LocalDate fechaUltimoMantenimiento, boolean traccion4x4) {
        super(placa, capacidadCarga, consumoCombustible, fechaUltimoMantenimiento);
        this.traccion4x4 = traccion4x4;
    }

    /**
     * Calcula el costo del viaje para una camioneta
     * @param distancia Distancia del viaje en kilómetros
     * @param precioCombustible Precio actual del combustible por litro
     * @return Costo base del viaje sin mantenimiento, incrementado en 20% si es 4x4
     */
    @Override
    public double calcularCosto(double distancia, double precioCombustible) {
        double costoBase = distancia * getConsumoCombustible() * precioCombustible;
        return traccion4x4 ? costoBase * INCREMENTO_4X4 : costoBase;
    }

    public boolean isTraccion4x4() { return traccion4x4; }
}
