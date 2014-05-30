package model;

/**
 * Represents an single item in model's maze representation
 * 
 * @author Jakub Maleszewski
 * @since 2014-05-27
 */
public class MazeModelItem      //TODO It should probably be a double-linked list
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
    public void setIsServer(Boolean isServer)   //TODO: parameter is not necessary, setting it always as true is enough
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
    
    public void setTakenNeighborCount(Integer takenNeighborCount)
    {
        this.takenNeighborCount = takenNeighborCount;
    }
    
    public Boolean isUpNeighborConnected()
    {
        return upNeighborConnected;
    }
    
    public void setUpNeighborConnected(Boolean upNeighborConnected)
    {
        this.upNeighborConnected = upNeighborConnected;
    }
    
    public Boolean isLeftNeighborConnected()
    {
        return leftNeighborConnected;
    }
    
    public void setLeftNeighborConnected(Boolean leftNeighborConnected)
    {
        this.leftNeighborConnected = leftNeighborConnected;
    }
    
    public Boolean isRightNeighborConnected()
    {
        return rightNeighborConnected;
    }
    
    public void setRightNeighborConnected(Boolean rightNeighborConnected)
    {
        this.rightNeighborConnected = rightNeighborConnected;
    }

    public Integer getConnectedNeighborCount()
    {
        return connectedNeighborCount;
    }

    public void incrementConnectedNeighborCount()
    {
        this.connectedNeighborCount++;
    }

    public Boolean isDownNeighborConnected()
    {
        return downNeighborConnected;
    }

    public void setDownNeighborConnected(Boolean downNeighborConnected)
    {
        this.downNeighborConnected = downNeighborConnected;
    }

    public Integer getXPosition()
    {
        return xPosition;
    }

    public void setXPosition(Integer xPosition)
    {
        this.xPosition = xPosition;
    }

    public Integer getYPosition()
    {
        return yPosition;
    }

    public void setYPosition(Integer yPosition)
    {
        this.yPosition = yPosition;
    }

    public Boolean isUpNeighborTaken()
    {
        return upNeighborTaken;
    }

    public void setUpNeighborTaken(Boolean upNeighborTaken)
    {
        this.upNeighborTaken = upNeighborTaken;
    }

    public Boolean isDownNeighborTaken()
    {
        return downNeighborTaken;
    }

    public void setDownNeighborTaken(Boolean downNeighborTaken)
    {
        this.downNeighborTaken = downNeighborTaken;
    }

    public Boolean isLeftNeighborTaken()
    {
        return leftNeighborTaken;
    }

    public void setLeftNeighborTaken(Boolean leftNeighborTaken)
    {
        this.leftNeighborTaken = leftNeighborTaken;
    }

    public Boolean isRightNeighborTaken()
    {
        return rightNeighborTaken;
    }

    public void setRightNeighborTaken(Boolean rightNeighborTaken)
    {
        this.rightNeighborTaken = rightNeighborTaken;
    }
    
    public Boolean isTriWayWire()
    {
        if (connectedNeighborCount.equals(3))
            return true;
        else return false;
    }
        //TODO komentarze!!!
    public Boolean areBothUpAndDownConnected()
    {
        return upNeighborConnected && downNeighborConnected;
    }
    
    public Boolean areBothLeftAndRightConnected()
    {
        return leftNeighborConnected && rightNeighborConnected;
    }
    
    public Boolean isNinetyDegreeWire()
    {
        if (connectedNeighborCount.equals(2) && !areBothLeftAndRightConnected() && !areBothUpAndDownConnected())
            return true;
        else return false;
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
    
    
}