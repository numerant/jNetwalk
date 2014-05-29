package model.MazeItems;

import java.awt.Image;

import view.itemResources.ClientImages;
import model.MazeMockItem;

/**
 * Represents client items on the maze
 * Contains associated images within static EnumMap
 * 
 * @author Jakub Maleszewski
 * @since 2014-05-26
 */
public class Client extends MazeMockItem
{
    /**
     * Creates object with a specific direction
     */
    public Client(Direction direction)
    {
        this.direction = direction;
        isConnected = false;
    }

    /**
     * See {@link MazeMockItem}
     */
    public Image getImage()
    {
        return ClientImages.getImage(this);
    }

}
