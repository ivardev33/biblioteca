package controller;

import java.text.ParseException;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import model.Lector;
import model.Libro;
import model.Prestamo;
import java.util.List;
import org.hibernate.query.Query;

public class MenuController {

    private static final Scanner scanner = new Scanner(System.in);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public static void mostrarMenu() {

        // Crea una fábrica de sesiones
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

        // Abre una nueva sesión
        Session session = sessionFactory.openSession();
        System.out.println("CONFIGURACIÓN REALIZADA");
        System.out.println("\n");
        int opcion;
        do {

            System.out.println("== MENÚ DE BIBLIOTECA ==");
            System.out.println("1. Agregar lector");
            System.out.println("2. Agregar libro");
            System.out.println("3. Registrar préstamo");
            System.out.println("4. Registrar devolución");
            System.out.println("5. Libros actualmente prestados a un lector");
            System.out.println("6. Libros disponibles para préstamo.");
            System.out.println("7. Historial de préstamos por lector.");
            System.out.println("8. Salir");
            System.out.print("Selecciona una opción: ");

            opcion = leerOpcion();
            scanner.nextLine(); // Consumir la nueva línea pendiente
            switch (opcion) {
                case 1:
                    agregarLector(session);
                    break;
                case 2:
                    agregarLibro(session);
                    break;
                case 3:
                    registrarPrestamo(session);
                    break;
                case 4:
                    registrarDevolucion(session);
                    break;
                case 5:
                    mostrarLibrosPrestados(session);
                    break;
                case 6:
                	mostrarLibrosDisponiblesParaPrestamo(session);
                    break;
                case 7:
                	mostrarHistorialPrestamosPorLector(session);
                    break;
                case 8:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción no válida.");
                    break;
            }
        } while (opcion != 8);
        // Cierra la sesión al salir del programa
        session.close();
        // Cierra la fábrica de sesiones
        sessionFactory.close();
    }

    private static void agregarLector(Session session) {
        LectorService lectorService = new LectorService();
        // Solicitar datos del lector
        System.out.print("Ingrese el nombre del lector:");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese el apellido del lector:");
        String apellido = scanner.nextLine();
        System.out.print("Ingrese el correo electrónico del lector:");
        String email = scanner.nextLine();

        // Agregar un nuevo lector
        Lector nuevoLector = new Lector();
        nuevoLector.setNombre(nombre);
        nuevoLector.setApellido(apellido);
        nuevoLector.setEmail(email);
        lectorService.agregarLector(session, nuevoLector);
        System.out.println("Lector agregado: " + nuevoLector);
    }

    private static void agregarLibro(Session session) {
        LibroService libroService = new LibroService();
        // Solicitar datos del libro
        System.out.println("Ingrese el título del libro:");
        String titulo = scanner.nextLine();
        System.out.println("Ingrese el autor del libro:");
        String autor = scanner.nextLine();
        System.out.println("Ingrese el año de publicación del libro:");
        int anoPublicacion = Integer.parseInt(scanner.nextLine());

        // Agregar un nuevo libro
        Libro nuevoLibro = new Libro();
        nuevoLibro.setTitulo(titulo);
        nuevoLibro.setAutor(autor);
        nuevoLibro.setAnoPublicacion(anoPublicacion);
        libroService.agregarLibro(session, nuevoLibro);
        System.out.println("Libro agregado: " + nuevoLibro);
    }

    private static void registrarPrestamo(Session session) {
        PrestamoService prestamoService = new PrestamoService();
        
        // Solicitar datos del préstamo
        System.out.println("Ingrese el ID del lector:");
        long idLector = scanner.nextLong();
        scanner.nextLine(); // Consumir la nueva línea pendiente

        System.out.println("Ingrese el ID del libro:");
        long idLibro = scanner.nextLong();
        scanner.nextLine(); // Consumir la nueva línea pendiente

        // Obtener el lector por su ID
        Lector lector = obtenerLectorPorId(session, idLector);
        if (lector == null) {
            System.out.println("No se encontró ningún lector con el ID proporcionado.");
            return;
        }

        // Obtener el libro por su ID
        Libro libro = obtenerLibroPorId(session, idLibro);
        if (libro == null) {
            System.out.println("No se encontró ningún libro con el ID proporcionado.");
            return;
        }

        // Solicitar datos del préstamo
        System.out.println("Ingrese la fecha de préstamo del formato yyyy-MM-dd:");
        String fechaPrestamoStr = scanner.nextLine();
        Calendar calendarPrestamo = Calendar.getInstance();
        try {
            Date fechaPrestamo = dateFormat.parse(fechaPrestamoStr);
            calendarPrestamo.setTime(fechaPrestamo);
        } catch (ParseException e) {
            System.out.println("Error: Formato de fecha incorrecto.");
            return;
        }

        System.out.println("Ingrese la fecha de devolución del formato yyyy-MM-dd:");
        String fechaDevolucionStr = scanner.nextLine();
        Calendar calendarDevolucion = Calendar.getInstance();
        try {
            Date fechaDevolucion = dateFormat.parse(fechaDevolucionStr);
            calendarDevolucion.setTime(fechaDevolucion);
        } catch (ParseException e) {
            System.out.println("Error: Formato de fecha incorrecto.");
            return;
        }

        // Registrar un nuevo préstamo
        Prestamo nuevoPrestamo = new Prestamo();
        nuevoPrestamo.setFchPrestamo(calendarPrestamo.getTime()); // Convertir Calendar a Date
        nuevoPrestamo.setFchDevolucion(calendarDevolucion.getTime()); // Convertir Calendar a Date
        nuevoPrestamo.setLector(lector);
        nuevoPrestamo.setLibro(libro);
        prestamoService.registrarPrestamo(session, nuevoPrestamo);
        System.out.println("Préstamo registrado: " + nuevoPrestamo);
    }
    
    private static Lector obtenerLectorPorId(Session session, long idLector) {
        try {
            // Utilizamos el método get de Hibernate para obtener el Lector por su ID
            Lector lector = session.get(Lector.class, idLector);
            return lector; // Retorna el Lector encontrado o null si no se encuentra
        } catch (Exception e) {
            // Manejamos cualquier excepción que pueda ocurrir durante la consulta
            System.out.println("Error al obtener el Lector: " + e.getMessage());
            return null; // En caso de error, retornamos null
        }
    }

    private static Libro obtenerLibroPorId(Session session, long idLibro) {
        try {
            // Utilizamos el método get de Hibernate para obtener el Libro por su ID
            Libro libro = session.get(Libro.class, idLibro);
            return libro; // Retorna el Libro encontrado o null si no se encuentra
        } catch (Exception e) {
            // Manejamos cualquier excepción que pueda ocurrir durante la consulta
            System.out.println("Error al obtener el Libro: " + e.getMessage());
            return null; // En caso de error, retornamos null
        }
    }

    private static void registrarDevolucion(Session session) {
        PrestamoService prestamoService = new PrestamoService();

        // Solicitar el ID del préstamo a devolver
        System.out.print("Ingrese el ID del préstamo a devolver: ");
        long idPrestamo = scanner.nextLong();
        scanner.nextLine(); // Consumir la nueva línea pendiente

        // Obtener el préstamo por su ID
        Prestamo prestamo = prestamoService.obtenerPrestamoPorId(session, idPrestamo);
        if (prestamo == null) {
            System.out.println("No se encontró ningún préstamo con el ID proporcionado.");
            return;
        }

        // Solicitar la fecha de devolución
        System.out.println("Ingrese la fecha de devolución del formato yyyy-MM-dd:");
        String fechaDevolucionStr = scanner.nextLine();
        Calendar calendarDevolucion = Calendar.getInstance();
        try {
            Date fechaDevolucion = dateFormat.parse(fechaDevolucionStr);
            calendarDevolucion.setTime(fechaDevolucion);
        } catch (ParseException e) {
            System.out.println("Error: Formato de fecha incorrecto.");
            return;
        }

        // Actualizar la fecha de devolución del préstamo
        prestamo.setFchDevolucion(calendarDevolucion.getTime());

        // Registrar la devolución del préstamo
        prestamoService.registrarDevolucion(session, prestamo);
        System.out.println("Devolución registrada para el préstamo ID " + idPrestamo);
    }
    
    //CONSULTA LIBROS ACTUALMENTE PRESTADOS LECTOR

    private static void mostrarLibrosPrestados(Session session) {
    	System.out.print("Ingrese el ID del lector para ver los libros prestados: ");
        long idLector = scanner.nextLong();
        scanner.nextLine(); // Consumir la nueva línea pendiente

        // Obtener el lector por su ID
        Lector lector = obtenerLectorPorId(session, idLector);
        if (lector == null) {
            System.out.println("No se encontró ningún lector con el ID proporcionado.");
            return;
        }

        
        Query query = session.createQuery("SELECT p FROM Prestamo p WHERE p.lector.idLector = :idLector");
        query.setParameter("idLector", idLector);

        List<Prestamo> prestamos = query.list();
        if (prestamos.isEmpty()) {
            System.out.println("El lector no tiene libros prestados en este momento.");
            return;
        }

        System.out.println("Libros prestados al lector " + lector.getNombre() + ":");
        for (Prestamo prestamo : prestamos) {
            System.out.println(prestamo.getLibro().getTitulo());
        }
    }
    
    //CONSULTA LIBROS DISPONIBLES POR PRESTAMO
    
    private static void mostrarLibrosDisponiblesParaPrestamo(Session session) {
        LibroService libroService = new LibroService(); // Instancia de LibroService
        List<Libro> librosDisponibles = libroService.obtenerLibrosDisponiblesParaPrestamo(session);

        if (librosDisponibles != null) {
            System.out.println("Libros disponibles para préstamo:");
            for (Libro libro : librosDisponibles) {
                System.out.println(libro.getTitulo());
            }
        } else {
            System.out.println("Error al obtener la lista de libros disponibles para préstamo.");
        }
    }
    
  //CONSULTA  HISTORIAL DE PRESTAMOS POR LECTOR
    private static void mostrarHistorialPrestamosPorLector(Session session) {
    	
    	PrestamoService prestamoService = new PrestamoService();
        // Obtener el historial de préstamos de todos los lectores
        List<Prestamo> historial = prestamoService.obtenerHistorialPrestamos(session);
        
        // Verificar si se encontraron préstamos
        if (historial.isEmpty()) {
            System.out.println("No hay préstamos registrados en el historial.");
        } else {
            System.out.println("Historial de préstamos:");
            for (Prestamo prestamo : historial) {
                System.out.println("ID Préstamo: " + prestamo.getIdPrestamo());
                System.out.println("Fecha Préstamo: " + prestamo.getFchPrestamo());
                System.out.println("Fecha Devolución: " + prestamo.getFchDevolucion());
                System.out.println("Lector: " + prestamo.getLector().getNombre());
                System.out.println("Libro: " + prestamo.getLibro().getTitulo());
                System.out.println("------------------------------------");
            }
        }

}

    private static int leerOpcion() {
        while (!scanner.hasNextInt()) {
            System.out.println("Error: Ingresa un número válido.");
            scanner.next();
        }
        return scanner.nextInt();
    }
    
    
}
