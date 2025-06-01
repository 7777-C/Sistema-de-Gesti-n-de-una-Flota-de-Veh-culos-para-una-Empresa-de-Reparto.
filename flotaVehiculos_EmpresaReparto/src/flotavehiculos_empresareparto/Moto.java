package flotaVehiculos_EmpresaReparto;
/**
 *
 * @author Derick Vargas
 */
import java.time.LocalDate;

/**
 * Clase que representa una motocicleta en la flota.
 * Extiende de Vehiculo y añade la característica específica de cilindrada.
 */
public class Moto extends Vehiculo {
    private int cilindrada;

    public Moto(String placa, double capacidadCarga, double consumoCombustible, 
                LocalDate fechaUltimoMantenimiento, int cilindrada) {
        super(placa, capacidadCarga, consumoCombustible, fechaUltimoMantenimiento);
        this.cilindrada = cilindrada;
    }

    /**
     * Calcula el costo del viaje para una moto
     * @param distancia Distancia del viaje en kilómetros
     * @param precioCombustible Precio actual del combustible por litro
     * @return Costo base del viaje sin mantenimiento
     */
    @Override
    public double calcularCosto(double distancia, double precioCombustible) {
        return distancia * getConsumoCombustible() * precioCombustible;
    }

    public int getCilindrada() { return cilindrada; }
}
