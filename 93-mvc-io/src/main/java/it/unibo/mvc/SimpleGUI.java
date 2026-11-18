package it.unibo.mvc;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A very simple program using a graphical interface.
 *
 */
public final class SimpleGUI {

    private static final int PROPORTION = 3;
    private static final String TITLE = "A very simple GUI application";
    private final JFrame frame = new JFrame(TITLE);
    private final SimpleController controller = new SimpleController();

    /**
     * A very simple program using a graphical interface.
     * 
     */
    public SimpleGUI() {
        final JPanel canvas = new JPanel();
        canvas.setLayout(new BorderLayout());
        frame.setContentPane(canvas);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        final JTextField textField = new JTextField();
        final JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        final JPanel buttonPanel = new JPanel();
        /*modified instead of usign BorderLayout I prefer GridLayout so the buttons have the same width */
        buttonPanel.setLayout(new GridLayout(1,2));

        final JButton print = new JButton("print string");
        final JButton showHistory = new JButton("show history");

        buttonPanel.add(print);
        buttonPanel.add(showHistory);

        /*adding to canvas*/
        canvas.add(textField, BorderLayout.NORTH);
        canvas.add(textArea, BorderLayout.CENTER);
        canvas.add(buttonPanel, BorderLayout.SOUTH);
        /*handlers */

        showHistory.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(final ActionEvent e) {
                    /*I don't want visualize it as an array but as a vertical list */
                    textArea.setText(String.join("\n", controller.getPrintedStringHistory()));
                }
            }
        );

        print.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(final ActionEvent e) {
                    final String string = textField.getText();
                    if (string.isEmpty()) {
                        JOptionPane.showMessageDialog(frame, "The string cannot be empty", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    controller.setNextString(string);
                    controller.printCurrentString();
                } 
            }
        );
    }

    public void display() {
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int sh = (int) screen.getHeight();    
        final int sw = (int) screen.getWidth();
        frame.setSize(sw / PROPORTION, sh / PROPORTION);
        frame.setLocationByPlatform(true);
        // frame.pack();
        frame.setVisible(true);
    }

    public void main() {
        new SimpleGUI().display();
    }
}
