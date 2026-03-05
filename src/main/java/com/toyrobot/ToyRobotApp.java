package com.toyrobot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * Toy Robot Console Application (Java 11)
 *
 * Usage:
 *  - From a file: java -jar toy-robot-1.0.0.jar examples/example-a.txt
 *  - From stdin : java -jar toy-robot-1.0.0.jar
 *
 * Type EXIT to quit when using stdin.
 */
public final class ToyRobotApp {

    private ToyRobotApp() {}

    public static void main(String[] args) throws IOException {
       
    	Table table = new Table(5, 5);
        Robot robot = new Robot(table);
        CommandProcessor processor = new CommandProcessor(robot);

        if (args.length >= 1) {
            runFromFile(processor, args[0]);
        } else {
            runFromStdin(processor);
        }
    }

    private static void runFromFile(CommandProcessor processor, String filePath) throws IOException {
        
    	Path path = Path.of(filePath);
        if (!Files.exists(path)) {
            System.err.println("File not found: " + path.toAbsolutePath());
            System.exit(2);
        }

        List<String> lines = Files.readAllLines(path);
        List<String> outputs = processor.processAll(lines);

        for (String out : outputs) {
            System.out.println(out);
        }
    }

    private static void runFromStdin(CommandProcessor processor) throws IOException {
       
    	System.out.println("Toy Robot Console App");
        System.out.println("Commands: PLACE X,Y,F | MOVE | LEFT | RIGHT | REPORT | EXIT");
        System.out.println("Table: 5x5 (X,Y in 0..4)");
        System.out.println();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while (true) {
            System.out.print("==: ");
            line = br.readLine();
            if (line == null) break;

            String trimmed = line.trim();
            if (trimmed.equalsIgnoreCase("EXIT") || trimmed.equalsIgnoreCase("QUIT")) {
                break;
            }

            processor.processLine(trimmed).ifPresent(System.out::println);
        }
    }
    
}
