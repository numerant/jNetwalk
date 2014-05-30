package model;

/**
 * Represents an single item in model's maze representation
 * 
 * @author Jakub Maleszewski
 * @since 2014-05-27
 */
public class MazeModelItem
{       
    private Boolean isServer = false;
    private Boolean isClient = false;
    private Boolean isTaken = false;
    private Integer takenNeighborCount = 0;
    private Boolean upNeighborTaken = false;
    private Boolean downNeighborTaken = false;
    private Boolean leftNeighborTaken = false;
    private Boolean rightNeighborTaken = false;
    private Integer connectedNeighborCount = 0;
    private Boolean upNeighborConnected = false;
    private Boolean downNeighborConnected = false;
    private Boolean leftNeighborConnected = false;
    private Boolean rightNeighborConnected = false;
    private Boolean isConnectedToTheInternet = false;
    private Boolean wasChecked = false;
    private Integer xPosition;
    private Integer yPosition;
    
    /**
     * Creates new MazeModelItem on specified position
     */
    public MazeModelItem(Integer xPosition, Integer yPosition)
    {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
    }
    
    /**
     * Getter for isServer property
     * @return {@link Boolean} value - true if item represents a server
     */
    public Boolean isServer()
    {
        return isServer;
    }
    
    /**
     * Setter for isServer property
     * @param isServer - {@link Boolean} parameter to set
     */
    public void setIsServer(Boolean isServer)
    {
        this.isServer = isServer;
            /* If it's a server, its field must be taken */
        this.isTaken = true;
    }
    
    /**
     * Getter for isClient property
     * @return {@link Boolean} value - true if item represents a client
     */
    public Boolean isClient()
    {
        return isClient;
    }

    /**
     * Setter for isClient property
     * @param isClient - {@link Boolean} parameter to set
     */
    public void setIsClient(Boolean isClient)
    {
        this.isClient = isClient;
            /* If it's a client, its field must be taken */
        this.isTaken = true;
    }
    
    /**
     * Getter for isTaken propety
     * @return {@link Boolean} value - true if the field is already taken
     */
    public Boolean isTaken()
    {
        return isTaken;
    }
    
    /**
     * Setter for isTaken property
     * @param isTaken - {@link Boolean} parameter to set
     */
    public void setIsTaken(Boolean isTaken)
    {
        this.isTaken = isTaken;
    }
    
    /**
     * Returns number of neighbors that are not taken yet
     * @return {@link Integer} value - free neighbor count
     */
    public Integer getFreeNeighborCount()
    {
        return (4 -takenNeighborCount);
    }
    
    /**
     * Setter for takenNeighborCount property
     * @param isClient - {@link Integer} parameter to set
     */
    public void setTakenNeighborCount(Integer takenNeighborCount)
    {
        this.takenNeighborCount = takenNeighborCount;
    }
    
    /**
     * Getter for isUpNeighborConnected property
     * @return {@link Boolean} value
     */
    public Boolean isUpNeighborConnected()
    {
        return upNeighborConnected;
    }
    
    /**
     * Setter for upNeighborConnected property
     * @param isClient - {@link Boolean} parameter to set
     */
    public void setUpNeighborConnected(Boolean upNeighborConnected)
    {
        this.upNeighborConnected = upNeighborConnected;
    }
    
    /**
     * Getter for isLeftNeighborConnected property
     * @return {@link Boolean} value
     */
    public Boolean isLeftNeighborConnected()
    {
        return leftNeighborConnected;
    }
    
    /**
     * Setter for leftNeighborConnected property
     * @param isClient - {@link Boolean} parameter to set
     */
    public void setLeftNeighborConnected(Boolean leftNeighborConnected)
    {
        this.leftNeighborConnected = leftNeighborConnected;
    }
    
    /**
     * Getter for isRightNeighborConnected property
     * @return {@link Boolean} value
     */
    public Boolean isRightNeighborConnected()
    {
        return rightNeighborConnected;
    }
    
    /**
     * Setter for rightNeighborConnected property
     * @param isClient - {@link Boolean} parameter to set
     */
    public void setRightNeighborConnected(Boolean rightNeighborConnected)
    {
        this.rightNeighborConnected = rightNeighborConnected;
    }

    /**
     * Getter for isDownNeighborConnected property
     * @return {@link Boolean} value
     */
    public Boolean isDownNeighborConnected()
    {
        return downNeighborConnected;
    }

    /**
     * Setter for downNeighborConnected property
     * @param isClient - {@link Boolean} parameter to set
     */
    public void setDownNeighborConnected(Boolean downNeighborConnected)
    {
        this.downNeighborConnected = downNeighborConnected;
    }

    /**
     * Getter for connectedNeighborCount property
     * @return {@link Integer} value
     */
    public Integer getConnectedNeighborCount()
    {
        return connectedNeighborCount;
    }

    /**
     * Adds 1 to connected neighbor count
     */
    public void incrementConnectedNeighborCount()
    {
        this.connectedNeighborCount++;
    }
    
    /**
     * Getter for xPosition property
     * @return {@link Integer} value
     */
    public Integer getXPosition()
    {
        return xPosition;
    }

    /**
     * Setter for xPosition property
     * @param xPosition position to set
     */
    public void setXPosition(Integer xPosition)
    {
        this.xPosition = xPosition;
    }

    /**
     * Getter for yPosition property
     * @return {@link Integer} value
     */
    public Integer getYPosition()
    {
        return yPosition;
    }
    
    /**
     * Setter for yPosition property
     * @param yPosition position to set
     */
    public void setYPosition(Integer yPosition)
    {
        this.yPosition = yPosition;
    }

    /**
     * Getter for isUpNeighborTaken property
     * @return {@link Boolean} value
     */
    public Boolean isUpNeighborTaken()
    {
        return upNeighborTaken;
    }

    /**
     * Setter for upNeighborTaken property
     * @param isClient - {@link Boolean} parameter to set
     */
    public void setUpNeighborTaken(Boolean upNeighborTaken)
    {
        this.upNeighborTaken = upNeighborTaken;
    }

    /**
     * Getter for isDownNeighborTaken property
     * @return {@link Boolean} value
     */
    public Boolean isDownNeighborTaken()
    {
        return downNeighborTaken;
    }

    /**
     * Setter for downNeighborTaken property
     * @param isClient - {@link Boolean} parameter to set
     */
    public void setDownNeighborTaken(Boolean downNeighborTaken)
    {
        this.downNeighborTaken = downNeighborTaken;
    }

    /**
     * Getter for isLeftNeighborTaken property
     * @return {@link Boolean} value
     */
    public Boolean isLeftNeighborTaken()
    {
        return leftNeighborTaken;
    }

    /**
     * Setter for leftNeighborTaken property
     * @param isClient - {@link Boolean} parameter to set
     */
    public void setLeftNeighborTaken(Boolean leftNeighborTaken)
    {
        this.leftNeighborTaken = leftNeighborTaken;
    }

    /**
     * Getter for isRightNeighborTaken property
     * @return {@link Boolean} value
     */
    public Boolean isRightNeighborTaken()
    {
        return rightNeighborTaken;
    }

    /**
     * Setter for rightNeighborTaken property
     * @param isClient - {@link Boolean} parameter to set
     */
    public void setRightNeighborTaken(Boolean rightNeighborTaken)
    {
        this.rightNeighborTaken = rightNeighborTaken;
    }
    
    /**
     * Checks if node represents tri-way wire
     * @return {@link Boolean} value
     */
    public Boolean isTriWayWire()
    {
        if (connectedNeighborCount.equals(3))
            return true;
        else return false;
    }
    
    /**
     * Checks if node represents 90-degree wire
     * @return {@link Boolean} value
     */
    public Boolean isNinetyDegreeWire()
    {
        if (connectedNeighborCount.equals(2) && !areBothLeftAndRightConnected() && !areBothUpAndDownConnected())
            return true;
        else return false;
    }
    
   /**
    * Checks if the node is connected to both upper and lower neighbor
    * @return {@link Boolean} value
    */
    public Boolean areBothUpAndDownConnected()
    {
        return upNeighborConnected && downNeighborConnected;
    }
    
    /**
     * Checks if the node is connected to both left and right neighbor
     * @return {@link Boolean} value
     */
    public Boolean areBothLeftAndRightConnected()
    {
        return leftNeighborConnected && rightNeighborConnected;
    }
    
    /**
     * Rotates specified node counter-clockwise
     * (by updating is*Connected parameters
     */
    public void rotate()
    {
        Boolean oldUpNeighborConnected = upNeighborConnected;
        upNeighborConnected = rightNeighborConnected;
        rightNeighborConnected = downNeighborConnected;
        downNeighborConnected = leftNeighborConnected;
        leftNeighborConnected = oldUpNeighborConnected;
    }
    
    /**
     * Rotates specified node counter-clockwise specified number of times
     * @param rotationCount - how many times should we rotate it
     */
    public void rotate(Integer rotationCount)
    {
        for (int iterator=0; iterator<rotationCount; iterator++)
            rotate();
    }

    /**
     * Getter for isConnectedToTheInternet property
     * @return {@link Boolean} value
     */
    public Boolean isConnectedToTheInternet()
    {
        return isConnectedToTheInternet;
    }

    /**
     * Setter for isConnectedToTheInternet property
     * @param isClient - {@link Boolean} parameter to set
     */
    public void setIsConnectedToTheInternet(Boolean isConnectedToTheInternet)
    {
        if (isServer.equals(false))     //server is always connected
            this.isConnectedToTheInternet = isConnectedToTheInternet;
    }

    /**
     * Getter for wasChecked property
     * @return {@link Boolean} value
     */
    public Boolean wasChecked()
    {
        return wasChecked;
    }

    /**
     * Setter for wasChecked property
     * @param isClient - {@link Boolean} parameter to set
     */
    public void setWasChecked(Boolean wasChecked)
    {
        this.wasChecked = wasChecked;
    }
    
    
}