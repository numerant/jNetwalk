package view;

import javax.swing.*;
import java.awt.*;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;
import net.miginfocom.swing.MigLayout;



public class MainWindow
{
    private JFrame frame;
    private JMenuBar menuBar;
    private JMenu gameMenu;
    private JMenuItem newGameMenuItem;
    private JMenuItem highScoresMenuItem;
    private JMenuItem quitMenuItem;
    private JMenu helpMenu;
    private JMenuItem howToPlayMenuItem;
    private JMenuItem aboutMenuItem;
    

    /**
     * Create the application.
     */
    public MainWindow()
    {
        createMainWindow();
        createMainMenu();
        createStatusBar();
        initialize();
        frame.setVisible(true);
        frame.setResizable(false);
    }

    /**
     * Initialize the contents of the frame.
     */
    private void createMainWindow()
    {
        try 
        {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");     //TODO Make it multiplatform
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
        
        frame = new JFrame();
        frame.setBounds(100, 100, 574, 494);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    /**
     * Create main menu on top of the window
     */
    
    private void createMainMenu()
    {
        menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);
        
        gameMenu = new JMenu("Game");
        menuBar.add(gameMenu);
        
        newGameMenuItem = new JMenuItem("New game");
        gameMenu.add(newGameMenuItem);
        
        highScoresMenuItem = new JMenuItem("High scores");
        gameMenu.add(highScoresMenuItem);
        
        quitMenuItem = new JMenuItem("Quit");
        gameMenu.add(quitMenuItem);
        
        helpMenu = new JMenu("Help");
        menuBar.add(helpMenu);
        
        howToPlayMenuItem = new JMenuItem("How to play");
        helpMenu.add(howToPlayMenuItem);
        
        aboutMenuItem = new JMenuItem("About");
        helpMenu.add(aboutMenuItem);
        frame.getContentPane().setLayout(new BorderLayout(0, 0));
    }
    
    private void createStatusBar()
    {
        JPanel panel = new JPanel();
        frame.getContentPane().add(panel, BorderLayout.SOUTH);
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        
        JLabel lblTime = new JLabel("Time:");
        panel.add(lblTime);
        
        JLabel lblTime_1 = new JLabel("TIME");
        lblTime_1.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(lblTime_1);
        
        Component horizontalGlue = Box.createHorizontalGlue();
        panel.add(horizontalGlue);
        
        JLabel lblMoveCount = new JLabel("Move count:");
        lblMoveCount.setHorizontalAlignment(SwingConstants.LEFT);
        panel.add(lblMoveCount);
        
        JLabel lblCount = new JLabel("COUNT");
        panel.add(lblCount);
    }
    
    private void initialize()
    {

        

        

        
        JPanel panel_1 = new JPanel();
        frame.getContentPane().add(panel_1, BorderLayout.CENTER);
        panel_1.setLayout(new GridLayout(5, 5, 0, 0));
        
        for (int i=0; i<25; i++)
        {
            panel_1.add(new JButton("New button"));
        }
        

    }

}
