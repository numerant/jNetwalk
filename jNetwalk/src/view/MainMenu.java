package view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;


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
    
    MainMenu(View view)
    {
        this.view = view;
        createMenuItems();
        enableMenu();
    }
    
    void createMenuItems()
    {
        menuBar = new JMenuBar();

        gameMenu = new JMenu("Game");
        menuBar.add(gameMenu);
        
        newGameMenuItem = new JMenuItem("New game");
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
    
    void enableMenu()
    {
        view.setMenuBar(menuBar);
        
    }
    
}
