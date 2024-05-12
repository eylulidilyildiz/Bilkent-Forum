package com.example;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class CommentManager 
{
    public void createComment(int id, String content, int ownerID, int commentedPostID) 
    {
        Comment newComment = new Comment();
        newComment.setId(id);
        newComment.setContent(content);
        newComment.setOwnerID(ownerID);
        newComment.setCommentedPostID(commentedPostID);

        Session session = DatabaseConnection.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.persist(newComment);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void deleteComment(int commentID) 
    {
        Session session = DatabaseConnection.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Comment commentToDelete = session.get(Comment.class, commentID); 
            if (commentToDelete != null) {
                session.remove(commentID); 
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
