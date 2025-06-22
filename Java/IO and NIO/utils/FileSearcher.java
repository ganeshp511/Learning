package utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Utility class to search files by keyword or extension.
 */
public class FileSearcher {
    /**
     * Search files by keyword or extension in a directory.
     * @param dirPath Directory path
     * @param keywordOrExt Keyword or extension (e.g., ".txt")
     * @return List of matching file names
     */
    public static List<String> searchFiles(String dirPath, String keywordOrExt) {
        List<String> matches = new ArrayList<>();
        File dir = new File(dirPath);
        if (dir.exists() && dir.isDirectory()) {
            for (File file : dir.listFiles()) {
                if (file.getName().contains(keywordOrExt) || file.getName().endsWith(keywordOrExt)) {
                    matches.add(file.getName());
                }
            }
        }
        return matches;
    }
}
