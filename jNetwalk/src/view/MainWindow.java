package view;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;


public class MainWindow
{

    private JFrame frame;

    /**
     * Create the application.
     */
    public MainWindow()
    {
        initialize();
        frame.setVisible(true);
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize()
    {
        frame = new JFrame();
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JButton btnNewButton = new JButton("New button");
        frame.getContentPane().add(btnNewButton, BorderLayout.WEST);
    }

}
