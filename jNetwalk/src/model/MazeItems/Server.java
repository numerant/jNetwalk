package model.MazeItems;

import model.MazeItem;

/**
 * @author Jakub Maleszewski
 * @since 2014-05-25
 *
 */
public class Server extends MazeItem
{
    Server()
    {
            /* Server is always connected */
        isConnected = true;
        
        connectedImagePaths.put(Direction.UP, "/resources/img/server_up.png");
        connectedImagePaths.put(Direction.DOWN, "/resources/img/server_down.png");
        connectedImagePaths.put(Direction.LEFT, "/resources/img/server_left.png");
        connectedImagePaths.put(Direction.RIGHT, "/resources/img/server_right.png");
    }
}
