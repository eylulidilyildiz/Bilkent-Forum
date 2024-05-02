package com.example;
// Java Program to Illustrate App File


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

// Main class
public class test {

	// Main driver method
	public static void main(String[] args)
	{

		// Create Configuration
		Configuration configuration = new Configuration();
		configuration.configure("hibernate.cfg.xml");
        
		configuration.addAnnotatedClass(Song.class);

		// Create Session Factory and auto-close with try-with-resources.
		try (SessionFactory sessionFactory
				= configuration.buildSessionFactory()) {

			// Initialize Session Object
			Session session = sessionFactory.openSession();

			Song song1 = new Song();

			song1.setId(5);
			song1.setSongName("ahmet kaya");
			song1.setArtist("yorgun demokrat");

			session.beginTransaction();

			// Here we have used
			// persist() method of JPA
			session.persist(song1);

			session.getTransaction().commit();
		}
	}
}
