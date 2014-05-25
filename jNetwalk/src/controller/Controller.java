package controller;

import java.util.concurrent.BlockingQueue;

import events.NetwalkEvent;
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
    private BlockingQueue<NetwalkEvent> eventQueue;
    private Model model;
    private View view;
    
    
    /**
     * Constructor - sets references to model and view
     */
    public Controller(final Model model, final View view, final BlockingQueue<NetwalkEvent> eventQueue)
    {
        this.model = model;
        this.view = view;
        this.eventQueue = eventQueue;
    }
    
    /**
     * Event queue loop - waits for a new event in the queue, takes it and starts processing method
     */    
    public void eventQueueLoop()      //TODO Maybe private method invoked in constructor would be better?
    {
        while(true)
        {
            
        }
    }
}
