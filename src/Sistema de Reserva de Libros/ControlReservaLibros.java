import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ControlReservaLibros {

    private List<ArticuloBibliografico> libros;
    private Scanner sc;
    private int anoActual;

    public ControlReservaLibros() {
        sc = new Scanner(System.in);
        libros = new ArrayList<>();
        anoActual = LocalDate.now().getYear();

        // 5 libros RESERVABLES (más de 5 años)
        libros.add(new ArticuloBibliografico(101, "Cien años de soledad", "Gabriel García Márquez", anoActual - 20));
        libros.add(new ArticuloBibliografico(102, "Don Quijote de la Mancha", "Miguel de Cervantes", anoActual - 50));
        libros.add(new ArticuloBibliografico(103, "El Hobbit", "J. R. R. Tolkien", anoActual - 12));
        libros.add(new ArticuloBibliografico(104, "El Perfume", "Patrick Süskind", anoActual - 10));
        libros.add(new ArticuloBibliografico(105, "Orgullo y Prejuicio", "Jane Austen", anoActual - 30));

        // 5 libros NO RESERVABLES (5 años o menos)
        libros.add(new ArticuloBibliografico(201, "El Principito (Ed. reciente)", "Antoine de Saint-Exupéry", anoActual));
        libros.add(new ArticuloBibliografico(202, "Harry Potter (Ed. nueva)", "J. K. Rowling", anoActual - 2));
        libros.add(new ArticuloBibliografico(203, "Los Juegos del Hambre (Ed. nueva)", "Suzanne Collins", anoActual - 3));
        libros.add(new ArticuloBibliografico(204, "El Código Da Vinci (Ed. nueva)", "Dan Brown", anoActual - 4));
        libros.add(new ArticuloBibliografico(205, "It (Ed. nueva)", "Stephen King", anoActual - 1));
    }

    // Mostrar tabla simple
    private void mostrarTabla(List<ArticuloBibliografico> lista) {
        System.out.println("\nID   | Título                             | Autor");
        System.out.println("------------------------------------------------------------");
        for (ArticuloBibliografico libro : lista) {
            System.out.printf("%-4d | %-34s | %s\n",
                    libro.getId(),
                    libro.getTitulo(),
                    libro.getAutor());
        }
    }

    // Mostrar solo no reservados
    private void mostrarTablaNoReservados(List<ArticuloBibliografico> lista) {
        List<ArticuloBibliografico> filtro = new ArrayList<>();
        for (ArticuloBibliografico l : lista) {
            if (!l.isReservado()) {
                filtro.add(l);
            }
        }

        if (filtro.isEmpty()) {
            System.out.println("\nNo hay libros disponibles.");
        } else {
            mostrarTabla(filtro);
        }
    }

    // Buscar por ID
    private ArticuloBibliografico buscarPorId(int id) {
        for (ArticuloBibliografico l : libros) {
            if (l.getId() == id) {
                return l;
            }
        }
        return null;
    }

    // Programa principal
    public void ejecutar() {

        while (true) {
            System.out.println("\n=== SISTEMA DE RESERVA DE LIBROS ===");
            mostrarTabla(libros);

            System.out.print("\n¿Qué ID de libro desea reservar?: ");
            int idSeleccion;

            try {
                idSeleccion = Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                System.out.println("Entrada no válida.");
                continue;
            }

            ArticuloBibliografico elegido = buscarPorId(idSeleccion);

            if (elegido == null) {
                System.out.println("ID no válido.");
                continue;
            }

            if (elegido.isReservado()) {
                System.out.println("Este libro ya está reservado.");
                continue;
            }

            int antiguedad = elegido.calcularAntiguedad(anoActual);

            if (!elegido.esReservable(anoActual)) {
                System.out.println("Este libro NO es reservable (antigüedad: " + antiguedad + " años).");
                continue;
            }

            List<ArticuloBibliografico> carrito = new ArrayList<>();
            carrito.add(elegido);

            while (true) {
                System.out.print("¿Desea agregar otro libro? (si/no): ");
                String resp = sc.nextLine();

                if (!resp.equalsIgnoreCase("si")) {
                    break;
                }

                mostrarTablaNoReservados(libros);
                System.out.print("Ingrese ID del libro: ");
                int nuevoId = Integer.parseInt(sc.nextLine());

                ArticuloBibliografico nuevo = buscarPorId(nuevoId);

                if (nuevo == null || nuevo.isReservado() || !nuevo.esReservable(anoActual)) {
                    System.out.println("Libro no válido.");
                } else {
                    carrito.add(nuevo);
                    System.out.println("Libro agregado: " + nuevo.getTitulo());
                }
            }

            System.out.println("\nSe reservarán los siguientes libros:");
            for (ArticuloBibliografico l : carrito) {
                System.out.println("- " + l.getTitulo());
                l.setReservado(true);
            }

            System.out.println("Reserva realizada con éxito.");

            System.out.print("¿Desea salir del programa? (si/no): ");
            String salir = sc.nextLine();
            if (salir.equalsIgnoreCase("si")) {
                System.out.println("Saliendo del programa...");
                return;
            }
        }
    }
}
