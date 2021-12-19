package ua.com.alevel;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class FileIO {

    public void createFile(String path) throws IOException {
        File file = new File(path);
        boolean newFile = file.createNewFile();
        System.out.println("newFile = " + newFile);
        System.out.println("isFile = " + file.isFile());
        System.out.println("isDirectory = " + file.isDirectory());
        System.out.println("path = " + file.getAbsolutePath());
    }

    public void createDirectory(String path) throws IOException {
        File file = new File(path);
        boolean newFile = file.mkdir();
        System.out.println("newFile = " + newFile);
        System.out.println("isFile = " + file.isFile());
        System.out.println("isDirectory = " + file.isDirectory());
        System.out.println("path = " + file.getAbsolutePath());
    }

    public void createDirectories(String path) throws IOException {
        File file = new File(path);
        boolean newFile = file.mkdirs();
        System.out.println("newFile = " + newFile);
        System.out.println("isFile = " + file.isFile());
        System.out.println("isDirectory = " + file.isDirectory());
        System.out.println("path = " + file.getAbsolutePath());
    }

    public void update(String pathTo, String pathFrom) {
        File file = new File(pathTo);
        file.renameTo(new File(pathFrom));
    }

    public void removeFile(String path) {
        File file = new File(path);
//        delete(file);
        try {
            FileUtils.deleteDirectory(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void delete(File root) {
        File[] children = root.listFiles();
        for (File child : children) {
            if (child.isDirectory()) {
                delete(child);
            }
            child.delete();
        }
        root.delete();
    }

    private static void setHiddenAttrib(File file) {
        try {
            Process p = Runtime.getRuntime().exec("attrib +H " + file.getPath());
            p.waitFor();
            if (file.isHidden()) {
                System.out.println(file.getName() + " hidden attribute is set to true");
            } else {
                System.out.println(file.getName() + " hidden attribute not set to true");
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
