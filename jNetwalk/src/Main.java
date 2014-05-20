/**
 * jNetwalk - simple netwalk game written in Java
 * 
 * @author Jakub Maleszewski
 */


import java.awt.EventQueue;
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
    private static Model model;
    private static View view;
    private static Controller controller;
    
    /**
     * Main application method - creates model, view and controller
     */
    public static void main(String[] args)
    {    
        model = new Model(view);
        view = new View();
        controller = new Controller(model, view);
    }

}
