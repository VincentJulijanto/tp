package seedu.address.logic.parser;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;

import org.junit.jupiter.api.Test;

/**
 * Contains parser tests for {@code AddTimingCommandParser}.
 */
public class AddTimingCommandParserTest {

    private final AddTimingCommandParser parser = new AddTimingCommandParser();

    /**
     * Tests parse failures when required fields are missing.
     */
    @Test
    public void parse_missingFields_failure() {
        // missing all prefixed fields
        assertParseFailure(parser, "1",
                "Missing required field: dist/DISTANCE");

        // missing sec/
        assertParseFailure(parser, "1 dist/2.4km min/10",
                "Missing required field: sec/SECONDS");

        // missing min/
        assertParseFailure(parser, "1 dist/2.4km sec/30",
                "Missing required field: min/MINUTES");

        // missing dist/
        assertParseFailure(parser, "1 min/10 sec/30",
                "Missing required field: dist/DISTANCE");
    }

    /**
     * Tests parse failures when invalid values are provided.
     */
    @Test
    public void parse_invalidValues_failure() {
        // invalid distance
        assertParseFailure(parser, "1 dist/5km min/10 sec/30",
                "Invalid distance: supported distances are 400m, 2.4km, 10km, and 42km.");

        // negative minutes
        assertParseFailure(parser, "1 dist/2.4km min/-1 sec/30",
                "Invalid minutes: must be a non-negative integer.");

        // seconds out of range
        assertParseFailure(parser, "1 dist/2.4km min/10 sec/60",
                "Invalid seconds: must be a number from 0 to 59.99.");

        // zero total time
        assertParseFailure(parser, "1 dist/2.4km min/0 sec/0",
                "Invalid timing: total time must be greater than 0.");
    }

    /**
     * Tests parse failures when duplicate prefixes are provided.
     */
    @Test
    public void parse_duplicateFields_failure() {
        assertParseFailure(parser, "1 dist/2.4km dist/10km min/10 sec/30",
                "Invalid command: each field (dist/, min/, sec/) can only be provided once.");

        assertParseFailure(parser, "1 dist/2.4km min/10 min/11 sec/30",
                "Invalid command: each field (dist/, min/, sec/) can only be provided once.");

        assertParseFailure(parser, "1 dist/2.4km min/10 sec/30 sec/31",
                "Invalid command: each field (dist/, min/, sec/) can only be provided once.");
    }

    /**
     * Tests parse failures when the athlete index is invalid.
     */
    @Test
    public void parse_invalidIndex_failure() {
        assertParseFailure(parser, "abc dist/2.4km min/10 sec/30",
                "Invalid index: please provide a positive integer athlete index.");

        assertParseFailure(parser, "-1 dist/2.4km min/10 sec/30",
                "Invalid index: please provide a positive integer athlete index.");
    }
}
