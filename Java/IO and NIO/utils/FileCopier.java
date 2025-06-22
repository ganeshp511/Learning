package utils;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

/**
 * Utility class to copy files using NIO FileChannel and buffer.
 */
public class FileCopier {
    /**
     * Copies a file using NIO FileChannel and buffer.
     * @param sourcePath Source file path
     * @param destPath Destination file path
     */
    public static void copyFile(String sourcePath, String destPath) {
        Path src = Path.of(sourcePath);
        Path dst = Path.of(destPath);
        try (FileChannel inChannel = FileChannel.open(src, StandardOpenOption.READ);
             FileChannel outChannel = FileChannel.open(dst, StandardOpenOption.CREATE, StandardOpenOption.WRITE)) {
            ByteBuffer buffer = ByteBuffer.allocate(1024 * 8); // 8KB buffer
            while (inChannel.read(buffer) > 0) {
                buffer.flip();
                outChannel.write(buffer);
                buffer.clear();
            }
            System.out.println("File copied successfully.");
        } catch (IOException e) {
            System.err.println("Error copying file: " + e.getMessage());
        }
    }
}
