package ua.com.alevel;

import ua.com.alevel.data.JsonTest;
import ua.com.alevel.serial.SerialProcess;

import java.io.IOException;

public class IoNioMain {

    private static final String FILE = "file.txt";
    private static final String FILE_HIDDEN = ".file.txt";
    private static final String DIR = "folder";
    private static final String FILE_UPDATE = DIR + "/file_update.txt";
    private static final String DIRS = "folder/folder1/folder2/folder3";

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        JsonTest jsonTest = new JsonTest();
        jsonTest.test();
//        SerialProcess serialProcess = new SerialProcess();
//        serialProcess.serial();
//        serialProcess.deserial();
//        RWFile rwFile = new RWFile();
//        rwFile.write(FILE);
//        FileNIO nio = new FileNIO();
//        nio.createFile(FILE_HIDDEN);
//        nio.createDir(DIR);
//        nio.createDirs(DIRS);
//        FileIO io = new FileIO();
//        io.createFile(FILE);
//        io.createDirectory(DIR);
//        io.createDirectories(DIRS);
//        io.removeFile(DIR);
//        io.update(FILE, FILE_UPDATE);
    }
}
