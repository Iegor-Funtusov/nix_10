package ua.com.alevel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ExceptionMain {

    public static void main(String[] args) {
        InputStreamReader inputStreamReader = null;
        BufferedReader reader = null;
        try {
            inputStreamReader = new InputStreamReader(System.in);
            reader = new BufferedReader(inputStreamReader);
        } catch (Exception e) {
            System.out.println("e = " + e.getMessage());
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                inputStreamReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try(BufferedReader reader1 = new BufferedReader(new InputStreamReader(System.in))) {

        } catch (IOException exception) {

        }

//        List<ExceptionTest> list = new ArrayList<>();
//        List<RunnubleTest> runnubleTests = new ArrayList<>();
//        for (int i = 0; i < 10; i++) {
//            list.add(new ExceptionTest("thread" + i));
//            runnubleTests.add(new RunnubleTest());
//        }
//
//        for (RunnubleTest runnubleTest : runnubleTests) {
//            try {
//                runnubleTest.run();
//            } catch (RuntimeException exception) {
//                System.out.println("exception = " + exception.getMessage());
//            }
//        }

//        for (ExceptionTest exceptionTest : list) {
//            try {
//                exceptionTest.start();
//            } catch (RuntimeException exception) {
//                System.out.println("exception = " + exception.getMessage());
//            }
//        }

//        test("1lhjkgl3");
    }

    private static void test(String s) throws NumberFormatException {
        int i = Integer.parseInt(s);
        System.out.println("i = " + i);
//
//        try {
//            int[] ints = { 1 };
//            ints[1] = 10;
//            System.out.println(1/ 0);
//        } catch (ArithmeticException exception) {
//            System.out.println("exception = " + exception.getMessage());
//        } catch (ArrayIndexOutOfBoundsException exception) {
//            System.out.println("exception == " + exception.getMessage());
//        }
    }
}