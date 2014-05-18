package controller;

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
    private Model model;
    private View view;
    
    
    /**
     * Constructor - sets references to model and view
     */
    public Controller(final Model model, final View view)
    {
        this.model = model;
        this.view = view;
    }
    
}
