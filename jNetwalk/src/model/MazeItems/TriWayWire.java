package model.MazeItems;

import java.awt.Image;
import view.itemResources.TriWayWireImages;
import model.MazeMockItem;

/**
 * Represents tri-way (-|) wire on the maze
 * 
 * @author Jakub Maleszewski
 * @since 2014-05-26
 */
public class TriWayWire extends Wire
{
    /**
     * Creates object with a specific direction
     */
    public TriWayWire(Direction direction)
    {
        this.direction = direction;
        isConnected = false;
    }

    /**
     * See {@link MazeMockItem}
     */
    public Image getImage()
    {
        return TriWayWireImages.getImage(this);
    }
}
