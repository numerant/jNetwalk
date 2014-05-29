package model.MazeItems;

import java.awt.Image;
import view.itemResources.StraightWireImages;
import model.MazeMockItem;


/**
 * Represents straight (|) wire on the maze
 * 
 * @author Jakub Maleszewski
 * @since 2014-05-26
 */
public class StraightWire extends Wire
{
    /**
     * Creates object with a specific direction
     */
    public StraightWire(Direction direction)
    {
        this.direction = direction;
        isConnected = false;
    }

    /**
     * See {@link MazeMockItem}
     */
    public Image getImage()
    {
        return StraightWireImages.getImage(this);
    }
}
