package model.MazeItems;

import java.awt.Image;

import view.itemResources.NinetyDegreeWireImages;
import model.MazeMockItem;

/**
 * Represents 90-degree (L) wire on the maze
 * 
 * @author Jakub Maleszewski
 * @since 2014-05-26
 */
public class NinetyDegreeWire extends Wire
{
    /**
     * Creates object with a specific direction
     */
    public NinetyDegreeWire(Direction direction)
    {
        this.direction = direction;
        isConnected = false;
    }

    /**
     * See {@link MazeMockItem}
     */
    public Image getImage()
    {
        return NinetyDegreeWireImages.getImage(this);
    }
}
