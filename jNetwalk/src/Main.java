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

/**
 * @author Jakub Maleszewski
 * @since 2014-05-18
 * 
 *
 */
public class Main
{
    private static LinkedBlockingQueue<Object> eventQueue;      //TODO Replace Object with "Event"
    private static Model model;
    private static View view;
    private static Controller controller;
    
    /**
     * Main application method - creates model, view and controller
     */
    public static void main(String[] args)
    {    
        eventQueue = new LinkedBlockingQueue<Object>();
        model = new Model(view);
        view = new View(eventQueue);
        controller = new Controller(model, view);
    }

}
