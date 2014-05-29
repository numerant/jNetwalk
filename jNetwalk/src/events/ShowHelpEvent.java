package events;

import model.Model;
import view.View;

/**
 * Shows the help dialog
 * 
 * @author Jakub Maleszewski
 * @since 2014-05-29
 */
public class ShowHelpEvent extends NetwalkEvent
{

    /**
     * Tell the view to show help window
     */
    public void process(View view, Model model)
    {
        view.showHelp();
    }

}
