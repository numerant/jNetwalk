package controller;

import java.util.concurrent.BlockingQueue;
import model.Model;
import view.View;

/**
 * Class implementing controller in MVC pattern
 * 
 * @author Jakub Maleszewski
 * @since 2014-05-18
 *
 */

public class Controller
{
    private BlockingQueue<Object> eventQueue;
    private Model model;
    private View view;
    
    
    /**
     * Constructor - sets references to model and view
     */
    public Controller(final Model model, final View view, final BlockingQueue<Object> eventQueue)
    {
        this.model = model;
        this.view = view;
        this.eventQueue = eventQueue;
    }
    
}
