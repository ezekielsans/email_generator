package employeeDAO;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;


import model.Employee;
import session.HibernateUtil;

public class EmployeeDao {


    public void saveEmployee(Employee employee) {

        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(employee);
            transaction.commit();


        } catch (Exception ex) {

            if (transaction != null) {

                transaction.rollback();
            }
            ex.printStackTrace();

        }

    }

    public List<Employee> getEmployee() {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            return session.createQuery("from Employee", Employee.class).list();

        }


    }
}
