package model.MazeItems;

import java.util.EnumMap;

import model.MazeItem;
import model.MazeItem.Direction;

/**
 * @author Jakub Maleszewski
 * @since 2014-05-25
 *
 */
public class Server extends MazeItem
{
    
    public Server()
    {
            /* Server is always connected */
        isConnected = true;
                //TODO IT'S SUPER INEFFICIENT! CHANGE THIS BEHAVIOR RIGHT AWAWY
        connectedImagePaths = new EnumMap<Direction, String>(Direction.class);
                
        connectedImagePaths.put(Direction.UP, "/resources/img/server_up.png");
        connectedImagePaths.put(Direction.DOWN, "/resources/img/server_down.png");
        connectedImagePaths.put(Direction.LEFT, "/resources/img/server_left.png");
        connectedImagePaths.put(Direction.RIGHT, "/resources/img/server_right.png");
    }
}
