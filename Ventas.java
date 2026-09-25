import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Ventas {

    private List<List<Double>> matrizVentas;
    private String[] meses = {
        "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio",
        "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"
    };
    private List<String> departamentos;

    public Ventas() {
        this.departamentos = new ArrayList<>();
        this.departamentos.add("Ropa");
        this.departamentos.add("Deportes");
        this.departamentos.add("Juguetería");

        this.matrizVentas = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            this.matrizVentas.add(new ArrayList<>());
        }
        generarVentasAleatorias();
    }

    public void generarVentasAleatorias() {
        Random random = new Random();
        for (int i = 0; i < 12; i++) {
            this.matrizVentas.get(i).clear();
            for (int j = 0; j < departamentos.size(); j++) {
                double monto = 1000 + (20000 - 1000) * random.nextDouble();
                this.matrizVentas.get(i).add(Math.round(monto * 100.0) / 100.0);
            }
        }
    }

    public void agregarDepartamento(String nombreDepto) {
        departamentos.add(nombreDepto);
        for (int i = 0; i < 12; i++) {
            matrizVentas.get(i).add(0.0);
        }
        System.out.println("\n ¡Departamento '" + nombreDepto + "' agregado a la lista!");
    }

    public void insertarVenta(int mesNum, int deptoNum, double monto) {
        if (mesNum >= 1 && mesNum <= 12 && deptoNum >= 1 && deptoNum <= departamentos.size()) {
            matrizVentas.get(mesNum - 1).set(deptoNum - 1, monto);
            System.out.printf("\n Venta de $%.2f registrada en %s (%s).\n", 
                monto, departamentos.get(deptoNum - 1), meses[mesNum - 1]);
        } else {
            System.out.println("\n Error: Número de mes o departamento inválido.");
        }
    }

    public void insertarVentaSilenciosa(int mesNum, int deptoNum, double monto) {
        if (mesNum >= 1 && mesNum <= 12 && deptoNum >= 1 && deptoNum <= departamentos.size()) {
            matrizVentas.get(mesNum - 1).set(deptoNum - 1, monto);
        }
    }

    public double buscarVenta(int mesNum, int deptoNum) {
        if (mesNum >= 1 && mesNum <= 12 && deptoNum >= 1 && deptoNum <= departamentos.size()) {
            double monto = matrizVentas.get(mesNum - 1).get(deptoNum - 1);
            System.out.printf("\n Venta en %s (%s): $%.2f\n", 
                departamentos.get(deptoNum - 1), meses[mesNum - 1], monto);
            return monto;
        } else {
            System.out.println("\n Error: Número de mes o departamento inválido.");
            return -1;
        }
    }

    public void eliminarVenta(int mesNum, int deptoNum) {
        if (mesNum >= 1 && mesNum <= 12 && deptoNum >= 1 && deptoNum <= departamentos.size()) {
            matrizVentas.get(mesNum - 1).set(deptoNum - 1, 0.0);
            System.out.printf("\n️ Venta eliminada (reiniciada a $0.00) en %s (%s).\n", 
                departamentos.get(deptoNum - 1), meses[mesNum - 1]);
        } else {
            System.out.println("\n Error: Número de mes o departamento inválido.");
        }
    }

    public void mostrarTabla() {
        System.out.println("\n------------------- TABLA DE VENTAS -------------------");
        System.out.printf("%-12s | ", "Mes");
        for (String dept : departamentos) {
            System.out.printf("%-12s | ", dept);
        }
        System.out.println("\n--------------------------------------------------------");

        double[] totalesAnuales = new double[departamentos.size()];

        for (int i = 0; i < 12; i++) {
            System.out.printf("%-12s | ", meses[i]);
            for (int j = 0; j < departamentos.size(); j++) {
                double venta = matrizVentas.get(i).get(j);
                totalesAnuales[j] += venta;
                System.out.printf("$%-11.2f | ", venta);
            }
            System.out.println();
        }

        System.out.println("--------------------------------------------------------");
        System.out.printf("%-12s | ", "TOTAL ANUAL");
        for (int j = 0; j < departamentos.size(); j++) {
            System.out.printf("$%-11.2f | ", totalesAnuales[j]);
        }
        System.out.println("\n--------------------------------------------------------");
    }

    public void mostrarDepartamentos() {
        System.out.println("\nDepartamentos disponibles:");
        for (int i = 0; i < departamentos.size(); i++) {
            System.out.println((i + 1) + ". " + departamentos.get(i));
        }
    }

    public int getCantidadDepartamentos() {
        return departamentos.size();
    }

    public String getNombreMes(int mesNum) {
        return meses[mesNum - 1];
    }

    public static void main(String[] args) {
        // Mide el tiempo de inicialización del programa y carga del primer menú
        long inicioArranque = System.currentTimeMillis();

        Ventas sistema = new Ventas();
        Scanner scanner = new Scanner(System.in);
        int opcion = 0;

        long finArranque = System.currentTimeMillis();
        long msArranque = finArranque - inicioArranque;
        System.out.printf("Tiempo de carga e inicialización del programa: %.3f segundos (%d ms)\n", 
                          msArranque / 1000.0, msArranque);

        while (opcion != 7) {
            System.out.println("\n====================================");
            System.out.println("    SISTEMA DE CONTROL DE VENTAS    ");
            System.out.println("====================================");
            System.out.println("1. Ver tabla de ventas");
            System.out.println("2. Insertar / Modificar venta");
            System.out.println("3. Buscar venta");
            System.out.println("4. Eliminar venta");
            System.out.println("5. Agregar nuevo departamento");
            System.out.println("6. Regenerar datos aleatorios");
            System.out.println("7. Salir");
            System.out.print("Selecciona una opción (1-7): ");

            if (scanner.hasNextInt()) {
                opcion = scanner.nextInt();
                scanner.nextLine(); // Limpieza del buffer
            } else {
                scanner.nextLine();
                System.out.println("\n Opción no válida.");
                continue;
            }

            // --- INICIO DE MEDICIÓN DE TIEMPO DE LA OPCIÓN ---
            long inicioTiempo = System.currentTimeMillis();

            if (opcion == 1) {
                sistema.mostrarTabla();
            } else if (opcion == 2) {
                System.out.print("Ingrese número de mes (1-12): ");
                int m = scanner.nextInt();
                sistema.mostrarDepartamentos();
                System.out.print("Ingrese número de departamento: ");
                int d = scanner.nextInt();
                System.out.print("Ingrese el monto: ");
                double monto = scanner.nextDouble();
                scanner.nextLine();
                sistema.insertarVenta(m, d, monto);
            } else if (opcion == 3) {
                System.out.print("Ingrese número de mes (1-12): ");
                int m = scanner.nextInt();
                sistema.mostrarDepartamentos();
                System.out.print("Ingrese número de departamento: ");
                int d = scanner.nextInt();
                scanner.nextLine();
                sistema.buscarVenta(m, d);
            } else if (opcion == 4) {
                System.out.print("Ingrese número de mes (1-12): ");
                int m = scanner.nextInt();
                sistema.mostrarDepartamentos();
                System.out.print("Ingrese número de departamento: ");
                int d = scanner.nextInt();
                scanner.nextLine();
                sistema.eliminarVenta(m, d);
            } else if (opcion == 5) {
                System.out.print("\nIngrese el nombre del nuevo departamento: ");
                String nombreDepto = scanner.nextLine();
                sistema.agregarDepartamento(nombreDepto);
                
                System.out.println("\n¿Qué desea hacer con los datos de venta de '" + nombreDepto + "'?");
                System.out.println("1. Mantener todas las ventas en $0.00");
                System.out.println("2. Ingresar los montos mes a mes manualmente");
                System.out.print("Seleccione una opción (1 o 2): ");
                
                int opcionLlenado = 1;
                if (scanner.hasNextInt()) {
                    opcionLlenado = scanner.nextInt();
                    scanner.nextLine();
                } else {
                    scanner.nextLine();
                }

                if (opcionLlenado == 2) {
                    int nuevoDeptoId = sistema.getCantidadDepartamentos();
                    System.out.println("\n--- Ingresando ventas para: " + nombreDepto + " ---");
                    
                    for (int m = 1; m <= 12; m++) {
                        System.out.print("Monto para " + sistema.getNombreMes(m) + ": $");
                        double monto = scanner.nextDouble();
                        sistema.insertarVentaSilenciosa(m, nuevoDeptoId, monto);
                    }
                    scanner.nextLine();
                    System.out.println("\n ¡Todas las ventas de '" + nombreDepto + "' han sido registradas exitosamente!");
                } else {
                    System.out.println("Las ventas de '" + nombreDepto + "' se han inicializado en $0.00.");
                }

            } else if (opcion == 6) {
                sistema.generarVentasAleatorias();
                System.out.println("\n Nuevas ventas aleatorias generadas.");
            } else if (opcion == 7) {
                System.out.println("\n¡Hasta luego!");
            }

            // --- FIN DE MEDICIÓN DE TIEMPO Y CÁLCULO ---
            long finTiempo = System.currentTimeMillis();
            long milisegundos = finTiempo - inicioTiempo;
            double segundos = milisegundos / 1000.0;

            if (opcion >= 1 && opcion <= 7) {
                System.out.printf("\n Tiempo transcurrido para procesar la opción %d: %.3f segundos (%d ms)\n", 
                                  opcion, segundos, milisegundos);
            }
        }
        scanner.close();
    }
}
