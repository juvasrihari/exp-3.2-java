package com.demo.hibernatecrud;

import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class MainApp {
    public static void main(String[] args) {

        // CREATE
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            Student s1 = new Student("John", 22);
            session.save(s1);
            tx.commit();
            System.out.println("Insert Success!");
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }

        // READ
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            List<Student> list = session.createQuery("from Student", Student.class).list();
            System.out.println("Student List:");
            for (Student s : list) {
                System.out.println(s);
            }
        }

        // UPDATE
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            Student s = session.get(Student.class, 1);
            if (s != null) {
                s.setAge(25);
                session.update(s);
                tx.commit();
                System.out.println("Update Success!");
            }
        }

        // DELETE
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            Student s = session.get(Student.class, 1);
            if (s != null) {
                session.delete(s);
                tx.commit();
                System.out.println("Delete Success!");
            }
        }

        HibernateUtil.getSessionFactory().close();
    }
}
