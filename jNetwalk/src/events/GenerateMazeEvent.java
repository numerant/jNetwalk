package events;

import model.Model;
import view.View;

/**
 * Maze generation event
 * @author Jakub Maleszewski
 * @since 2014-05-26
 */
public class GenerateMazeEvent extends NetwalkEvent
{
    private Integer size;
    
    public GenerateMazeEvent(Integer size)
    {
        this.size = size;
    }
    
    public void process(final View view, final Model model)
    {
        model.generateMaze(size);
    }

}
