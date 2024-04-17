import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.Border;

public class LoginFrame extends JFrame {
    
    private LoginPanel loginPanel;
    private CreateProfilePanel createProfilePanel;
    
    public LoginFrame()
    {
        setExtendedState(JFrame.MAXIMIZED_BOTH); 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Login to the Bilkent Forum");

        JLabel label = new JLabel("UMUT OVUNC SENOL");
        add(label, BorderLayout.WEST);
        loginPanel = new LoginPanel();
        createProfilePanel = new CreateProfilePanel();

        add(loginPanel, BorderLayout.CENTER);
          
    }

        

}
