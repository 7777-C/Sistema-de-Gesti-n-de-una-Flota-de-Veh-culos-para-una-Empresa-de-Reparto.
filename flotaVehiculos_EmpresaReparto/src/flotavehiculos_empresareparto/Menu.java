package flotaVehiculos_EmpresaReparto;

/**
 *
 * @author Joseph
 */
import java.util.List;
import java.util.Scanner;
import java.time.LocalDate;

/**
 * Clase que maneja la interfaz de usuario en consola para la gestión de la flota de vehículos.
 * Esta clase implementa el patrón de diseño Singleton para el GestorFlota y proporciona
 * métodos para registrar vehículos, viajes, actualizar mantenimientos y listar la flota.
 * 
 * La clase gestiona la entrada del usuario con validaciones para evitar datos incorrectos
 * y maneja diferentes tipos de vehículos (Moto, Camioneta, Camión) de manera polimórfica.
 */
public class Menu {
    private Scanner scanner;
    private GestorFlota gestor;

    /**
     * Constructor que inicializa el scanner para entrada de usuario y
     * obtiene la instancia única del GestorFlota.
     */
    public Menu() {
        scanner = new Scanner(System.in);
        gestor = GestorFlota.getInstance();
    }

    /**
     * Muestra el menú principal con las opciones disponibles del sistema.
     * Las opciones incluyen: registrar vehículo, registrar viaje,
     * actualizar mantenimiento, listar vehículos y salir.
     */
    public void mostrarMenu() {
        System.out.println("=== Gestion de Flota de Reparto ===");
        System.out.println("1. Registrar vehiculo");
        System.out.println("2. Registrar viaje");
        System.out.println("3. Actualizar mantenimiento");
        System.out.println("4. Listar vehiculos");
        System.out.println("5. Salir");
        System.out.print("Seleccione una opcion: ");
    }

    /**
     * Procesa la opción seleccionada por el usuario y ejecuta la operación correspondiente.
     * 
     * @param opcion número entero que representa la opción seleccionada (1-5)
     */
    public void procesarOpcion(int opcion) {
        switch (opcion) {
            case 1:
                registrarVehiculo();
                break;
            case 2:
                registrarViaje();
                break;
            case 3:
                actualizarMantenimiento();
                break;
            case 4:
                listarVehiculos();
                break;
            case 5:
                System.out.println("Saliendo del sistema...");
                System.exit(0);
                break;
            default:
                System.out.println("Opcion no válida");
        }
    }

    /**
     * Solicita y valida la fecha del último mantenimiento de un vehículo.
     * Permite al usuario indicar si el mantenimiento fue realizado hoy o
     * ingresar una fecha específica en formato YYYY-MM-DD.
     * 
     * @return LocalDate objeto que representa la fecha del último mantenimiento
     */
    private LocalDate obtenerFechaMantenimiento() {
        System.out.print("¿El ultimo mantenimiento fue hoy? (s/n): ");
        if (scanner.next().toLowerCase().equals("s")) {
            return LocalDate.now();
        }

        scanner.nextLine(); // Limpiar buffer
        while (true) {
            System.out.print("Ingrese la fecha del ultimo mantenimiento (YYYY-MM-DD): ");
            String fecha = scanner.nextLine();
            if (Validador.validarFecha(fecha)) {
                return LocalDate.parse(fecha);
            }
            System.out.println("Formato de fecha invalido. Use YYYY-MM-DD");
        }
    }

    /**
     * Registra un nuevo vehículo en el sistema. Solicita y valida todos los datos
     * necesarios según el tipo de vehículo (Moto, Camioneta o Camión).
     * 
     * Validaciones realizadas:
     * - Formato de placa
     * - Valores positivos para capacidad y consumo
     * - Fecha de último mantenimiento
     * - Datos específicos según tipo de vehículo
     */
    private void registrarVehiculo() {
        System.out.println("\nTipo de vehiculo (1: Moto, 2: Camioneta, 3: Camion): ");
        int tipo = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer

        System.out.print("Placa: ");
        String placa = scanner.nextLine();
        if (!Validador.validarPlaca(placa)) {
            System.out.println("Error: Formato de placa invalido");
            return;
        }

        System.out.print("Capacidad (kg): ");
        double capacidad = scanner.nextDouble();
        if (!Validador.validarValoresPositivos(capacidad)) {
            System.out.println("Error: La capacidad debe ser positiva");
            return;
        }

        System.out.print("Consumo (l/km): ");
        double consumo = scanner.nextDouble();
        if (!Validador.validarValoresPositivos(consumo)) {
            System.out.println("Error: El consumo debe ser positivo");
            return;
        }

        LocalDate fechaMantenimiento = obtenerFechaMantenimiento();

        switch (tipo) {
            case 1: // Moto
                System.out.print("Cilindrada (cc): ");
                int cilindrada = scanner.nextInt();
                gestor.registrarVehiculo(new Moto(placa, capacidad, consumo, fechaMantenimiento, cilindrada));
                break;

            case 2: // Camioneta
                System.out.print("¿Tiene traccion 4x4? (s/n): ");
                boolean traccion4x4 = scanner.next().toLowerCase().equals("s");
                gestor.registrarVehiculo(new Camioneta(placa, capacidad, consumo, fechaMantenimiento, traccion4x4));
                break;

            case 3: // Camión
                System.out.print("Numero de ejes: ");
                int ejes = scanner.nextInt();
                gestor.registrarVehiculo(new Camion(placa, capacidad, consumo, fechaMantenimiento, ejes));
                break;

            default:
                System.out.println("Tipo de vehiculo no válido");
                return;
        }
        System.out.println("Vehiculo " + placa + " registrado exitosamente.");
    }

    /**
     * Registra un nuevo viaje para un vehículo existente. Calcula y muestra el costo
     * total del viaje considerando el consumo de combustible y costos de mantenimiento.
     * 
     * Características:
     * - Usa un Scanner independiente para evitar problemas de buffer
     * - Acepta números decimales con punto o coma
     * - Valida la existencia del vehículo y valores positivos
     * - Muestra alertas de mantenimiento si corresponde
     * - Maneja excepciones para entrada inválida
     */
    private void registrarViaje() {
        // Crear un nuevo scanner para esta operación
        Scanner inputScanner = new Scanner(System.in);
        
        try {
            System.out.print("Ingrese la placa del vehiculo: ");
            String placa = inputScanner.nextLine().trim();

            Vehiculo vehiculo = gestor.buscarVehiculo(placa);
            if (vehiculo == null) {
                System.out.println("Error: Vehiculo no encontrado");
                return;
            }

            System.out.print("Distancia (km): ");
            String distanciaStr = inputScanner.nextLine().trim();
            double distancia;
            try {
                distancia = Double.parseDouble(distanciaStr.replace(',', '.'));
            } catch (NumberFormatException e) {
                System.out.println("Error: Ingrese un numero valido para la distancia");
                return;
            }

            if (!Validador.validarValoresPositivos(distancia)) {
                System.out.println("Error: La distancia no puede ser negativa");
                return;
            }

            if (vehiculo.necesitaMantenimiento()) {
                System.out.println("¡Alerta! Mantenimiento pendiente desde " + 
                                  vehiculo.getFechaUltimoMantenimiento());
            }

            Viaje viaje = new Viaje(vehiculo, distancia, LocalDate.now(), GestorFlota.getPrecioCombustible());
            System.out.printf("Costo del viaje: $%.2f%n", viaje.getCostoTotal());
            System.out.printf("Combustible: %.2f litros/km%n", vehiculo.getConsumoCombustible());
            System.out.printf("Mantenimiento: $%.2f%n", Vehiculo.COSTO_MANTENIMIENTO_BASE);
        } catch (Exception e) {
            System.out.println("Error al registrar el viaje: " + e.getMessage());
        }
    }

    /**
     * Actualiza la fecha de último mantenimiento de un vehículo a la fecha actual.
     * Verifica la existencia del vehículo por su placa antes de la actualización.
     */
    private void actualizarMantenimiento() {
        System.out.print("Ingrese la placa del vehiculo: ");
        scanner.nextLine(); // Limpiar buffer
        String placa = scanner.nextLine();

        Vehiculo vehiculo = gestor.buscarVehiculo(placa);
        if (vehiculo == null) {
            System.out.println("Error: Vehiculo no encontrado");
            return;
        }

        vehiculo.setFechaUltimoMantenimiento(LocalDate.now());
        System.out.println("Mantenimiento actualizado para el vehiculo " + placa);
    }

    /**
     * Muestra una lista detallada de todos los vehículos registrados en el sistema.
     * Para cada vehículo muestra:
     * - Información general (placa, tipo, capacidad, consumo)
     * - Atributos específicos según el tipo de vehículo
     * - Estado del mantenimiento
     * 
     * Si no hay vehículos registrados, muestra un mensaje apropiado.
     */
    private void listarVehiculos() {
        List<Vehiculo> vehiculos = gestor.listarVehiculos();
        if (vehiculos.isEmpty()) {
            System.out.println("No hay vehiculos registrados");
            return;
        }

        System.out.println("\nLista de Vehiculos:");
        System.out.println("===================");

        for (Vehiculo v : vehiculos) {
            System.out.println("\nPlaca: " + v.getPlaca());
            System.out.println("Tipo: " + v.getClass().getSimpleName());
            System.out.println("Capacidad: " + v.getCapacidadCarga() + " kg");
            System.out.println("Consumo: " + v.getConsumoCombustible() + " l/km");
            System.out.println("Ultimo mantenimiento: " + v.getFechaUltimoMantenimiento());

            if (v instanceof Moto) {
                System.out.println("Cilindrada: " + ((Moto) v).getCilindrada() + " cc");
            } else if (v instanceof Camioneta) {
                System.out.println("4x4: " + (((Camioneta) v).isTraccion4x4() ? "Si" : "No"));
            } else if (v instanceof Camion) {
                System.out.println("Numero de ejes: " + ((Camion) v).getNumeroEjes());
            }

            if (v.necesitaMantenimiento()) {
                System.out.println("¡ALERTA! Mantenimiento vencido");
            }
            System.out.println("-------------------");
        }
    }
}
