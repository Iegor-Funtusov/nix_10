package ua.com.alevel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BlaBla {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Select you event");
        String position;
        while ((position = reader.readLine()) != null) {
            logic(position, reader);
            position = reader.readLine();
            switch (position) {
                case "0" : {
                    System.exit(0);
                }
                case "1" : {
                    logic(position, reader);
                }
            }
        }
        reader.close();
    }

    private static void logic(String position, BufferedReader bufferedReader) {
        switch (position) {
            case "1" : task1(bufferedReader); break;
            case "2" : task2(); break;
        }
        System.out.println("Your variant: if you want exit, please input 0, else, repeat logic");
    }

    private static void task1(BufferedReader bufferedReader) {
        System.out.println("select your text:");
        Task1 task1 = new Task1();
        String text = null;
        try {
            text = bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("text = " + text);
        task1.run();
    }

    private static void task2() {
        Task2 task2 = new Task2();
        task2.run();
    }
}
