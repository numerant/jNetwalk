package view.itemResources;

import java.awt.Image;
import java.io.IOException;
import java.util.EnumMap;

import javax.imageio.ImageIO;

import model.MazeMockItem;
import model.MazeItems.StraightWire;
import model.MazeMockItem.Direction;

/**
 * Class containing image resources for {@link StraightWire}
 * 
 * @author Jakub Maleszewski
 * @since 2014-05-29
 *
 */

public class StraightWireImages
{
    private static EnumMap<Direction, Image> connectedImages;
    private static EnumMap<Direction, Image> disconnectedImages;
    
    static
    {
        connectedImages = new EnumMap<Direction, Image>(Direction.class);
        disconnectedImages = new EnumMap<Direction, Image>(Direction.class);
        try
        {
            connectedImages.put(Direction.UP, ImageIO.read(MazeMockItem.class.getClassLoader().getResource("resources/img/link_straight_up_down_connected.png")));
            connectedImages.put(Direction.DOWN, ImageIO.read(MazeMockItem.class.getClassLoader().getResource("resources/img/link_straight_up_down_connected.png")));
            connectedImages.put(Direction.LEFT, ImageIO.read(MazeMockItem.class.getClassLoader().getResource("resources/img/link_straight_left_right_connected.png")));
            connectedImages.put(Direction.RIGHT, ImageIO.read(MazeMockItem.class.getClassLoader().getResource("resources/img/link_straight_left_right_connected.png")));
            
            disconnectedImages.put(Direction.UP, ImageIO.read(MazeMockItem.class.getClassLoader().getResource("resources/img/link_straight_up_down_disconnected.png")));
            disconnectedImages.put(Direction.DOWN, ImageIO.read(MazeMockItem.class.getClassLoader().getResource("resources/img/link_straight_up_down_disconnected.png")));
            disconnectedImages.put(Direction.LEFT, ImageIO.read(MazeMockItem.class.getClassLoader().getResource("resources/img/link_straight_left_right_disconnected.png")));
            disconnectedImages.put(Direction.RIGHT, ImageIO.read(MazeMockItem.class.getClassLoader().getResource("resources/img/link_straight_left_right_disconnected.png")));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    
    public static Image getImage(StraightWire wire)
    {
        if (wire.isConnected())
            return connectedImages.get(wire.getDirection());
        else
            return disconnectedImages.get(wire.getDirection());
    }


    
}