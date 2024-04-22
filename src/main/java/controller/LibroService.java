package controller;
import org.hibernate.Session;


import org.hibernate.Transaction;
import model.Libro;
import java.util.List;
import org.hibernate.query.Query;

//CRUD LIBRO

public class LibroService {

    // Método para agregar un nuevo libro
    public void agregarLibro(Session session, Libro libro) {
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(libro);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    // Método para obtener un libro por su ID
    public Libro obtenerLibroPorId(Session session, long idLibro) {
        try {
            return session.get(Libro.class, idLibro);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Método para actualizar un libro
    public void actualizarLibro(Session session, Libro libro) {
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(libro);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    // Método para eliminar un libro
    public void eliminarLibro(Session session, Libro libro) {
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.delete(libro);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
 // Método para obtener libros disponibles para préstamo
    public List<Libro> obtenerLibrosDisponiblesParaPrestamo(Session session) {
        try {
            // Creamos la consulta HQL (Hibernate Query Language)
            Query<Libro> query = session.createQuery("FROM Libro WHERE disponible = true", Libro.class);
            // Ejecutamos la consulta y obtenemos el resultado
            List<Libro> libros = query.getResultList();
            return libros;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

