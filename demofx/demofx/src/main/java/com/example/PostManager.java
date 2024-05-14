package com.example;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class PostManager 
{
    public void createPost(int id, String content, String date, int ownerID, int upvotes, int downvotes,
    String commentIDs, boolean isSalePost, String bookTitle, String authorName,
    String courseName, double price, int usageAmount, String publisherName,
    String bookEdition) 
    {
        Post newPost = new Post();
        newPost.setId(id);
        newPost.setContent(content);
        //newPost.setDate(date);
        newPost.setOwnerID(ownerID);
        newPost.setUpvotes(upvotes);
        newPost.setDownvotes(downvotes);
        newPost.setCommentIDs(commentIDs);
        newPost.setIsSalePost(isSalePost);
        newPost.setBookTitle(bookTitle);
        newPost.setAuthorName(authorName);
        newPost.setCourseName(courseName);
        newPost.setPrice(price);
        newPost.setUsageAmount(usageAmount);
        newPost.setPublisherName(publisherName);
        newPost.setBookEdition(bookEdition);

        Session session = DatabaseConnection.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.persist(newPost);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void deletePost(int postId) 
    {
        Session session = DatabaseConnection.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Post postToDelete = session.get(Post.class, postId); 
            if (postToDelete != null) {
                session.remove(postToDelete); 
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
