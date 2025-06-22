import utils.*;
import model.FileMetadata;
import java.util.*;
import java.io.File;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n=== File Vault CLI ===");
            System.out.println("1. List all files in a directory");
            System.out.println("2. Search files by keyword or extension");
            System.out.println("3. Read a large file line-by-line");
            System.out.println("4. Copy a file");
            System.out.println("5. Serialize file metadata");
            System.out.println("6. Deserialize and display metadata");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    listFiles();
                    break;
                case "2":
                    searchFiles();
                    break;
                case "3":
                    readFile();
                    break;
                case "4":
                    copyFile();
                    break;
                case "5":
                    serializeMetadata();
                    break;
                case "6":
                    deserializeMetadata();
                    break;
                case "0":
                    System.out.println("Goodbye!");
                    return;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

    private static void listFiles() {
        System.out.print("Enter directory path: ");
        String dir = scanner.nextLine();
        List<String> files = FileBrowser.listFiles(dir);
        if (files.isEmpty()) {
            System.out.println("No files found or invalid directory.");
        } else {
            files.forEach(System.out::println);
        }
    }

    private static void searchFiles() {
        System.out.print("Enter directory path: ");
        String dir = scanner.nextLine();
        System.out.print("Enter keyword or extension: ");
        String keyword = scanner.nextLine();
        List<String> matches = FileSearcher.searchFiles(dir, keyword);
        if (matches.isEmpty()) {
            System.out.println("No matching files found.");
        } else {
            matches.forEach(System.out::println);
        }
    }

    private static void readFile() {
        System.out.print("Enter file path: ");
        String filePath = scanner.nextLine();
        System.out.println("Choose method: 1) BufferedReader 2) NIO");
        String method = scanner.nextLine();
        if ("1".equals(method)) {
            FileReaderUtil.readFileBuffered(filePath, System.out::println);
        } else {
            FileReaderUtil.readFileNIO(filePath, System.out::println);
        }
    }

    private static void copyFile() {
        System.out.print("Enter source file path: ");
        String src = scanner.nextLine();
        System.out.print("Enter destination file path: ");
        String dst = scanner.nextLine();
        FileCopier.copyFile(src, dst);
    }

    private static void serializeMetadata() {
        System.out.print("Enter directory path: ");
        String dir = scanner.nextLine();
        List<String> files = FileBrowser.listFiles(dir);
        List<FileMetadata> metadataList = new ArrayList<>();
        for (String fname : files) {
            File f = new File(dir, fname);
            metadataList.add(new FileMetadata(f.getName(), f.length(), f.lastModified()));
        }
        System.out.print("Enter output file for metadata: ");
        String outFile = scanner.nextLine();
        MetadataManager.serializeMetadata(metadataList, outFile);
    }

    private static void deserializeMetadata() {
        System.out.print("Enter metadata file path: ");
        String inFile = scanner.nextLine();
        List<FileMetadata> metadataList = MetadataManager.deserializeMetadata(inFile);
        if (metadataList == null || metadataList.isEmpty()) {
            System.out.println("No metadata found or error reading file.");
        } else {
            metadataList.forEach(System.out::println);
        }
    }
}
