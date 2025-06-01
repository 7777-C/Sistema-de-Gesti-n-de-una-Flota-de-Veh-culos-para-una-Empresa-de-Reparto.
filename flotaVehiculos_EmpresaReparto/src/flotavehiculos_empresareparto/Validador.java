package flotaVehiculos_EmpresaReparto;

/**
 *
 * @author Derick Vargas
 */
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Clase utilitaria que proporciona métodos de validación
 * para diferentes aspectos del sistema.
 */
public class Validador {
    /**
     * Valida el formato de la placa del vehículo
     * @param placa Placa a validar
     * @return true si la placa tiene 6 caracteres alfanuméricos
     */
    public static boolean validarPlaca(String placa) {
        return placa != null && placa.matches("^[A-Z0-9]{6}$");
    }

    /**
     * Valida el formato de una fecha
     * @param fecha Fecha en formato YYYY-MM-DD
     * @return true si la fecha tiene el formato correcto
     */
    public static boolean validarFecha(String fecha) {
        try {
            LocalDate.parse(fecha);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    /**
     * Valida que un valor numérico sea positivo
     * @param valor Valor a validar
     * @return true si el valor es mayor que cero
     */
    public static boolean validarValoresPositivos(double valor) {
        return valor > 0;
    }
}
