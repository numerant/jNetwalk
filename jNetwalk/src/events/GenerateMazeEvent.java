package events;

import model.Model;
import view.View;

/**
 * @author Jakub Maleszewski
 * @since 
 *
 */
public class GenerateMazeEvent extends NetwalkEvent
{
    private Integer size;
    
    public GenerateMazeEvent(Integer size)
    {
        this.size = size;
    }
    
    public void process(View view, Model model)
    {
        // TODO Auto-generated method stub
        model.generateMaze(size);
    }

}
