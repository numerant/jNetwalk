package view;

import javax.swing.*;
import java.awt.*;

public class MainWindow
{
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

    /**
     * Creates the application main window.
     */
    public MainWindow()
    {
        createMainWindow();
        createMainMenu();
        createStatusBar();
        showMaze(6);            //TODO Should be disabled by default
        frame.setVisible(true);
        frame.setResizable(false);
    }

    /**
     * Initializes the content of the frame.
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
     * Shows generated maze on the screen
     * @param size Count of rows and columns
     */
    private void showMaze(final Integer size)
    {
        gamePanel = new JPanel();
        frame.getContentPane().add(gamePanel, BorderLayout.CENTER);
        gamePanel.setLayout(new GridLayout(size, size, 0, 0));
        
        for (int xCurrent=0; xCurrent<size; xCurrent++)
            for (int yCurrent=0; yCurrent<size; yCurrent++)
            {
                gamePanel.add(new NetwalkButton("New button", xCurrent, yCurrent));
            }
        

    }

}
