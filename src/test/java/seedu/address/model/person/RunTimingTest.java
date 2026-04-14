package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class RunTimingTest {

    @Test
    public void getFormattedDuration_wholeNumberSeconds_omitsDecimalSuffix() {
        RunTiming timing = new RunTiming("2.4km", 10, 30.0);

        assertEquals("10min 30s", timing.getFormattedDuration());
        assertEquals("2.4km, 10min 30s", timing.getPrintFormat());
        assertEquals("2.4km in 10min 30s", timing.toString());
    }

    @Test
    public void getFormattedDuration_fractionalSeconds_keepsDecimalPart() {
        RunTiming timing = new RunTiming("400m", 0, 54.32);

        assertEquals("0min 54.32s", timing.getFormattedDuration());
        assertEquals("400m, 0min 54.32s", timing.getPrintFormat());
        assertEquals("400m in 0min 54.32s", timing.toString());
    }

    @Test
    public void getFormattedDuration_zeroSeconds_omitsDecimalSuffix() {
        RunTiming timing = new RunTiming("10km", 38, 0.0);

        assertEquals("38min 0s", timing.getFormattedDuration());
    }
}
