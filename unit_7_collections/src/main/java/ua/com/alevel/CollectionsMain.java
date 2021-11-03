package ua.com.alevel;

import java.util.*;

public class CollectionsMain {

    public static void main(String[] args) {
        new Func().convert();
//        for (int i = 0; i < 100; i++) {
//            new ListTest().test();
//        }
//        new MapTest().test();
//
//        Set<Integer> integers = new TreeSet<>();
//        integers.add(10);
//
//        Map<Integer, Object> map = new TreeMap<>();
//        map.put(10, null);
//
//        vara("0", "", "", "");
//        String[] strings = {"0", "", "", ""};
//        vara(strings);
//        System.out.println();
//        vara(strings, strings);
//
//        Number[] numbers1 = { 1, 1L, 0.5 };
//        Number[] numbers2 = { 1, 1L, 0.5 };
//
//        num(numbers1);
//        num(numbers1, numbers2);
    }

    private static void num(Number[] numbers) {}
    private static void num(Number[] ... numbers) {}

    private static void vara(String ... strings) {
        Integer integer;
        int i;

        Long aLong;
        long l;

        for (String string : strings) {
            System.out.println("string = " + string);
        }
    }

    private static void vara(String[] ... strings) {
        for (String[] string : strings) {
            System.out.println("string = " + string);
        }
    }
}
