package model;

import java.awt.Image;
import java.util.Vector;

import events.ShowYouWonEvent;
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
    /**
     * Represents pair of MazeModelItem objects.
     * First one is already connected to the internet,
     * the second one is the one to check.
     * Used by updateModelItemsInternetConnection()
     */
    private class ItemPair
    {
            /* node which is already connected to the Internet */
        public MazeModelItem sourceItem;
            /* node which we check if it should be connected */
        public MazeModelItem destinationItem;
            /* Direction (of source) on which they are probably connected */
        public Direction sourceDirection;
        
        public ItemPair(MazeModelItem sourceItem, MazeModelItem destinationItem, Direction sourceDirection)
        {
            this.sourceItem = sourceItem;
            this.destinationItem = destinationItem;
            this.sourceDirection = sourceDirection;
        }
    }
    
    private View view;
    private Integer mazeSize;
    private Integer moveCount = 0;
    private MazeMockItem[][] mazeItems;
    private MazeModelItem[][] mazeModel;
    private MazeModelItem[][] mazeModelUnmodified;
    private MazeGenerator generator;
 
    
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
        generator = new MazeGenerator(size);
        mazeModel = generator.generateMazeModel();  //TODO add option to show correct solution
        mazeModelUnmodified = mazeModel;    // we will change items in mazeModel later - this is needed to show the right solution of the maze 
        this.mazeSize = size;
        mazeItems = new MazeMockItem[size][size];
        randomlyRotateAllNodes();
        realMockGenerator(mazeModel);
        updateModelItemsInternetConnection();
        sendMock();
    }
    
    private void realMockGenerator(MazeModelItem[][] mazeModel)
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
    
    private void randomlyRotateAllNodes()
    {
        for (int yCurrent = 0; yCurrent < mazeSize; yCurrent++)
            for (int xCurrent = 0; xCurrent < mazeSize; xCurrent++)
            {
                Integer randomNumber = (int)(Math.random() * 4);
                MazeModelItem nodeToRotate = mazeModel[xCurrent][yCurrent];
                
                    /* rotate it random number of times */
                nodeToRotate.rotate(randomNumber);
            }
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
        mazeModel[xPosition][yPosition].rotate();
        
        moveCount++;
        view.setMoveCount(moveCount);
        
        updateModelItemsInternetConnection();
        
        sendMock();
        if (isMazeSolved())
        {
            System.out.println("Wygrales!");
            view.sendNetwalkEvent(new ShowYouWonEvent(moveCount, 0));
            view.hideMazePanel();
        }
        
        
    }
    
    /**
     * Updates isConnectedToTheInternet parameter on every item from the mazeModel
     * Needed to check if maze is solved
     */
    private void updateModelItemsInternetConnection()
    {
        ItemPair currentPair;
        MazeModelItem sourceItem;
        MazeModelItem destinationItem;
        Vector<MazeModelItem> nodesToCheck = new Vector<MazeModelItem>();
        
            /* Server is the first node we check */
        Integer xPosition = generator.getServerXCoordinate();   
        Integer yPosition = generator.getServerYCoordinate();
        
        MazeModelItem serverItem = mazeModel[xPosition][yPosition];
        
        eraseInternetConnections();
        
        nodesToCheck.add(serverItem);
        
        while (!nodesToCheck.isEmpty())
        {
                /* For every item on the list of nodes with internet connection we check if its neighbor should be 
                 * considered as connected too.
                 */
            
            sourceItem = nodesToCheck.remove(0);
            sourceItem.setWasChecked(true);
            
            xPosition = sourceItem.getXPosition();
            yPosition = sourceItem.getYPosition();
            
                /* Check the upper neighbor */
            try
            {
                if (sourceItem.isUpNeighborConnected())
                {
                    destinationItem = mazeModel[xPosition][yPosition - 1];
                    currentPair = new ItemPair(sourceItem, destinationItem,
                            Direction.UP);
                    if (isItemPairConnected(currentPair))
                    {
                        destinationItem.setIsConnectedToTheInternet(true);
                            /* If the new node wasn't checked yet, we add it to the list */
                        if (!destinationItem.wasChecked())
                            nodesToCheck.add(destinationItem);
                    }
                }
            }
            catch (ArrayIndexOutOfBoundsException e){ /* do nothing - it won't screw anything */}
            
                /* Check the lower neighbor */
            try
            {
                if (sourceItem.isDownNeighborConnected())
                {
                    destinationItem = mazeModel[xPosition][yPosition + 1];
                    currentPair = new ItemPair(sourceItem, destinationItem,
                            Direction.DOWN);
                    if (isItemPairConnected(currentPair))
                    {
                        destinationItem.setIsConnectedToTheInternet(true);
                        if (!destinationItem.wasChecked())
                            nodesToCheck.add(destinationItem);
                    }
                }
            }
            catch (ArrayIndexOutOfBoundsException e){}
            
                /* Check the left neighbor */
            try
            {
                if (sourceItem.isLeftNeighborConnected())
                {
                    destinationItem = mazeModel[xPosition - 1][yPosition];
                    currentPair = new ItemPair(sourceItem, destinationItem,
                            Direction.LEFT);
                    if (isItemPairConnected(currentPair))
                    {
                        destinationItem.setIsConnectedToTheInternet(true);
                        if (!destinationItem.wasChecked())
                            nodesToCheck.add(destinationItem);
                    }
                }
            }
            catch (ArrayIndexOutOfBoundsException e){}
            
                /* Check the right neighbor */
            try
            {
                if (sourceItem.isRightNeighborConnected())
                {
                    destinationItem = mazeModel[xPosition + 1][yPosition];
                    currentPair = new ItemPair(sourceItem, destinationItem,
                            Direction.RIGHT);
                    if (isItemPairConnected(currentPair))
                    {
                        destinationItem.setIsConnectedToTheInternet(true);
                        if (!destinationItem.wasChecked())
                            nodesToCheck.add(destinationItem);
                    }
                }
            }
            catch (ArrayIndexOutOfBoundsException e){}
        }
        copyInternetConnectionsToMock();
    }
    
    private Boolean isItemPairConnected(ItemPair itemPair)
    {
        if (itemPair.sourceDirection == Direction.UP)
        {
            if (itemPair.sourceItem.isUpNeighborConnected() && itemPair.destinationItem.isDownNeighborConnected())
                return true;
        }
        else if (itemPair.sourceDirection == Direction.DOWN)
        {
            if (itemPair.sourceItem.isDownNeighborConnected() && itemPair.destinationItem.isUpNeighborConnected())
                return true;
        }
        else if (itemPair.sourceDirection == Direction.LEFT)
        {
            if (itemPair.sourceItem.isLeftNeighborConnected() && itemPair.destinationItem.isRightNeighborConnected())
                return true;
        }
        else
        {
            if (itemPair.sourceItem.isRightNeighborConnected() && itemPair.destinationItem.isLeftNeighborConnected())
                return true;
        }
        
        return false;
    }
    
    /**
     * Checks if maze is solved (after user action)
     * @return {@link Boolean} - is maze solved
     */
    private Boolean isMazeSolved()
    {
        
        for (int yCurrent = 0; yCurrent < mazeSize; yCurrent++)
            for (int xCurrent = 0; xCurrent < mazeSize; xCurrent++)
            {
                /* if any item is not connected to the Internet, maze is not solved  */
                if (mazeModel[xCurrent][yCurrent].isConnectedToTheInternet().equals(false) && !mazeModel[xCurrent][yCurrent].isServer())
                    return false;
            }
        
        
        return true;
    }
    
    
    /**
     * Clears internet connection bit in model array
     */
    private void eraseInternetConnections()
    {
        for (int yCurrent = 0; yCurrent < mazeSize; yCurrent++)
            for (int xCurrent = 0; xCurrent < mazeSize; xCurrent++)
            {
                mazeModel[xCurrent][yCurrent].setIsConnectedToTheInternet(false);
                mazeModel[xCurrent][yCurrent].setWasChecked(false);
            }
    }
    /**
     * Copies information about being connected to the Internet from model array to the mock
     */
    private void copyInternetConnectionsToMock()
    {
        for (int yCurrent = 0; yCurrent < mazeSize; yCurrent++)
            for (int xCurrent = 0; xCurrent < mazeSize; xCurrent++)
            {
                Boolean isConnected = mazeModel[xCurrent][yCurrent].isConnectedToTheInternet();
                mazeItems[xCurrent][yCurrent].setIsConnectedToTheInternet(isConnected);
            }
    }
    
}
