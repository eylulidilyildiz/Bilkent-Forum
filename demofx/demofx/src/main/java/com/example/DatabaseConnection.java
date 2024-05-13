package com.example;
// Java Program to Illustrate App File

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import java.util.List;


public class DatabaseConnection {
    private static SessionFactory sessionFactory;

    // Initialize the Hibernate session factory
    public static void connect() {
        try {
            // Load configuration from hibernate.cfg.xml file
            Configuration configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml");

            // Build the session factory
            sessionFactory = configuration.buildSessionFactory();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error connecting to the database: " + e.getMessage());
        }
    }

    // Disconnect from the database
    public static void disconnect() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }

    // Get the Hibernate session factory
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static int countPosts() {
        try (Session session = sessionFactory.openSession()) {
            Long count = session.createQuery("SELECT COUNT(*) FROM Post", Long.class).getSingleResult();
            return count != null ? count.intValue() : 0;
        } catch (Exception e) {
            e.printStackTrace();
            return -1; // Handle exception appropriately
        }
    }

    public static int getMaxPostID() {
        try (Session session = sessionFactory.openSession()) {
            Object result = session.createQuery("SELECT MAX(id) FROM Post", Object.class).uniqueResult();
            if (result instanceof Number) {
                return ((Number) result).intValue();
            } else {
                return 0; // Handle unexpected result
            }
        } catch (Exception e) {
            e.printStackTrace();
            return -1; // Handle exception appropriately
        }
    }

    public static int getMaxCommentID() {
        try (Session session = sessionFactory.openSession()) {
            Object result = session.createQuery("SELECT MAX(id) FROM Comment", Object.class).uniqueResult();
            if (result instanceof Number) {
                return ((Number) result).intValue();
            } else {
                return 0; // Handle unexpected result
            }
        } catch (Exception e) {
            e.printStackTrace();
            return -1; // Handle exception appropriately
        }
    }

    public static int getMaxUserID()
    {
        try (Session session = sessionFactory.openSession()) {
            Object result = session.createQuery("SELECT MAX(id) FROM User", Object.class).uniqueResult();
            if (result instanceof Number) {
                return ((Number) result).intValue();
            } else {
                return 0; // Handle unexpected result
            }
        } catch (Exception e) {
            e.printStackTrace();
            return -1; // Handle exception appropriately
        }
    }
    
    public static int countUsers() {
        try (Session session = sessionFactory.openSession()) {
            Long count = session.createQuery("SELECT COUNT(*) FROM User", Long.class).getSingleResult();
            return count != null ? count.intValue() : 0;
        } catch (Exception e) {
            e.printStackTrace();
            return -1; // Handle exception appropriately
        }
    }
}
