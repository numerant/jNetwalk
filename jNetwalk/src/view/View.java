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
    private StatusBar statusBar;

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
        statusBar = new StatusBar(this);    //TODO add timer!
        
        frame.setSize(400, 400);
        
        frame.setResizable(false);              //Due to bug in Java positioning does not work as intended
        frame.setLocationByPlatform(true);      //when both setResizable() and setLocationByPlatform() are used
        frame.setVisible(true);
        frame.setTitle("jNetwalk");
    }

    /**
     * Initializes the content of the frame.
     */
    private void createMainWindow()
    {
        try 
        {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
        
            /* Some frame properties - default size and positioning */
        frame = new JFrame();
        
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
     * Doesn't need to run in EDT, as it's called by methods already running in this thread
     * @param panel - {@link JPanel} object to add
     * @param layout - {@link BorderLayout} layout type string
     */
    public void addPanel (final JPanel panel, final String layout)
    {
        frame.getContentPane().add(panel, layout);
        frame.pack();
    }

    /**
     * Hides maze panel
     */
    public void hideMazePanel()
    {
        EventQueue.invokeLater(new Runnable()
        {
            public void run() 
            {
                maze.getMazePanel().setVisible(false);
            }
        });
    }
    
    /**
     * Removes previous game panel from the main window and creates new one.
     * 
     * @param size - size of the new maze
     */
    public void createMazePanel(final Integer size)
    {
        final View view = this;
        EventQueue.invokeLater(new Runnable()
        {
            public void run() 
            {
                    /* Remove previous panel if it does exist */
                if (maze != null)       
                {
                    frame.getContentPane().remove(maze.getMazePanel());
                    maze = null;
                }
                
                    /* Create new maze panel and show it */
                maze = new Maze(view, size);
                
            }
        });
    }
    
    /**
     * Updates on-screen maze using a new mock
     * Makes maze panel visible after update (useful when staring a new game)
     * @param mock - mock to update panel with
     */
    public void setMock (final Image[][] mock)
    {
        EventQueue.invokeLater(new Runnable()
        {
            public void run() 
            {
                maze.updateMazePanelFromMock(mock);
                maze.showMaze();
            }
        });
    }
    
    /**
     * Wrapper for setting move count on status bar
     * @param moveCount - number of moves to set as label
     */
    public void setMoveCount(final Integer moveCount)
    {
        EventQueue.invokeLater(new Runnable()
        {
            public void run() 
            {
                statusBar.setMoveCountValue(moveCount);
            }
        });
    }
    
    /**
     * Shows specified message as a message box on the screen.
     * @param message - message to show
     */
    public void showMessage(final String message, final String title)
    {
        try
        {
            EventQueue.invokeAndWait(new Runnable()
            {
                public void run() 
                {
                    JOptionPane.showMessageDialog(frame, message, title, JOptionPane.INFORMATION_MESSAGE);
                }
            });
        }
        catch (InvocationTargetException e)
        {
            e.printStackTrace();
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
    
    /**
     * Wrapper method for showing help dialog.
     */
    public void showHelp()
    {
        EventQueue.invokeLater(new Runnable()
        {
            public void run() 
            {
                new HelpWindow(frame);
            }
        });
    }
}
