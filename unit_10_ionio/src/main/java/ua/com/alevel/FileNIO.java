package ua.com.alevel;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileNIO {

    public void createFile(String path) {
        Path current = Paths.get(path);
        try {
            if (!Files.exists(current)) {
                Files.createFile(current);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createDir(String path) {
        Path current = Paths.get(path);
        try {
            if (!Files.exists(current)) {
                Files.createDirectory(current);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createDirs(String path) {
        Path current = Paths.get(path);
        try {
            if (!Files.exists(current)) {
                Files.createDirectories(current);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
