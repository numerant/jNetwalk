package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import events.RotateButtonEvent;
import events.ShowButtonCoordinatesEvent;
import view.NetwalkButton;

/**
 * Class responsible for creation of Netwalk maze in the main window
 * @author Jakub Maleszewski
 * @since 2014-05-25
 */

public class Maze
{
    private View view;
        /* Panel containing maze */
    private Integer mazeSize;
    private JPanel gamePanel;
    private NetwalkButton[][] mazeButtons;
    private static final Integer BUTTON_SIZE_PX = 64;
    
    /**
     * @param view - specifies view used to send events
     * @param size - size of the square maze
     */
    public Maze(final View view, final Integer size)
    {
        this.view = view;
        this.mazeSize = size;
        
        createMazePanel();
    }
    
    /** 
     * Creates maze panel with buttons.
     * Adds buttons to the mazeButtons array
     */
    private void createMazePanel()
    {
        gamePanel = new JPanel();
        gamePanel.setLayout(new GridLayout(mazeSize, mazeSize, 0, 0));
        
        mazeButtons = new NetwalkButton[mazeSize][mazeSize];
        
        for (int yCurrent = 0; yCurrent < mazeSize; yCurrent++)
            for (int xCurrent = 0; xCurrent < mazeSize; xCurrent++)
            {
                final NetwalkButton newButton = new NetwalkButton("", xCurrent, yCurrent);
                newButton.setPreferredSize(new Dimension(BUTTON_SIZE_PX, BUTTON_SIZE_PX));
               
                addRotateActionListener(newButton, xCurrent, yCurrent);
                
                gamePanel.add(newButton);
                mazeButtons[xCurrent][yCurrent] = newButton;
            }
    }

    /**
     * Updates images on maze buttons using a mock
     * @param mock
     */
    public void updateMazePanelFromMock(final Image[][] mock)
    {
        for (int yCurrent = 0; yCurrent < mazeSize; yCurrent++)
            for (int xCurrent = 0; xCurrent < mazeSize; xCurrent++)
            {
                mazeButtons[xCurrent][yCurrent].setIcon(new ImageIcon( mock[xCurrent][yCurrent] ));
            }
    }
    
    /**
     * Adds {@link ShowButtonCoordinatesEvent} {@link ActionListener} to the specified button
     * @param button - NetwalkButton object, which we add a listener to
     * @param xPosition - horizontal position of the button in the maze
     * @param yPosition - vertical position of the button in the maze
     */
    private void addRotateActionListener(final NetwalkButton button, final int xPosition, final int yPosition)
    {
        button.addActionListener(new ActionListener()
        {                 
            public void actionPerformed(ActionEvent event)
            {
                view.sendNetwalkEvent(new RotateButtonEvent(xPosition, yPosition));
            }
        });
    }
    
    /**
     * Adds maze panel to the main window
     */
    public void showMaze()
    {
        
        view.addPanel(gamePanel, BorderLayout.CENTER);
    }
    
    /**
     * Returns reference to the JPanel object associated with current Maze.
     * Useful for removing panel from main window.
     * @return JPanel - associated panel
     */
    public JPanel getMazePanel()
    {
        return gamePanel;
    }
}


