package utils;

import model.FileMetadata;
import java.io.*;
import java.util.List;

/**
 * Utility class to serialize and deserialize file metadata.
 */
public class MetadataManager {
    /**
     * Serializes a list of FileMetadata objects to a file.
     * @param metadataList List of FileMetadata
     * @param filePath Output file path
     */
    public static void serializeMetadata(List<FileMetadata> metadataList, String filePath) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(metadataList);
            System.out.println("Metadata serialized successfully.");
        } catch (IOException e) {
            System.err.println("Error serializing metadata: " + e.getMessage());
        }
    }

    /**
     * Deserializes a list of FileMetadata objects from a file.
     * @param filePath Input file path
     * @return List of FileMetadata
     */
    public static List<FileMetadata> deserializeMetadata(String filePath) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            return (List<FileMetadata>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error deserializing metadata: " + e.getMessage());
            return null;
        }
    }
}
