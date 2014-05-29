package view.itemResources;

import java.awt.Image;
import java.io.IOException;
import java.util.EnumMap;

import javax.imageio.ImageIO;

import model.MazeMockItem;
import model.MazeItems.TriWayWire;
import model.MazeMockItem.Direction;

/**
 * Class containing image resources for {@link TriWayWire}
 * 
 * @author Jakub Maleszewski
 * @since 2014-05-29
 *
 */

public class TriWayWireImages
{
    private static EnumMap<Direction, Image> connectedImages;
    private static EnumMap<Direction, Image> disconnectedImages;
    
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
    
    public static Image getImage(TriWayWire wire)
    {
        if (wire.isConnected())
            return connectedImages.get(wire.getDirection());
        else
            return disconnectedImages.get(wire.getDirection());
    }


    
}