package events;

/**
 * Abstract class representing application events
 * 
 * @author Jakub Maleszewski
 * @since 2014-05-25
 */

public abstract class NetwalkEvent
{
    /*
     * Abstract process() method describes what should be done in response to the event. It is called from the controller.
     */
    public abstract void process();
}
