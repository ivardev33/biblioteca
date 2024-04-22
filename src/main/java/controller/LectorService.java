package controller;
import org.hibernate.Session;
import org.hibernate.Transaction;
import model.Lector;

//CRUD LECTOR
public class LectorService {

    // Método para agregar un nuevo lector
    public void agregarLector(Session session, Lector lector) {
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(lector);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    // Método para obtener un lector por su ID
    public Lector obtenerLectorPorId(Session session, long idLector) {
        try {
            return session.get(Lector.class, idLector);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Método para actualizar un lector
    public void actualizarLector(Session session, Lector lector) {
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(lector);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    // Método para eliminar un lector
    public void eliminarLector(Session session, Lector lector) {
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.delete(lector);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}
