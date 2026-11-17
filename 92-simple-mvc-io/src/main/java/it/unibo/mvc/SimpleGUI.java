package it.unibo.mvc;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JFrame;
import java.awt.event.ActionEvent;
/**
 * A very simple program using a graphical interface.
 * 
 */

public final class SimpleGUI {

    private static final int PROPORTION = 4;
    private static final String TITLE = "A very simple GUI application";
    private final JFrame frame = new JFrame(TITLE);

    /**
     * A very simple program using a graphical interface with file chooser.
     * 
     */
    public SimpleGUI() {
        final JPanel canvas = new JPanel();
        canvas.setLayout(new BorderLayout());
        frame.setContentPane(canvas);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        final JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        final JButton button = new JButton("SAVE");

        final JTextArea textArea = new JTextArea();

        canvas.add(textArea, 0);
        canvas.add(button, BorderLayout.SOUTH);
        button.addActionListener(
            new ActionListener() {
                @Override 
                public void actionPerformed(final ActionEvent e) {
                    IO.println("hai cliccato bottone SALVA"); 
                }
            }
        );
    }

    private void display() {
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int sw = (int) screen.getWidth();
        final int sh = (int) screen.getHeight();
        frame.setSize(sw / PROPORTION, sh / PROPORTION);

        frame.setLocationByPlatform(true);
        // frame.pack();
        frame.setVisible(true);
    }

    /**
     * Launches the application.
     */
    public static void main() {
       new SimpleGUI().display();
    }
}
