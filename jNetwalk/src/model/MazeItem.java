package model;

import java.awt.Image;
import java.io.IOException;
import java.util.EnumMap;

import javax.imageio.ImageIO;

import model.MazeItems.Server;

/**
 * Class representing type of a field on the maze
 * Contains static subclasses, like server, client, cable
 * 
 * @author Jakub Maleszewski
 * @since 2014-05-25
 */
public class MazeItem
{
    public static enum Direction
    {
        UP, DOWN, LEFT, RIGHT
    }

    protected Integer xPosition;
    protected Integer yPosition;
    protected Direction direction;
    protected Boolean isConnected;
    protected EnumMap<Direction, String> disconnectedImagePaths;
    protected static EnumMap<Direction, String> connectedImagePaths;
    
    /**
     * Returns an {@link Image} object containing picture of MazeItem's current state
     * @return {@link Image} object
     */
    public Image getImage()
    {
        Image image = null;
        try
        {
            if (this instanceof Server || isConnected)
            {
                image = ImageIO.read(getClass().getResource(connectedImagePaths.get(direction)));
            }
            else
            {
                image = ImageIO.read(getClass().getResource(disconnectedImagePaths.get(direction)));
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        };
        
        return image;
    }
    
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
    
    public void rotate(Direction direction)
    {
        this.direction = direction;
    }
    
}
