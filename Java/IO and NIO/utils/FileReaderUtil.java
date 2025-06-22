package utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.function.Consumer;

/**
 * Utility class to read large files line-by-line.
 */
public class FileReaderUtil {
    /**
     * Reads a file line-by-line using BufferedReader.
     * @param filePath Path to file
     * @param lineConsumer Consumer for each line
     */
    public static void readFileBuffered(String filePath, Consumer<String> lineConsumer) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                lineConsumer.accept(line);
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }

    /**
     * Reads a file line-by-line using NIO Files.lines().
     * @param filePath Path to file
     * @param lineConsumer Consumer for each line
     */
    public static void readFileNIO(String filePath, Consumer<String> lineConsumer) {
        try {
            Files.lines(Path.of(filePath)).forEach(lineConsumer);
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }
}
