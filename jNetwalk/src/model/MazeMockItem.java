package model;

import java.awt.Image;

import model.MazeItems.Server;

/**
 * Class representing type of a field on the maze
 * Contains static subclasses, like server, client, cable
 * 
 * @author Jakub Maleszewski
 * @since 2014-05-25
 */
public abstract class MazeMockItem
{
    /**
     * Describes direction of the item on the maze
     */
    public static enum Direction
    {
        UP, DOWN, LEFT, RIGHT
    }

    protected Integer xPosition;
    protected Integer yPosition;
    protected Direction direction;
    protected Boolean isConnected;
    
    public MazeMockItem() {}
    
    /**
     * Returns an {@link Image} object containing picture of MazeItem's current state
     * @return {@link Image} object
     */
    public abstract Image getImage();
    
    /**
     * Rotates an object (counter-clockwise)
     */
    public void rotate()
    {     
        switch (direction)
        {
            case DOWN:
                direction = Direction.RIGHT;
                break;
            case LEFT:
                direction = Direction.DOWN;
                break;
            case RIGHT:
                direction = Direction.UP;
                break;
            case UP:
                direction = Direction.LEFT;
                break;
        }
    }

    /**
     * Getter for direction field - used in {@link ItemImages}
     */
    public Direction getDirection()
    {
        return this.direction;
    }
    
    /**
     * Getter for isConnected field - used in {@link ItemImages}
     */
    public Boolean isConnected()
    {
        return this.isConnected;
    }
    
    /**
     * Setter for isConnected field
     */
    public void setIsConnectedToTheInternet(Boolean isConnected)
    {
        if (this instanceof Server)      //server is always connected
            this.isConnected = true;
        else
            this.isConnected = isConnected;
    }
    
}
