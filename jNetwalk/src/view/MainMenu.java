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
    private JMenu newGameMenu;
    private JMenuItem difficulyEasyItem;
    private JMenuItem difficulyMediumItem;
    private JMenuItem difficulyHardItem;
    private JMenuItem difficulyVeryHardItem;
    private JMenuItem highScoresMenuItem;
    private JMenuItem quitMenuItem;
    private JMenu helpMenu;
    private JMenuItem howToPlayMenuItem;
    private JMenuItem aboutMenuItem;
    
        /* How difficult are difficulty levels */
    private final Integer DIFFICULTY_EASY_SIZE = 5;
    private final Integer DIFFICULTY_MEDIUM_SIZE = 7;
    private final Integer DIFFICULTY_HARD_SIZE = 8;
    private final Integer DIFFICULTY_VERY_HARD_SIZE = 10;
    
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
        
        newGameMenu = new JMenu("New game");
        gameMenu.add(newGameMenu);
        
        difficulyEasyItem = new JMenuItem("Easy");
        difficulyEasyItem.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent arg0)
            {
                view.sendNetwalkEvent(new GenerateMazeEvent(DIFFICULTY_EASY_SIZE));
            }
        });
        newGameMenu.add(difficulyEasyItem);
        
        difficulyMediumItem = new JMenuItem("Medium");
        difficulyMediumItem.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent arg0)
            {
                view.sendNetwalkEvent(new GenerateMazeEvent(DIFFICULTY_MEDIUM_SIZE));
            }
        });
        newGameMenu.add(difficulyMediumItem);
        
        difficulyHardItem = new JMenuItem("Hard");
        difficulyHardItem.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent arg0)
            {
                view.sendNetwalkEvent(new GenerateMazeEvent(DIFFICULTY_HARD_SIZE));
            }
        });
        newGameMenu.add(difficulyHardItem);
        
        difficulyVeryHardItem = new JMenuItem("Very Hard");
        difficulyVeryHardItem.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent arg0)
            {
                view.sendNetwalkEvent(new GenerateMazeEvent(DIFFICULTY_VERY_HARD_SIZE));
            }
        });
        newGameMenu.add(difficulyVeryHardItem);
        
        
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
