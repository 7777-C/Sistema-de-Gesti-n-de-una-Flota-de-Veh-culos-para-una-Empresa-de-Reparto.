package flotaVehiculos_EmpresaReparto;

/**
 *
 * @author Ethan Soto
 */
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * Clase base abstracta para todos los tipos de vehículos.
 * Implementa la interfaz CostoCalculable y define la estructura común
 * para todos los vehículos de la flota.
 */
public abstract class Vehiculo implements CostoCalculable {
    private String placa;
    private double capacidadCarga;
    private double consumoCombustible;
    private LocalDate fechaUltimoMantenimiento;
    protected static final double COSTO_MANTENIMIENTO_BASE = 50.0;

    /**
     * Constructor base para todos los vehículos
     * @param placa Identificador único del vehículo
     * @param capacidadCarga Capacidad máxima de carga en kg
     * @param consumoCombustible Consumo de combustible en l/km
     * @param fechaUltimoMantenimiento Fecha del último mantenimiento realizado
     */
    public Vehiculo(String placa, double capacidadCarga, double consumoCombustible, LocalDate fechaUltimoMantenimiento) {
        this.placa = placa;
        this.capacidadCarga = capacidadCarga;
        this.consumoCombustible = consumoCombustible;
        this.fechaUltimoMantenimiento = fechaUltimoMantenimiento;
    }

    /**
     * Verifica si el vehículo necesita mantenimiento
     * @return true si han pasado 6 o más meses desde el último mantenimiento
     */
    public boolean necesitaMantenimiento() {
        return ChronoUnit.MONTHS.between(fechaUltimoMantenimiento, LocalDate.now()) >= 6;
    }

    /**
     * Calcula el costo total de operación incluyendo mantenimiento
     * @param distancia Distancia del viaje en kilómetros
     * @param precioCombustible Precio actual del combustible por litro
     * @return Costo total incluyendo combustible y mantenimiento
     */
    public double calcularCostoOperativo(double distancia, double precioCombustible) {
        return calcularCosto(distancia, precioCombustible) + COSTO_MANTENIMIENTO_BASE;
    }

    // Getters y Setters
    public String getPlaca() { return placa; }
    public double getCapacidadCarga() { return capacidadCarga; }
    public double getConsumoCombustible() { return consumoCombustible; }
    public LocalDate getFechaUltimoMantenimiento() { return fechaUltimoMantenimiento; }
    public void setFechaUltimoMantenimiento(LocalDate fecha) { this.fechaUltimoMantenimiento = fecha; }
}
