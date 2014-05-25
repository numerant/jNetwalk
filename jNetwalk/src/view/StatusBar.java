package view;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * Class responsible for creating status bar in main application window.
 * Status bar contains actual score and game duration.
 * 
 * @author Jakub Maleszewski
 * @since 2014-05-25
 */

public class StatusBar
{
    private View view;
        /* Status bar items */
    private JPanel statusBarPanel;
    private JLabel timeLabel;
    private JLabel timeValue;
    private JLabel moveCountLabel;
    private JLabel moveCountValue;
    
    StatusBar(final View view)
    {
        this.view = view;
        createStatusBarItems();
        showStatusBar();
    }
    
    /**
     * Creates status bar with its items
     */
    void createStatusBarItems()
    {
        statusBarPanel = new JPanel();
        statusBarPanel.setLayout(new BoxLayout(statusBarPanel, BoxLayout.X_AXIS));
        
        timeLabel = new JLabel("Time:");
        statusBarPanel.add(timeLabel);
        
        timeValue = new JLabel("");
        timeValue.setAlignmentX(Component.CENTER_ALIGNMENT);
        statusBarPanel.add(timeValue);
        
        statusBarPanel.add(Box.createHorizontalGlue());
        
        moveCountLabel = new JLabel("Move count:");
        moveCountLabel.setHorizontalAlignment(SwingConstants.LEFT);
        statusBarPanel.add(moveCountLabel);
        
        moveCountValue = new JLabel("");
        statusBarPanel.add(moveCountValue);
    }
    
    /**
     * Adds status bar panel to the main window
     */
    void showStatusBar()
    {
        view.addPanel(statusBarPanel, BorderLayout.SOUTH);
    }
}
