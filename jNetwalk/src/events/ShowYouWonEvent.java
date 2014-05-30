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
    private Integer time;
    
    public ShowYouWonEvent (final Integer moveCount, final Integer time)
    {
        this.moveCount = moveCount;
        this.time = time;
    }
    
    /**
     * Tell the view to create new message box
     */
    public void process(View view, Model model)
    {
        String message = "<html>You won this game!<br>Moves: " + moveCount.toString() + "<br>Time: "+ time.toString() +"</html>";
        String title = "Congratulations!";

        view.showMessage(message, title);
    }

}
