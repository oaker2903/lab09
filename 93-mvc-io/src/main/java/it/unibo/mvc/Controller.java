package it.unibo.mvc;
import java.util.List;
/**
 * This interface represents a controller in the MVC pattern,
 * responsible for managing the flow of data between the model and the view.
 */
public interface Controller {
    //It considers only the standard output, and it is able to print on it.


    /**
     * A method for setting the next string to print. Null values are not
     * acceptable, and an exception should be produced
     * @param next the next string to print
     * @throws IllegalArgumentException if {@code next} is {@code null}
       */
    public void setNextString(String next);


    /**
     * A method for getting the next string to print
     * 
     * @return the next string to print, or {@code null} if it has not been set 
     */
    public String getNextString();


    
    /**
     * Returns the history of the printed strings.
     * 
     * @return a {@code List} of {@link String}s, in printing order 
     */
    public List<String> getPrintedStringHistory();

    /**
     * Prints the current string on the standard output.
     *
     * @throws IllegalStateException If the current string is unset
    */
    public void printCurrentString();

}
