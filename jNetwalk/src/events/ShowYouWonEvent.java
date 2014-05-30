package events;

import model.Model;
import view.View;

/**
 * Shows About message
 * @author Jakub Maleszewski
 * @since 2014-05-29
 */
public class ShowYouWonEvent extends NetwalkEvent
{
    private Integer moveCount;
    
    public ShowYouWonEvent (final Integer moveCount)
    {
        this.moveCount = moveCount;
    }
    
    /**
     * Tell the view to create new message box
     */
    public void process(View view, Model model)
    {
        String message = "<html>You won this game!<br>Moves: " + moveCount.toString() +"</html>";
        String title = "Congratulations!";

        view.showMessage(message, title);
        view.hideMazePanel();
    }

}
