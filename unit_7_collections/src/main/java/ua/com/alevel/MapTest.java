package ua.com.alevel;

import java.util.*;

public class MapTest {

    private Map<String, Integer> hashMap = new HashMap<>();
    private Map<String, Integer> linkedHashMap = new LinkedHashMap<>();
    private Map<String, Integer> treeMap = new TreeMap<>();

    private Map<User, Integer> map = new HashMap<>();
    private Map<User, Integer> map2 = new TreeMap<>();

    public void test() {

        for (int i = 0; i < 10; i++) {
            User user = new User();
            user.setId(UUID.randomUUID().toString());
            user.setName("name");
            user.setAge(i);
            map.put(user, i);
        }

        System.out.println("map = " + map.size());

        for (int i = 0; i < 10; i++) {
            User user = new User();
            user.setId(UUID.randomUUID().toString());
            user.setName("name");
            user.setAge(i);
            map2.put(user, i);
        }

        System.out.println("map = " + map2.size());
        map2.forEach((k, v) -> {
            System.out.println("k = " + k);
        });

//        for (int i = 0; i < 10; i++) {
//            String key = UUID.randomUUID().toString();
//            hashMap.put(key, i);
//        }
//        hashMap.forEach((k, v) -> {
//            System.out.println("k = " + k);
//            System.out.println("v = " + v);
//        });
//
//        System.out.println();
//
//        for (int i = 0; i < 10; i++) {
//            String key = UUID.randomUUID().toString();
//            linkedHashMap.put(key, i);
//        }
//        linkedHashMap.forEach((k, v) -> {
//            System.out.println("k = " + k);
//            System.out.println("v = " + v);
//        });
    }
}
