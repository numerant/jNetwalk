package view;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.BlockingQueue;
import events.NetwalkEvent;

/**
 * Class representing view in MVC. Creates interface and main window.
 * 
 * @author Jakub Maleszewski
 * @since 2014-05-18
 */

public class View
{
    private BlockingQueue<NetwalkEvent> eventQueue; 
    private JFrame frame;
    private Maze maze;

    /**
     * Constructor - sets event queue for communication with controller and calls (in EDT) method responsible for creation of GUI
     */
    public View(final BlockingQueue<NetwalkEvent> eventQueue)
    {
        this.eventQueue = eventQueue;
        
        try
        {
            EventQueue.invokeAndWait(new Runnable()
            {
                public void run() 
                {
                    createAndShowGUI();
                }
            });
        }
        catch (InvocationTargetException | InterruptedException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Creates the application main window.
     */
    private void createAndShowGUI()
    {
        createMainWindow();
        new MainMenu(this);
        maze = new Maze(this, 7);          //TODO Should be disabled by default
        new StatusBar(this);        //TODO Status bar should be shown after maze creation
        
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
        frame.setResizable(false);  // It has to be set after making the window visible - otherwise positioning won't work as intended
    }

    /**
     * Initializes the content of the frame.
     */
    private void createMainWindow()
    {
        try 
        {
            //UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");     //TODO Make it multiplatform
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
        
            /* Some frame properties - default size and positioning */
        frame = new JFrame();
        frame.setSize(400, 400);
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout(0, 0));
    }
    
    /**
     * Sends an event to the controller event queue
     * @param event - {@link NetwalkEvent} event to send
     */
    public void sendNetwalkEvent(final NetwalkEvent event)
    {
        eventQueue.offer(event);
    }
    
    /**
     * Sets the default menu bar of application main window
     * @param menuBar - {@link JMenuBar} object to set as main menu
     */
    public void setMenuBar (final JMenuBar menuBar)
    {
        frame.setJMenuBar(menuBar);
    }
    
    /**
     * Adds panel to the main window
     * @param panel - {@link JPanel} object to add
     * @param layout - {@link BorderLayout} layout type string
     */
    public void addPanel (final JPanel panel, final String layout)
    {
        frame.getContentPane().add(panel, layout);
        frame.pack();
    }

    public void setMock (final Image[][] mock)
    {
        EventQueue.invokeLater(new Runnable()
        {
            public void run() 
            {
                maze.updateMazePanelFromMock(mock);
            }
        });
    }
    
    public void showMessage(final String message) //TODO Testing only - for removal!
    {
        EventQueue.invokeLater(new Runnable()
        {
            public void run() 
            {
                JOptionPane.showMessageDialog(null, message);
            }
        });
    }
}
