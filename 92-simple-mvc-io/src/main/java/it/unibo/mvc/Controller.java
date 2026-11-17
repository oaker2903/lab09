package it.unibo.mvc;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

/**
 * Application controller. Performs the I/O.
 */
public class Controller {
    private File file;
    /*
     * System.getProperty("user.home")
     * + File.separator
     * + BadIOGUI.class.getSimpleName() + ".txt";
     */

    public Controller() {
        this.file = new File(System.getProperty("user.home") + File.separator + "output.txt");
    }

    public void setFile(final File file) {
        this.file = file;
    }

    public File getFile() {
        return this.file;
    }

    public String getPathString() {
        return this.file.getPath();
    }

    public void writeString(final String string) {
        //Devo scrivere string in this.file che è un File e guardo quidni metodi
        if(this.file.exists()) {
            this.file.setWritable(true);
            try ( final OutputStream output = new FileOutputStream (this.file)) {
                output.write(string.getBytes(StandardCharsets.UTF_8));
            } catch ( final IOException e ){
                IO.println (" Something went wrong !") ;
            }
        } else {
            throw new IllegalArgumentException("file non esiste");
        }
    }  
} 
