package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

/**
 * Represents a 2.4km run timing record.
 */
public class RunTiming {

    private static final double DISTANCE = 2.4;

    private final int minutes;
    private final double seconds;

    public RunTiming(int minutes, double seconds) {
        requireNonNull(minutes);
        requireNonNull(seconds);

        this.minutes = minutes;
        this.seconds = seconds;
    }

    public int getMinutes() {
        return minutes;
    }

    public double getSeconds() {
        return seconds;
    }

    public double getDistance() {
        return DISTANCE;
    }

    public double getTotalSeconds() {
        return minutes * 60 + seconds;
    }

    public String getPrintFormat() {
        return DISTANCE + ", " + minutes + "min " + seconds + "s";
    }

    @Override
    public String toString() {
        return DISTANCE + "km in " + minutes + "min " + seconds + "s";
    }
}
