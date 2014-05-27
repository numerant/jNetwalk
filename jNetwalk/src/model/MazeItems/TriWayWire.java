package model.MazeItems;

import java.awt.Image;
import java.io.IOException;
import java.util.EnumMap;
import javax.imageio.ImageIO;

import model.MazeMockItem;

/**
 * Represents tri-way (-|) wire on the maze
 * 
 * @author Jakub Maleszewski
 * @since 2014-05-26
 */
public class TriWayWire extends Wire
{
    private static EnumMap<Direction, Image> connectedImages;
    private static EnumMap<Direction, Image> disconnectedImages;
    
    /**
     * Loads images for the class into a static EnumMap, so they can be used by all instances of the class
     */
    static
    {
        connectedImages = new EnumMap<Direction, Image>(Direction.class);
        disconnectedImages = new EnumMap<Direction, Image>(Direction.class);
        try
        {
            connectedImages.put(Direction.UP, ImageIO.read(MazeMockItem.class.getClassLoader().getResource("resources/img/link_triway_up_connected.png")));
            connectedImages.put(Direction.DOWN, ImageIO.read(MazeMockItem.class.getClassLoader().getResource("resources/img/link_triway_down_connected.png")));
            connectedImages.put(Direction.LEFT, ImageIO.read(MazeMockItem.class.getClassLoader().getResource("resources/img/link_triway_left_connected.png")));
            connectedImages.put(Direction.RIGHT, ImageIO.read(MazeMockItem.class.getClassLoader().getResource("resources/img/link_triway_right_connected.png")));
            
            disconnectedImages.put(Direction.UP, ImageIO.read(MazeMockItem.class.getClassLoader().getResource("resources/img/link_triway_up_disconnected.png")));
            disconnectedImages.put(Direction.DOWN, ImageIO.read(MazeMockItem.class.getClassLoader().getResource("resources/img/link_triway_down_disconnected.png")));
            disconnectedImages.put(Direction.LEFT, ImageIO.read(MazeMockItem.class.getClassLoader().getResource("resources/img/link_triway_left_disconnected.png")));
            disconnectedImages.put(Direction.RIGHT, ImageIO.read(MazeMockItem.class.getClassLoader().getResource("resources/img/link_triway_right_disconnected.png")));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    
    /**
     * Creates object with a specific direction
     */
    public TriWayWire(Direction direction)
    {
        this.direction = direction;
        isConnected = false;
    }
    
    /**
     * Returns image associated with the item's actual direction
     */
    public Image getImage()
    {
        if (isConnected)
            return connectedImages.get(direction);
        else
            return disconnectedImages.get(direction);
    }
}
