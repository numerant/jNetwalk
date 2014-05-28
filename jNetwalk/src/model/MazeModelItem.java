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
    
    public MazeModelItem(Integer xPosition, Integer yPosition)
    {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
    }
    
    public Boolean getIsServer()
    {
        return isServer;
    }
    
    public void setIsServer(Boolean isServer)
    {
        this.isServer = isServer;
            /* If it's a server, its field must be taken */
        this.isTaken = true;
    }
    
    public Boolean getIsClient()
    {
        return isClient;
    }
    
    public void setIsClient(Boolean isClient)
    {
        this.isClient = isClient;
            /* If it's a client, its field must be taken */
        this.isTaken = true;
    }
    
    public Boolean getIsTaken()
    {
        return isTaken;
    }
    
    public void setIsTaken(Boolean isTaken)
    {
        this.isTaken = isTaken;
    }
    
    public Integer getFreeNeighborCount()
    {
        return (4 -takenNeighborCount);
    }
    
    public void setTakenNeighborCount(Integer takenNeighborCount)
    {
        this.takenNeighborCount = takenNeighborCount;
    }
    
    public Boolean getUpNeighborConnected()
    {
        return upNeighborConnected;
    }
    
    public void setUpNeighborConnected(Boolean upNeighborConnected)
    {
        this.upNeighborConnected = upNeighborConnected;
    }
    
    public Boolean getLeftNeighborConnected()
    {
        return leftNeighborConnected;
    }
    
    public void setLeftNeighborConnected(Boolean leftNeighborConnected)
    {
        this.leftNeighborConnected = leftNeighborConnected;
    }
    
    public Boolean getRightNeighborConnected()
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

    public Boolean getDownNeighborConnected()
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

    public Boolean getUpNeighborTaken()
    {
        return upNeighborTaken;
    }

    public void setUpNeighborTaken(Boolean upNeighborTaken)
    {
        this.upNeighborTaken = upNeighborTaken;
    }

    public Boolean getDownNeighborTaken()
    {
        return downNeighborTaken;
    }

    public void setDownNeighborTaken(Boolean downNeighborTaken)
    {
        this.downNeighborTaken = downNeighborTaken;
    }

    public Boolean getLeftNeighborTaken()
    {
        return leftNeighborTaken;
    }

    public void setLeftNeighborTaken(Boolean leftNeighborTaken)
    {
        this.leftNeighborTaken = leftNeighborTaken;
    }

    public Boolean getRightNeighborTaken()
    {
        return rightNeighborTaken;
    }

    public void setRightNeighborTaken(Boolean rightNeighborTaken)
    {
        this.rightNeighborTaken = rightNeighborTaken;
    }
    
}