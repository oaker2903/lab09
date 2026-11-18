package it.unibo.mvc;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple implementation of the {@link Controller} interface.
 */
public final class SimpleController implements Controller {

    private String nextString;
    private final List<String> history = new ArrayList<>();

    @Override
    public void setNextString(final String next) {
        if (next == null) {
            throw new IllegalArgumentException("next string cannot be null");
        } else {
            this.nextString = next;
        }
    }

    @Override
    public String getNextString() {
        return this.nextString;
    }

    @Override
    public List<String> getPrintedStringHistory() {
        return List.copyOf(this.history);
    }

    @Override
    public void printCurrentString() {
        if (this.nextString == null) {
            throw new IllegalStateException("currest string is unset");
        } else {
            /*at this point it prints on ST and save it in the history */
            IO.println(this.nextString);
            history.add(nextString);
        }
    }
}
