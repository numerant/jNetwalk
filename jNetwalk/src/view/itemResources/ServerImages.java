package view.itemResources;

import java.awt.Image;
import java.io.IOException;
import java.util.EnumMap;

import javax.imageio.ImageIO;

import model.MazeMockItem;
import model.MazeItems.Server;
import model.MazeMockItem.Direction;

/**
 * Class containing image resources for {@link Server}
 * 
 * @author Jakub Maleszewski
 * @since 2014-05-29
 *
 */

public class ServerImages
{
    private static EnumMap<Direction, Image> connectedImages;
    
    static
    {
        connectedImages = new EnumMap<Direction, Image>(Direction.class);
        try
        {
            connectedImages.put(Direction.UP, ImageIO.read(MazeMockItem.class.getClassLoader().getResource("resources/img/server_up.png")));
            connectedImages.put(Direction.DOWN, ImageIO.read(MazeMockItem.class.getClassLoader().getResource("resources/img/server_down.png")));
            connectedImages.put(Direction.LEFT, ImageIO.read(MazeMockItem.class.getClassLoader().getResource("resources/img/server_left.png")));
            connectedImages.put(Direction.RIGHT, ImageIO.read(MazeMockItem.class.getClassLoader().getResource("resources/img/server_right.png")));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    
    public static Image getImage(Server server)
    {
            return connectedImages.get(server.getDirection());
    }


    
}