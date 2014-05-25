/**
 * jNetwalk - simple netwalk game written in Java
 * 
 * @author Jakub Maleszewski
 */


import java.awt.EventQueue;
import java.util.concurrent.LinkedBlockingQueue;
import model.Model;
import view.View;
import controller.Controller;
import events.NetwalkEvent;

/**
 * @author Jakub Maleszewski
 * @since 2014-05-18
 * 
 *
 */
public class Main
{
    private static LinkedBlockingQueue<NetwalkEvent> eventQueue;     
    private static Model model;
    private static View view;
    private static Controller controller;
    
    /**
     * Main application method - creates model, view and controller
     */
    public static void main(String[] args)
    {    
        eventQueue = new LinkedBlockingQueue<NetwalkEvent>();
        model = new Model(view);
        view = new View(eventQueue);
        controller = new Controller(model, view, eventQueue);
    }

}
