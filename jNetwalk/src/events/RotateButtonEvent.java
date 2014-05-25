package events;

import view.View;
import model.Model;

/**
 * Class representing event of button rotation
 * 
 * @author Jakub Maleszewski
 * @since 2014-05-25
 */

public final class RotateButtonEvent extends NetwalkEvent
{
    private Integer xPosition;
    private Integer yPosition;
    
    public RotateButtonEvent(Integer xPosition, Integer yPosition)
    {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
    }
    
    public void process(final View view, final Model model)
    {
        view.showMessage(xPosition.toString() + " " + yPosition.toString());
    }
}
