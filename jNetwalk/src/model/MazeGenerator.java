package model;

import java.util.Vector;

/**
 * Class responsible for the process of generating game maze
 * @author Jakub Maleszewski
 * @since 2014-05-27
 */
public class MazeGenerator      //TODO: maybe make it static?
{
    private Integer mazeSize;
    private MazeModelItem mazeModel[][];
    private Vector<MazeModelItem> nodesToCheck;
    
    public MazeGenerator(Integer size)
    {
        this.mazeSize = size;
        generateMazeModel();
    }
    
    /**
     * Initializes every item in the array and makes them aware of their positions
     */
    private void initializeMazeModelArray()
    {
        for (int yCurrent = 0; yCurrent < mazeSize; yCurrent++)
            for (int xCurrent = 0; xCurrent < mazeSize; xCurrent++)
            {
                mazeModel[xCurrent][yCurrent] = new MazeModelItem(xCurrent,yCurrent);
            }
    }
    
    /**
     * The main maze generation method.
     * Uses simplified Prim's algorithm for finding the spanning tree
     */
    public MazeModelItem[][] generateMazeModel()      //TODO Maze generation algorithm will be just here!
    {
        nodesToCheck = new Vector<MazeModelItem>();
        Integer serverXCoordinate = (int)(Math.random() * mazeSize);    //TODO check if generated numbers are from the right range
        Integer serverYCoordinate = (int)(Math.random() * mazeSize);
        
        mazeModel = new MazeModelItem[mazeSize][mazeSize];
        MazeModelItem currentItem;
        MazeModelItem randomNeighbor;
      
        initializeMazeModelArray();
        
            /* Randomize server position, check its neighborhood and add its random neighbor to the list */
        currentItem = mazeModel[serverXCoordinate][serverYCoordinate];
        currentItem.setIsServer(true);
        updateItemNeighborhood(currentItem);
        randomNeighbor = createRandomNeighbor(currentItem);
        connectNodes(currentItem, randomNeighbor);
        addNodeToCheck(randomNeighbor);
        
            /* We build a tree until there are any nodes to check left */
        while(!nodesToCheck.isEmpty())
        {
            currentItem = getRandomNode();
            updateItemNeighborhood(currentItem);
            Integer freeNeighborCount = currentItem.getFreeNeighborCount();
            Integer connectedNeighborCount = currentItem.getConnectedNeighborCount();
            
                /* No free neighbors - check if the node is a client (only one connection) */
            if (freeNeighborCount == 0)
            {
                if (connectedNeighborCount == 1)
                    currentItem.setIsClient(true);
                nodesToCheck.remove(currentItem);
                continue;
            }
                /* There is a free neighbor, but the node is already connected to three others */
            if (freeNeighborCount > 0 && connectedNeighborCount == 3)
            {
                nodesToCheck.remove(currentItem);
                continue;
            }
            
                /* Let's find random free neighbor, connect to it and add it to the list */
            randomNeighbor = createRandomNeighbor(currentItem);
            updateItemNeighborhood(currentItem);
            updateItemNeighborhood(randomNeighbor);
            connectNodes(currentItem, randomNeighbor);
            addNodeToCheck(randomNeighbor);
            
        }
        return mazeModel;
    }
    
    /**
     * Helper method used to update neighborhood information of specified item
     * @param item - node in a maze to update data
     */
    private void updateItemNeighborhood(MazeModelItem item)
    {
        item.setTakenNeighborCount(0);
        
        Integer xPosition = item.getXPosition();
        Integer yPosition = item.getYPosition();
        Integer takenNeighborCount = 0;
        
            /* When item is in the top row or its higher neighbor is taken */
        if (yPosition.equals(0) || mazeModel[xPosition][yPosition - 1].isTaken())
        {
            item.setUpNeighborTaken(true);
            takenNeighborCount++;
        }
            /* When item is in the bottom row or its lower neighbor is taken */
        if (yPosition.equals(mazeSize - 1) || mazeModel[xPosition][yPosition + 1].isTaken())
        {
            item.setDownNeighborTaken(true);
            takenNeighborCount++;
        }
            /* When item is in the first row from the left or its left neighbor is taken */
        if (xPosition.equals(0) || mazeModel[xPosition - 1][yPosition].isTaken())
        {
            item.setLeftNeighborTaken(true);
            takenNeighborCount++;
        }
            /* When item is in the first row from the right or its right neighbor is taken */
        if (xPosition.equals(mazeSize - 1) || mazeModel[xPosition + 1][yPosition].isTaken())
        {
            item.setRightNeighborTaken(true);
            takenNeighborCount++;
        }
        
        item.setTakenNeighborCount(takenNeighborCount);
    }
    
    /**
     * Finds random free neighbor of specified item and initializes it
     * @param item - node to find a new neighbor for
     * @return {@link MazeModelItem} - reference to the new neighbor
     */
    private MazeModelItem createRandomNeighbor(MazeModelItem item)
    {
            /* Maximum count of taken neighbors is 4 */
        Integer freeNeighborsCount = item.getFreeNeighborCount();
            /* No free neighbors - return null */
        if (freeNeighborsCount.equals(0))
        {
            return null;
        }
        
        MazeModelItem[] freeNeighbors = new MazeModelItem[4];
        Integer iterator = 0;
        
            /* Adds every free neighbor to the freeNeighbors array */
        if (item.isUpNeighborTaken().equals(false))
        {
            freeNeighbors[iterator] = mazeModel[ item.getXPosition() ][ item.getYPosition() - 1 ];
            iterator++;
        }
        if (item.isDownNeighborTaken().equals(false))
        {
            freeNeighbors[iterator] = mazeModel[ item.getXPosition() ][ item.getYPosition() + 1 ];
            iterator++;
        }
        if (item.isLeftNeighborTaken().equals(false))
        {
            freeNeighbors[iterator] = mazeModel[ item.getXPosition() - 1 ][ item.getYPosition() ];
            iterator++;
        }
        if (item.isRightNeighborTaken().equals(false))
        {
            freeNeighbors[iterator] = mazeModel[ item.getXPosition() + 1 ][ item.getYPosition() ];
            iterator++;
        }
        
            /* Pick the random free neighbor */
        Integer randomNumber = (int)(Math.random() * freeNeighborsCount);
        
            /* Set it as taken - this one is the chosen one!*/
        freeNeighbors[randomNumber].setIsTaken(true);
        return freeNeighbors[randomNumber];
    }
    
    /** 
     * Sets *NeighborTaken properties of both nodes.
     * It is done by subtraction of second node coordinates [x2,y2] from first node coordinates [x1,y1].
     * When the result is [0,-1], the second node is on top of the first, when it's [0,1] - on the bottom,
     * when [-1,0] - on the right, [1,0] - on the left.
     */
    public void connectNodes(MazeModelItem firstNode, MazeModelItem secondNode)
    {       
        /* Subtract coordinates */
        Integer xDifference = firstNode.getXPosition() - secondNode.getXPosition();
        Integer yDifference = firstNode.getYPosition() - secondNode.getYPosition();
        
        /* Check how nodes are connected */
        if (xDifference.equals(0) && yDifference.equals(1))    // second node is on top
        {
            firstNode.setUpNeighborConnected(true);
            secondNode.setDownNeighborConnected(true);
        }
        else if (xDifference.equals(0) && yDifference.equals(-1))    // second node is on bottom
        {
            firstNode.setDownNeighborConnected(true);
            secondNode.setUpNeighborConnected(true);
        }
        else if (xDifference.equals(1) && yDifference.equals(0))    // second node is on the left
        {
            firstNode.setLeftNeighborConnected(true);
            secondNode.setRightNeighborConnected(true);
        }
        else if (xDifference.equals(-1) && yDifference.equals(0))    // second node is on the right
        {
            firstNode.setRightNeighborConnected(true);
            secondNode.setLeftNeighborConnected(true);
        }
        //TODO add else throw
        
        firstNode.incrementConnectedNeighborCount();
        secondNode.incrementConnectedNeighborCount();
    }
    
    /**
     * Places specified node in the list of nodes to check
     * @param node - node to add
     */
    private void addNodeToCheck(MazeModelItem node)
    {
        nodesToCheck.add(node);
    }
    
    /**
     * Returns random node from the "nodes to check" list
     * @return random node
     * @throws Exception 
     */
    private MazeModelItem getRandomNode()
    {
        if (nodesToCheck.isEmpty() == true)
            System.out.println("Nie dziala");
        
        Integer randomNumber = (int)(Math.random() * nodesToCheck.size());
        return nodesToCheck.get(randomNumber);
        
    }
        
}
