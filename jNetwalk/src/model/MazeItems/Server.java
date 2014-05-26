package model.MazeItems;

import java.awt.Image;
import java.io.IOException;
import java.util.EnumMap;

import javax.imageio.ImageIO;

import model.MazeItem;

/**
 * Represents server item on the maze
 * Contains associated images within static EnumMap
 * 
 * @author Jakub Maleszewski
 * @since 2014-05-25
 */
public class Server extends MazeItem
{
    private static EnumMap<Direction, Image> connectedImages;
    
    /**
     * Loads images for the Server class into a static EnumMap, so they can be used by all instances of the class
     */
    static
    {
        connectedImages = new EnumMap<Direction, Image>(Direction.class);
        try
        {
            connectedImages.put(Direction.UP, ImageIO.read(MazeItem.class.getClassLoader().getResource("resources/img/server_up.png")));
            connectedImages.put(Direction.DOWN, ImageIO.read(MazeItem.class.getClassLoader().getResource("resources/img/server_down.png")));
            connectedImages.put(Direction.LEFT, ImageIO.read(MazeItem.class.getClassLoader().getResource("resources/img/server_left.png")));
            connectedImages.put(Direction.RIGHT, ImageIO.read(MazeItem.class.getClassLoader().getResource("resources/img/server_right.png")));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
   
    /**
     * Creates object with a specific direction. Useful for creating mock
     */
    public Server(Direction direction)
    {
        this.direction = direction;
        /* Server is always connected */
        isConnected = true;
    }
    
    /**
     * Returns image associated with the item on the maze and its actual direction
     */
    public Image getImage()
    {
        return connectedImages.get(direction);
    }
}
