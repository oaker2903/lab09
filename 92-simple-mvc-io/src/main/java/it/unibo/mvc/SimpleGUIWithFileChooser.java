package it.unibo.mvc;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
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
 */
public final class SimpleGUIWithFileChooser {
    
    private static final int PROPORTION = 4;
    private static final String TITLE = "A very simple GUI application";
    private final JFrame frame = new JFrame(TITLE);
    private final Controller controller = new Controller();
    
    public SimpleGUIWithFileChooser() {    
        final JPanel canvas = new JPanel();
        canvas.setLayout(new BorderLayout());
        frame.setContentPane(canvas);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        final JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        final JButton button = new JButton("SAVE");

        final JTextArea textArea = new JTextArea();

        JPanel browsePanel = new JPanel();
        browsePanel.setLayout(new BorderLayout());

        final JTextField browseTextField = new JTextField("file selected"); 
        browseTextField.setEditable(false);
        final JButton browseButton = new JButton("SELECT FILE");

        browsePanel.add(browseTextField, BorderLayout.LINE_START);

        browsePanel.add(browseButton, BorderLayout.LINE_END);

        canvas.add(textArea, 0);
        canvas.add(button, BorderLayout.SOUTH);

        canvas.add(browsePanel, BorderLayout.NORTH);

        button.addActionListener(
            new ActionListener() {
                @Override 
                public void actionPerformed(final ActionEvent e) {
                    IO.println("hai cliccato bottone SALVA"); 
                }
            }
        );



        browseButton.addActionListener(
            new ActionListener() {
                @Override 
                public void actionPerformed(final ActionEvent e) {
                    IO.println("hai cliccato browseButton");
                    JFileChooser chooser = new JFileChooser();  //gli passo il file del controller??
                    if(chooser.showSaveDialog(browseButton) == chooser.APPROVE_OPTION) {
                        controller.setFile(chooser.getSelectedFile());
                        browseTextField.setText(controller.getFile().toString());
                    } else if (chooser.showSaveDialog(browseButton) != JFileChooser.CANCEL_OPTION) {
                        JOptionPane.showMessageDialog(browseButton, "an error has occurred", "Error", JOptionPane.ERROR_MESSAGE);
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
