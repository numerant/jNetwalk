package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import events.*;


/**
 * Class responsible for creating main application menu.
 * 
 * @author Jakub Maleszewski
 * @since 2014-05-25
 */

public class MainMenu
{
    private View view;
        /* Menu bar and items */
    private JMenuBar menuBar;
    private JMenu gameMenu;
    private JMenuItem newGameMenuItem;
    private JMenuItem highScoresMenuItem;
    private JMenuItem quitMenuItem;
    private JMenu helpMenu;
    private JMenuItem howToPlayMenuItem;
    private JMenuItem aboutMenuItem;
    
    MainMenu(final View view)
    {
        this.view = view;
        createMenuItems();
        enableMenu();
    }
    
    /**
     * Creates menu items and sets their action listeners
     */
    void createMenuItems()
    {
        menuBar = new JMenuBar();

        gameMenu = new JMenu("Game");
        menuBar.add(gameMenu);
        
        newGameMenuItem = new JMenuItem("New game");
        newGameMenuItem.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent arg0)
            {
                view.sendNetwalkEvent(new GenerateMazeEvent(5));
            }
        });
        gameMenu.add(newGameMenuItem);
        
        
        highScoresMenuItem = new JMenuItem("High scores");
        gameMenu.add(highScoresMenuItem);
        
        quitMenuItem = new JMenuItem("Quit");
        quitMenuItem.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent event)
            {
                System.exit(0);
            }
        });
        gameMenu.add(quitMenuItem);
        
        helpMenu = new JMenu("Help");
        menuBar.add(helpMenu);
        
        howToPlayMenuItem = new JMenuItem("How to play");
        helpMenu.add(howToPlayMenuItem);
        
        aboutMenuItem = new JMenuItem("About");
        helpMenu.add(aboutMenuItem);
        
    }
    
    /**
     * Sets the menu as a default for specified view
     */
    void enableMenu()
    {
        view.setMenuBar(menuBar);
    }
    
}
