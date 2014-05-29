package events;

import model.Model;
import view.View;

/**
 * Shows About message
 * @author Jakub Maleszewski
 * @since 2014-05-29
 */
public class ShowAboutEvent extends NetwalkEvent
{

    /**
     * Tell the view to create new message box
     */
    public void process(View view, Model model)
    {
        String message = "<html>jNetwalk<br>by Jakub Maleszewski</html>";

        view.showMessage(message);
    }

}
