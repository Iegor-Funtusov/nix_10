package ua.com.alevel;

import java.util.*;

public class CollectionsMain {

    public static void main(String[] args) {
//        for (int i = 0; i < 100; i++) {
//            new ListTest().test();
//        }
        new MapTest().test();

        Set<Integer> integers = new TreeSet<>();
        integers.add(10);

        Map<Integer, Object> map = new TreeMap<>();
        map.put(10, null);
    }
}
