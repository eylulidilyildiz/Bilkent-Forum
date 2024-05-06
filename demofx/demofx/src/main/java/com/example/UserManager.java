package com.example;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class UserManager 
{
    public void createUser(String username, String password, String email) 
    {
        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPassword(password);
        newUser.setEmail(email);

        Session session = DatabaseConnection.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.persist(newUser);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void deleteUser(int userId) 
    {
        Session session = DatabaseConnection.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            User userToDelete = session.get(User.class, userId); 
            if (userToDelete != null) {
                session.remove(userToDelete); 
                tx.commit();
            } 
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}