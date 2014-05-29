package view.itemResources;

import java.awt.Image;

import java.io.IOException;
import java.util.EnumMap;

import javax.imageio.ImageIO;

import model.MazeMockItem;
import model.MazeItems.Client;
import model.MazeMockItem.Direction;

/**
 * Class containing image resources for {@link Client}
 * 
 * @author Jakub Maleszewski
 * @since 2014-05-29
 *
 */

public class ClientImages
{
    private static EnumMap<Direction, Image> connectedImages;
    private static EnumMap<Direction, Image> disconnectedImages;
    
    static
    {
        connectedImages = new EnumMap<Direction, Image>(Direction.class);
        disconnectedImages = new EnumMap<Direction, Image>(Direction.class);
        try
        {
            connectedImages.put(Direction.UP, ImageIO.read(MazeMockItem.class.getClassLoader().getResource("resources/img/client_up_connected.png")));
            connectedImages.put(Direction.DOWN, ImageIO.read(MazeMockItem.class.getClassLoader().getResource("resources/img/client_down_connected.png")));
            connectedImages.put(Direction.LEFT, ImageIO.read(MazeMockItem.class.getClassLoader().getResource("resources/img/client_left_connected.png")));
            connectedImages.put(Direction.RIGHT, ImageIO.read(MazeMockItem.class.getClassLoader().getResource("resources/img/client_right_connected.png")));
            
            disconnectedImages.put(Direction.UP, ImageIO.read(MazeMockItem.class.getClassLoader().getResource("resources/img/client_up_disconnected.png")));
            disconnectedImages.put(Direction.DOWN, ImageIO.read(MazeMockItem.class.getClassLoader().getResource("resources/img/client_down_disconnected.png")));
            disconnectedImages.put(Direction.LEFT, ImageIO.read(MazeMockItem.class.getClassLoader().getResource("resources/img/client_left_disconnected.png")));
            disconnectedImages.put(Direction.RIGHT, ImageIO.read(MazeMockItem.class.getClassLoader().getResource("resources/img/client_right_disconnected.png")));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    
    public static Image getImage(Client client)
    {
        if (client.isConnected())
            return connectedImages.get(client.getDirection());
        else
            return disconnectedImages.get(client.getDirection());
    }


    
}