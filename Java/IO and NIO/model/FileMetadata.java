package model;

import java.io.Serializable;
import java.nio.file.Path;
import java.nio.file.attribute.FileTime;

/**
 * Serializable class to store file metadata.
 */
public class FileMetadata implements Serializable {
    private static final long serialVersionUID = 1L;
    private String filename;
    private long size;
    private long lastModified;

    public FileMetadata(String filename, long size, long lastModified) {
        this.filename = filename;
        this.size = size;
        this.lastModified = lastModified;
    }

    public String getFilename() {
        return filename;
    }

    public long getSize() {
        return size;
    }

    public long getLastModified() {
        return lastModified;
    }

    @Override
    public String toString() {
        return String.format("%s (Size: %d bytes, Last Modified: %d)", filename, size, lastModified);
    }
}
