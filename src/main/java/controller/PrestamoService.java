
package controller;
import org.hibernate.Session;

import org.hibernate.Transaction;
import model.Prestamo;
import java.util.List;
import org.hibernate.query.Query;
//CRUD PRESTAMO
public class PrestamoService {

    // Método para registrar un nuevo préstamo
    public void registrarPrestamo(Session session, Prestamo prestamo) {
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(prestamo);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    // Método para registrar la devolución de un préstamo
    public void registrarDevolucion(Session session, Prestamo prestamo) {
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(prestamo);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    // Método para obtener un préstamo por su ID
    public Prestamo obtenerPrestamoPorId(Session session, long idPrestamo) {
        try {
            return session.get(Prestamo.class, idPrestamo);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public List<Prestamo> obtenerHistorialPrestamos(Session session) {
        try {
            // Creamos la consulta HQL (Hibernate Query Language)
            Query<Prestamo> query = session.createQuery("FROM Prestamo", Prestamo.class);
            // Ejecutamos la consulta y obtenemos el resultado
            List<Prestamo> historialPrestamos = query.getResultList();
            return historialPrestamos;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    
    
}
