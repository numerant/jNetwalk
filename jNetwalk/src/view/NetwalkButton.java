package view;

import javax.swing.JButton;

/**
 * Internal class for representing a button aware of its position in a maze
 * @author Jakub Maleszewski
 * @since 2014-05-18
 */

public class NetwalkButton extends JButton
{
    private static final long serialVersionUID = 1L;    // to suppress warnings
    private Integer xPosition;
    private Integer yPosition;
    
    /**
     * Constructor - sets button caption and maze position
     */
    public NetwalkButton(final String string, final Integer xPosition, final Integer yPosition)
    {
        super(string);
        this.xPosition = xPosition;
        this.yPosition = yPosition;
    }
}