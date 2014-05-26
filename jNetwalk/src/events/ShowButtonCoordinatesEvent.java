package events;

import view.View;
import model.Model;

/**
 * Class representing event of showing a message box.
 * 
 * @author Jakub Maleszewski
 * @since 2014-05-25
 */

public final class ShowButtonCoordinatesEvent extends NetwalkEvent
{
    private Integer xPosition;
    private Integer yPosition;
    
    public ShowButtonCoordinatesEvent(Integer xPosition, Integer yPosition)
    {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
    }
    
    public void process(final View view, final Model model)
    {
        view.showMessage(xPosition.toString() + " " + yPosition.toString());
    }
}
