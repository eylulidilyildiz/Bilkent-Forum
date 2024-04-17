import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.Border;

public class LoginFrame extends JFrame {
    
    // The instance variables
    private LoginPanel loginPanel;
    private CreateProfilePanel createProfilePanel;
    
    // The Constructor
    public LoginFrame()
    {
        this.setExtendedState (JFrame.MAXIMIZED_BOTH); 
        this.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        this.setTitle ("Login to the Bilkent Forum");

        
        loginPanel = new LoginPanel();
        createProfilePanel = new CreateProfilePanel();

        add (loginPanel, BorderLayout.CENTER);
          
    }

        

}
