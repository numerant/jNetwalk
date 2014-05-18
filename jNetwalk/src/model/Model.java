package model;

import view.View;


/**
 * Class implementing model in MVC pattern
 * 
 * @author Jakub Maleszewski
 * @since 2014-05-18
 *
 */

public class Model
{
    private View view;
    
    /**
     * Constructor - sets reference to view
     */
    public Model(final View view)
    {
        this.view = view;
    }
}
