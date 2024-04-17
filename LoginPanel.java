
import java.awt.*;  
import java.awt.event.*;
import javax.swing.*; 

public class LoginPanel extends JPanel{

    JLabel forumLabel;
    JTextField emailField;
    JTextField passwordField;
    JButton loginButton;

    public LoginPanel()
    {
        setLayout(new GridLayout(0,1));
        setSize(500,500);
        setBackground(Color.PINK);   
        createComponents();
    }

    private void createComponents()
    {
        forumLabel =  new JLabel("BILKENT FORUM");
        forumLabel.setFont(new Font("Arial", Font.BOLD, 64));
        emailField = new JTextField("Email Address");
        passwordField = new JTextField("Password");

                
        this.add(forumLabel);
        this.add(emailField);
        this.add(passwordField);

    }
}
