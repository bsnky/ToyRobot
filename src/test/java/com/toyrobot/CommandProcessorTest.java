package com.toyrobot;

import java.util.List;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CommandProcessorTest {

    private CommandProcessor newProcessor() {
        Table table = new Table(5, 5);
        Robot robot = new Robot(table);
        return new CommandProcessor(robot);
    }

    @Test
    void exampleA() {
        CommandProcessor p = newProcessor();
        List<String> out = p.processAll(List.of(
                "PLACE 0,0,NORTH",
                "MOVE",
                "REPORT"
        ));
        assertEquals(List.of("0,1,NORTH"), out);
    }

    @Test
    void exampleB() {
        CommandProcessor p = newProcessor();
        List<String> out = p.processAll(List.of(
                "PLACE 0,0,NORTH",
                "LEFT",
                "REPORT"
        ));
        assertEquals(List.of("0,0,WEST"), out);
    }

    @Test
    void exampleC() {
        CommandProcessor p = newProcessor();
        List<String> out = p.processAll(List.of(
                "PLACE 1,2,EAST",
                "MOVE",
                "MOVE",
                "LEFT",
                "MOVE",
                "REPORT"
        ));
        assertEquals(List.of("3,3,NORTH"), out);
    }

    @Test
    void ignoresCommandsUntilValidPlace() {
        CommandProcessor p = newProcessor();
        List<String> out = p.processAll(List.of(
                "MOVE",
                "LEFT",
                "REPORT",
                "PLACE 0,0,NORTH",
                "REPORT"
        ));
        assertEquals(List.of("0,0,NORTH"), out);
    }

    @Test
    void invalidPlaceIsIgnoredAndDoesNotActivateRobot() {
        CommandProcessor p = newProcessor();
        List<String> out = p.processAll(List.of(
                "PLACE 9,9,NORTH",
                "MOVE",
                "REPORT",
                "PLACE 0,0,NORTH",
                "REPORT"
        ));
        assertEquals(List.of("0,0,NORTH"), out);
    }

    @Test
    void moveThatWouldFallIsIgnored() {
        CommandProcessor p = newProcessor();
        List<String> out = p.processAll(List.of(
                "PLACE 0,0,SOUTH",
                "MOVE",
                "REPORT",
                "LEFT",
                "MOVE",
                "REPORT"
        ));
        // first MOVE ignored, then LEFT -> EAST, MOVE -> (1,0)
        assertEquals(List.of("0,0,SOUTH", "1,0,EAST"), out);
    }

    @Test
    void supportsCaseInsensitiveAndSpaces() {
        CommandProcessor p = newProcessor();
        List<String> out = p.processAll(List.of(
                "  place  1, 2, east ",
                "mOvE",
                "REPORT"
        ));
        assertEquals(List.of("2,2,EAST"), out);
    }

    @Test
    void unknownCommandsAreIgnored() {
        CommandProcessor p = newProcessor();
        List<String> out = p.processAll(List.of(
                "PLACE 0,0,NORTH",
                "JUMP",
                "SPIN 3",
                "REPORT"
        ));
        assertEquals(List.of("0,0,NORTH"), out);
    }
    
}
