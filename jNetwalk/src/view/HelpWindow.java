package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingConstants;

import events.RotateButtonEvent;

public class HelpWindow extends JDialog
{

    private final JPanel contentPanel = new JPanel();

    
    public HelpWindow(final JFrame mainWindow)
    {
        super(mainWindow, "How to play", ModalityType.MODELESS);
        createDialog();
        showDialog();
    }
    
    /**
     * Show the dialog
     */
    private void showDialog()
    {
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setLocationByPlatform(true);
        setVisible(true);
    }

    /**
     * Create the dialog
     */
    private void createDialog()
    {
        setSize(450, 235);
        
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(new BorderLayout(0, 0));
        {
            JLabel lblHowToPlay = new JLabel("How to play");
            lblHowToPlay.setHorizontalAlignment(SwingConstants.CENTER);
            lblHowToPlay.setFont(new Font("Dialog", Font.BOLD, 18));
            contentPanel.add(lblHowToPlay, BorderLayout.NORTH);
        }
        {
            JLabel lblNewLabel = new JLabel("<html>The goal is to connect all the computers to the Internet by rotating elements on the board. <br>\nClicking on the element rotates it counter clockwise. Wires and computers connected to the server (marked as a globe) glow.<br>\nTo win, you need to connect all elements on the board (computers and wires) to the Internet.</html>");
            lblNewLabel.setFont(new Font("Dialog", Font.PLAIN, 14));
            contentPanel.add(lblNewLabel, BorderLayout.CENTER);
        }
        {
            JPanel buttonPane = new JPanel();
            buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
            getContentPane().add(buttonPane, BorderLayout.SOUTH);
            {
                JButton okButton = new JButton("OK");
                okButton.setActionCommand("OK");
                okButton.addActionListener(new ActionListener()
                {                 
                    public void actionPerformed(ActionEvent event)
                    {
                        dispose();
                    }
                });
                buttonPane.add(okButton);
                getRootPane().setDefaultButton(okButton);
            }
        }
    }

}
