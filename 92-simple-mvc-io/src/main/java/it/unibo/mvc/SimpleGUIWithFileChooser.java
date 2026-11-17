package it.unibo.mvc;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.awt.event.ActionEvent;
/**
 * A very simple program using a graphical interface with file chooser.
 *
 * <p>
 * The window contains a text area, a "SAVE" button that writes the content
 * of the text area on the current file, and a "SELECT FILE" button that lets
 * the user choose the destination file through a {@link JFileChooser}.
 */

public final class SimpleGUIWithFileChooser {
    private static final int PROPORTION = 4;
    private static final String TITLE = "A very simple GUI application";
    private final JFrame frame = new JFrame(TITLE);
    private final Controller controller = new Controller();
    /**
     * Builds the GUI and wires the listeners of its buttons.
     */

    public SimpleGUIWithFileChooser() {
        final JPanel canvas = new JPanel();
        canvas.setLayout(new BorderLayout());
        frame.setContentPane(canvas);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        final JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        final JButton button = new JButton("SAVE");

        final JTextArea textArea = new JTextArea();

        final JPanel browsePanel = new JPanel();
        browsePanel.setLayout(new BorderLayout());

        final JTextField browseTextField = new JTextField("file selected"); 
        browseTextField.setEditable(false);
        final JButton browseButton = new JButton("SELECT FILE");

        browsePanel.add(browseTextField, BorderLayout.LINE_START);
        browsePanel.add(browseButton, BorderLayout.LINE_END);
        canvas.add(textArea, 0);
        canvas.add(button, BorderLayout.SOUTH);
        canvas.add(browsePanel, BorderLayout.NORTH);

        browseButton.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(final ActionEvent e) {
                    final JFileChooser chooser = new JFileChooser();
                    final int result = chooser.showSaveDialog(frame);
                    if (result == JFileChooser.APPROVE_OPTION) {
                        final File selected = chooser.getSelectedFile();
                        controller.setFile(selected);
                        browseTextField.setText(selected.getPath());
                    } else if (chooser.showSaveDialog(browseButton) != JFileChooser.CANCEL_OPTION) {
                        JOptionPane.showMessageDialog(browseButton, "an error has occurred", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        );

        button.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(final ActionEvent ignored) {
                    final String text = textArea.getText();
                    try (PrintStream ps = new PrintStream(controller.getPathString(), StandardCharsets.UTF_8)) {
                        ps.print(text);
                    } catch (final IOException e) {
                        JOptionPane.showMessageDialog(frame, e, "Error", JOptionPane.ERROR_MESSAGE);
                        // e.printStackTrace();
                    }
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
        frame.pack();
        frame.setVisible(true);
    }

    /**
     * Launches the application.
     */
    public static void main() {
       new SimpleGUIWithFileChooser().display();
    }
}
