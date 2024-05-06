package com.example;
// Java Program to Illustrate App File

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


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
}
