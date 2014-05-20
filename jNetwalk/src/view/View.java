package view;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;


/**
 * Class representing view in MVC. Creates interface and main window.
 * 
 * @author Jakub Maleszewski
 * @since 2014-05-18
 */
public class View
{
    /**
     *  Internal class for representing a button aware of its maze position
     */
    private class NetwalkButton extends JButton
    {
        private static final long serialVersionUID = 1L;    // to suppress warnings
        private Integer xPosition;
        private Integer yPosition;
        
        /**
         * Constructor - sets button caption and maze position
         */
        public NetwalkButton(final String string, final Integer xPosition, final Integer yPosition)
        {
            super(string);
            this.xPosition = xPosition;
            this.yPosition = yPosition;
        }
    }
    
    private JFrame frame;
        /* Menu bar related */
    private JMenuBar menuBar;
    private JMenu gameMenu;
    private JMenuItem newGameMenuItem;
    private JMenuItem highScoresMenuItem;
    private JMenuItem quitMenuItem;
    private JMenu helpMenu;
    private JMenuItem howToPlayMenuItem;
    private JMenuItem aboutMenuItem;
        /* Status bar related */
    private JPanel statusBarPanel;
    private JLabel timeLabel;
    private JLabel timeValue;
    private JLabel moveCountLabel;
    private JLabel moveCountValue;
        /* Panel containing maze */
    private JPanel gamePanel;
    private NetwalkButton[][] mazeButtons;
    
    private static final Integer BUTTON_SIZE_PX = 80;
    
    /**
     * Constructor - calls (in EDT) method responsible for creation of GUI
     */
    public View()
    {
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
        createMainMenu();
        showMaze(7);                //TODO Should be disabled by default
        createStatusBar();          //TODO Status bar should be shown after maze
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
        frame.setLocationByPlatform(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
    }
    
    /**
     * Creates main menu on top of the window
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
    
    /**
     * Creates and shows status bar with current score
     */
    private void createStatusBar()
    {
        statusBarPanel = new JPanel();
        frame.getContentPane().add(statusBarPanel, BorderLayout.SOUTH);
        statusBarPanel.setLayout(new BoxLayout(statusBarPanel, BoxLayout.X_AXIS));
        
        timeLabel = new JLabel("Time:");
        statusBarPanel.add(timeLabel);
        
        timeValue = new JLabel("");
        timeValue.setAlignmentX(Component.CENTER_ALIGNMENT);
        statusBarPanel.add(timeValue);
        
        statusBarPanel.add(Box.createHorizontalGlue());
        
        moveCountLabel = new JLabel("Move count:");
        moveCountLabel.setHorizontalAlignment(SwingConstants.LEFT);
        statusBarPanel.add(moveCountLabel);
        
        moveCountValue = new JLabel("");
        statusBarPanel.add(moveCountValue);
    }
    
    /**
     * Shows generated maze on the screen.
     * Creates array of buttons.
     * @param size Count of rows and columns
     */
    private void showMaze(final Integer size)
    {
        gamePanel = new JPanel();
        frame.getContentPane().add(gamePanel, BorderLayout.CENTER);
        gamePanel.setLayout(new GridLayout(size, size, 0, 0));
        
        mazeButtons = new NetwalkButton[size][size];
        
        
        for (int yCurrent=0; yCurrent<size; yCurrent++)
            for (int xCurrent=0; xCurrent<size; xCurrent++)
            {
                final NetwalkButton newButton = new NetwalkButton("", xCurrent, yCurrent);
                newButton.setPreferredSize(new Dimension(BUTTON_SIZE_PX, BUTTON_SIZE_PX));
                try 
                {
                    Image img = ImageIO.read(getClass().getResource("/resources/bmp/internet.png"));
                    if ((yCurrent + xCurrent +1)%3 == 1)
                        img = ImageIO.read(getClass().getResource("/resources/bmp/link1.png"));
                    newButton.setIcon(new ImageIcon(img));
                } 
                catch (IOException ex)
                {
                    ex.printStackTrace();
                }
                newButton.addActionListener(new ActionListener()
                {
                    public void actionPerformed(ActionEvent event)
                    {
                        JOptionPane.showMessageDialog(null, newButton.xPosition.toString());
                    }
                });
                
                gamePanel.add(newButton);
                mazeButtons[xCurrent][yCurrent] = newButton;
            }
        
        frame.pack();
    }

}
