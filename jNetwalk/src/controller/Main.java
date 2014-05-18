/**
 * jNetwalk - simple netwalk game written in Java
 * 
 * @author Jakub Maleszewski
 */

package controller;

import java.awt.EventQueue;
import model.Model;
import view.View;

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
        EventQueue.invokeLater(new Runnable()       // TODO Check if invokeAndWait isn't better than this
        {
            public void run() 
            {
                Model model = new Model();
                View view = new View();
                Controller controller = new Controller();
            }
        });
    }

}
