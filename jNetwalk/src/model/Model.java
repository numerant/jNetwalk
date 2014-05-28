package model;

import java.awt.Image;

import view.View;
import model.MazeMockItem.Direction;
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
    private MazeMockItem[][] mazeItems;
    private MazeModelItem[][] mazeModel;
 
    
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
        MazeGenerator generator = new MazeGenerator(size);
        mazeModel = generator.generateMazeModel();
        this.mazeSize = size;
        mazeItems = new MazeMockItem[size][size];
        //fakeMockGenerator();            //TODO Implement maze algorithm instead of "fake generator"
        realMockGenerator();
        sendMock();
    }
    
        /** FOR REMOVAL - JUST FOR THE TESTING PURPOSES */
    private void fakeMockGenerator()
    {
        for (int yCurrent = 0; yCurrent < mazeSize; yCurrent++)
            for (int xCurrent = 0; xCurrent < mazeSize; xCurrent++)
            {
                Wire newItem = new TriWayWire(Direction.RIGHT);
                mazeItems[xCurrent][yCurrent] = newItem;
            }
        mazeItems[2][4] = new Server(Direction.DOWN);
        mazeItems[0][2] = new Client(Direction.RIGHT);
        mazeItems[2][3].setIsConnected(true);
        mazeItems[0][3] = new Client(Direction.RIGHT);
    }
    
    private void realMockGenerator()
    {
        
        for (int yCurrent = 0; yCurrent < mazeSize; yCurrent++)
            for (int xCurrent = 0; xCurrent < mazeSize; xCurrent++)
            {
                MazeModelItem modelItem = mazeModel[xCurrent][yCurrent];
                MazeMockItem mockItem = null;
                
                if (modelItem.isClient())
                    mockItem = new Client(getDirectionFromMazeModelItem(modelItem));
                else if (modelItem.isServer())
                    mockItem = new Server(getDirectionFromMazeModelItem(modelItem));
                else if (modelItem.isTriWayWire())
                    mockItem = new TriWayWire(getDirectionFromMazeModelItem(modelItem));
                else if (modelItem.isNinetyDegreeWire())
                    mockItem = new NinetyDegreeWire(Direction.UP);
                else 
                    mockItem = new StraightWire(Direction.UP);
                
                mazeItems[xCurrent][yCurrent] = mockItem;
            }
    }
    
    /**
     * Takes direction from {@link MazeModelItem} object and converts it to {@link Direction} enumerator
     * @param modelItem - object to convert
     * @return {@link Direction} object
     */
    private Direction getDirectionFromMazeModelItem(final MazeModelItem modelItem)
    {
        Direction direction = null;
        
        if (modelItem.isClient() || modelItem.isServer())
        {
            if (modelItem.isDownNeighborConnected())
                direction = Direction.DOWN;
            else if (modelItem.isUpNeighborConnected())
                direction = Direction.UP;
            else if (modelItem.isLeftNeighborConnected())
                direction = Direction.LEFT;
            else if (modelItem.isRightNeighborConnected())
                direction = Direction.RIGHT;
        }
        else direction = Direction.UP;
        
        
        return direction;
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
     * Rotates {@link MazeMockItem} object of specified coordinates.
     * Invokes function checking how the maze has changed.
     * Notifies view of changes.
     * @param xPosition - horizontal position of {@link MazeMockItem} in a grid
     * @param yPosition - vertical position of {@link MazeMockItem} in a grid
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
