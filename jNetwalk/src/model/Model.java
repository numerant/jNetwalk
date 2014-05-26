package model;

import java.awt.Image;

import view.View;
import model.MazeItem.Direction;
import model.MazeItems.*;


/**
 * Class implementing model in MVC pattern
 * 
 * @author Jakub Maleszewski
 * @since 2014-05-18
 *
 */

public class Model
{
    private View view;
    private Integer mazeSize;
    private MazeItem[][] mazeItems;
    
    /**
     * Constructor - sets reference to view
     */
    public Model(final View view)
    {
        this.view = view;
    }
    
    /**
     * Method used to generate a maze.
     * Notifies View that the maze has changed.
     */
    public void generateMaze(Integer size)
    {
        this.mazeSize = size;
        mazeItems = new MazeItem[size][size];
        fakeMazeGenerator();            //TODO Implement maze algorithm instead of "fake generator"
        sendMock();
    }
    
        /** FOR REMOVAL - JUST FOR THE TESTING PURPOSES */
    private void fakeMazeGenerator()
    {
        for (int yCurrent = 0; yCurrent < mazeSize; yCurrent++)
            for (int xCurrent = 0; xCurrent < mazeSize; xCurrent++)
            {
                Wire newItem = new NinetyDegreeWire(Direction.RIGHT);
                mazeItems[xCurrent][yCurrent] = newItem;
            }
        mazeItems[2][4] = new Server(Direction.DOWN);
        mazeItems[0][2] = new Client(Direction.RIGHT);
        mazeItems[2][3].setIsConnected(true);
        mazeItems[0][3] = new Client(Direction.RIGHT);
    }
    
    private Boolean isMazeSolved()
    {

        //TODO Implement checkConnections
        return false;
    }
    
    /**
     * Sends mock to the view.
     */
    private void sendMock()
    {
        Image mock[][] = new Image[mazeSize][mazeSize];
        
        for (int yCurrent = 0; yCurrent < mazeSize; yCurrent++)
            for (int xCurrent = 0; xCurrent < mazeSize; xCurrent++)
            {
                mock[xCurrent][yCurrent] = mazeItems[xCurrent][yCurrent].getImage();
            }
        
        view.setMock(mock);
    }
    
    /**
     * Rotates {@link MazeItem} object of specified coordinates.
     * Invokes function checking how the maze has changed.
     * Notifies view of changes.
     * @param xPosition - horizontal position of {@link MazeItem} in a grid
     * @param yPosition - vertical position of {@link MazeItem} in a grid
     */
    public void rotateItem(final Integer xPosition, final Integer yPosition)
    {
        mazeItems[xPosition][yPosition].rotate();
        
        if (isMazeSolved())
        {
            //notify view that the game is finished
        }
        else
        {
            sendMock();
        }
        
    }
}
