package ua.com.alevel;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.stream.Stream;

public class RWFile {

    Reader reader;
    Writer writer;
    InputStream inputStream;
    OutputStream outputStream;

    public void read(String path) throws IOException {
        inputStream = new FileInputStream(path);
        byte[] bytes = inputStream.readAllBytes();
        for (byte aByte : bytes) {
            System.out.println("aByte = " + (char) aByte);
        }

        reader = new FileReader(path);

        while (reader.ready()) {
            int read = reader.read();
            System.out.println("read = " + read);
        }

        BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
        System.out.println("before");
        while (bufferedReader.ready()) {
            System.out.println("bufferedReader = " + bufferedReader.readLine());
        }
        System.out.println("after");
        while (bufferedReader.ready()) {
            System.out.println("bufferedReader = " + bufferedReader.readLine());
        }

        bufferedReader = new BufferedReader(new FileReader(path));
        Stream<String> lines = bufferedReader.lines();
        System.out.println("before");
        lines.forEach(System.out::println);
    }

    public void write(String path) throws IOException {
        outputStream = new FileOutputStream(path);
        outputStream.write(new byte[]{ 'a', 'b' });

        FileWriter fileWriter = new FileWriter(path, true);
        String s = "vjlkgfl";
        fileWriter.write('\n');
        fileWriter.write(s);
        fileWriter.flush();

        s = "\n hello";

        Files.write(Paths.get(path), s.getBytes(), StandardOpenOption.APPEND);
    }
}
