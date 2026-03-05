package com.toyrobot;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

public final class CommandProcessor {

    private final Robot robot;

    public CommandProcessor(Robot robot) {
        this.robot = robot;
    }

    /**
     * Processes a single command line.
     * @return an output line (only for REPORT), or empty if nothing should be printed.
     */
    public Optional<String> processLine(String rawLine) {
        if (rawLine == null) return Optional.empty();
        String line = rawLine.trim();
        if (line.isEmpty()) return Optional.empty();

        String upper = line.toUpperCase(Locale.ROOT);

        if (upper.startsWith("PLACE")) {
            handlePlace(line);
            return Optional.empty();
        }

        // Ignore all commands until the first VALID PLACE
        if (!robot.isPlaced()) {
            return Optional.empty();
        }

        switch (upper) {
            case "MOVE":
                robot.move();
                return Optional.empty();
            case "LEFT":
                robot.left();
                return Optional.empty();
            case "RIGHT":
                robot.right();
                return Optional.empty();
            case "REPORT":
                return robot.report();
            default:
                // Unknown command -> ignore (robust)
                return Optional.empty();
        }
    }

    public List<String> processAll(List<String> lines) {
        List<String> outputs = new ArrayList<>();
        for (String line : lines) {
            processLine(line).ifPresent(outputs::add);
        }
        return outputs;
    }

    private void handlePlace(String line) {
        // Expected: PLACE X,Y,F (spaces are allowed)
        // Examples:
        //   PLACE 0,0,NORTH
        //   PLACE 1, 2, EAST
        String rest = line.substring(5).trim(); // after "PLACE"
        if (rest.isEmpty()) return;

        String[] parts = rest.split(",");
        if (parts.length != 3) return;

        Integer x = tryParseInt(parts[0]);
        Integer y = tryParseInt(parts[1]);
        Direction d = Direction.parse(parts[2]);

        if (x == null || y == null || d == null) return;

        robot.place(x, y, d);
    }

    private Integer tryParseInt(String s) {
        try {
            return Integer.parseInt(s.trim());
        } catch (NumberFormatException ex) {
            return null;
        }
    }
    
}
