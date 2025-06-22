package utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Utility class to list files in a directory.
 */
public class FileBrowser {
    /**
     * Lists all files in the given directory.
     * @param dirPath Directory path
     * @return List of file names
     */
    public static List<String> listFiles(String dirPath) {
        List<String> files = new ArrayList<>();
        File dir = new File(dirPath);
        if (dir.exists() && dir.isDirectory()) {
            for (File file : dir.listFiles()) {
                files.add(file.getName());
            }
        }
        return files;
    }
}
