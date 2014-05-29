package model.MazeItems;

import java.awt.Image;
import view.itemResources.ServerImages;
import model.MazeMockItem;

/**
 * Represents server item on the maze
 * Contains associated images within static EnumMap
 * 
 * @author Jakub Maleszewski
 * @since 2014-05-25
 */
public class Server extends MazeMockItem
{
    /**
     * Creates object with a specific direction
     */
    public Server(Direction direction)
    {
        this.direction = direction;
        isConnected = true;
    }

    /**
     * See {@link MazeMockItem}
     */
    public Image getImage()
    {
        return ServerImages.getImage(this);
    }
}
