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
    private Integer moveCount = 0;
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
                    mockItem = new NinetyDegreeWire(getDirectionFromMazeModelItem(modelItem));
                else 
                    mockItem = new StraightWire(getDirectionFromMazeModelItem(modelItem));
                
                mazeItems[xCurrent][yCurrent] = mockItem;
            }
    }
    
    /**
     * Takes direction from {@link MazeModelItem} object and converts it to {@link Direction} enumerator
     * @param modelItem - object to convert
     * @return {@link Direction} object
     */
    private Direction getDirectionFromMazeModelItem(final MazeModelItem modelItem)  //TODO maybe move it to mazeModelItem
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
            /* Tri-way wire */
        else if (modelItem.isTriWayWire())
        {
            if (modelItem.isLeftNeighborConnected() && modelItem.isUpNeighborConnected() && modelItem.isRightNeighborConnected())
                direction = Direction.UP;
            else if (modelItem.isLeftNeighborConnected() && modelItem.isDownNeighborConnected() && modelItem.isRightNeighborConnected())
                direction = Direction.DOWN;
            else if (modelItem.isLeftNeighborConnected() && modelItem.isUpNeighborConnected() && modelItem.isDownNeighborConnected())
                direction = Direction.LEFT;
            else
                direction = Direction.RIGHT;
        }
            /* 90-degree wire */
        else if (modelItem.isNinetyDegreeWire())
        {
            if (modelItem.isUpNeighborConnected() && modelItem.isRightNeighborConnected() )
                direction = Direction.UP;
            else if (modelItem.isDownNeighborConnected() && modelItem.isLeftNeighborConnected())
                direction = Direction.DOWN;
            else if (modelItem.isLeftNeighborConnected() && modelItem.isUpNeighborConnected())
                direction = Direction.LEFT;
            else
                direction = Direction.RIGHT;
        }
            /* Straight wire */
        else 
        {
            if (modelItem.areBothUpAndDownConnected())
            {
                return Direction.UP;    // it doesn't matter if it's UP or DOWN - it looks the same
            }
            else if (modelItem.areBothLeftAndRightConnected())
            {
                return Direction.LEFT;  // it doesn't matter if it's LEFT or RIGHT - it looks the same
            }
        }
        
        
        return direction;
    }
    
    //TODO remove getRandomDirection
   /* private Direction getRandomDirection()
    {
        Direction randomDirection = null;
        Integer randomNumber = (int)(Math.random() * 4);
        
        if (randomNumber.equals(0))
            return Direction.UP;
        if (randomNumber.equals(1))
            return Direction.DOWN;
        if (randomNumber.equals(2))
            return Direction.LEFT;
        if (randomNumber.equals(3))
            return Direction.RIGHT;
        
        
        return randomDirection;
    }*/
    
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
        
        moveCount++;
        view.setMoveCount(moveCount);
        
        if (isMazeSolved())
        {
            //TODO notify view that the game is finished
        }
        else
        {
            sendMock();
        }
        
    }
    
}
