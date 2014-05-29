/**
 * jNetwalk - simple netwalk game written in Java
 * 
 * @author Jakub Maleszewski
 */

import java.util.concurrent.LinkedBlockingQueue;

import model.Model;
import view.View;
import controller.Controller;
import events.NetwalkEvent;

/**
 * Main application class.
 * Takes care of creating model, view and controller objects.
 * Creates event queue and connects it to other objects
 * @author Jakub Maleszewski
 * @since 2014-05-18
 */
public class Main
{
    private static LinkedBlockingQueue<NetwalkEvent> eventQueue;     
    private static Model model;
    private static View view;
    /**
     * Main application method - creates model, view and controller
     */
    public static void main(String[] args)
    {    
        eventQueue = new LinkedBlockingQueue<NetwalkEvent>();
        view = new View(eventQueue);
        model = new Model(view);
        new Controller(model, view, eventQueue);

    }

}
