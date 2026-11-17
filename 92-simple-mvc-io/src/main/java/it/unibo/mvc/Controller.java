package it.unibo.mvc;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

/**
 * Application controller. Performs the I/O.
 *
 * <p>
 * The controller keeps track of the file on which the application writes.
 * By default the file is {@code output.txt} located in the home directory
 * of the current user.
 */
public class Controller {

    private File file;

    /**
     * Creates a new controller whose current file is {@code output.txt}
     * in the user's home directory.
     */
    public Controller() {
        this.file = new File(System.getProperty("user.home") + File.separator + "output.txt");
    }

    /**
     * Sets the file on which the controller will write.
     *
     * @param file the new current file
     */
    public void setFile(final File file) {
        this.file = file;
    }

    /**
     * Returns the file currently in use.
     *
     * @return the current file
     */
    public File getFile() {
        return this.file;
    }

    /**
     * Returns the path of the current file as a string.
     *
     * @return the path of the current file
     * @see File#getPath()
     */
    public String getPathString() {
        return this.file.getPath();
    }

    /**
     * Writes the given string to the current file, encoded in UTF-8.
     * The previous content of the file is overwritten.
     *
     * @param string the text to write
     * @throws IllegalArgumentException if the current file does not exist
     */
    public void writeString(final String string) {
        if (this.file.exists()) {
            this.file.setWritable(true);
            try (OutputStream output = new FileOutputStream(this.file)) {
                output.write(string.getBytes(StandardCharsets.UTF_8));
            } catch (final IOException e) {
                IO.println(" Something went wrong !");
            }
        } else {
            throw new IllegalArgumentException("file doesn't exists");
        }
    }
}
