package ua.com.alevel;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import static ua.com.alevel.Const.CAPACITY;

public class ListTest {

    private List<Integer> arrayList = new ArrayList<>();
    private List<Integer> linkedList = new LinkedList<>();

    public void test() {
        add();
//        delete();
//        get();
        update();
    }

    private void update() {
        long start = System.currentTimeMillis();
        for (int i = 0; i < CAPACITY; i++) {
            arrayList.set(i, i);
        }
        long end = System.currentTimeMillis() - start;
        System.out.println("arrayList end = " + end);

        start = System.currentTimeMillis();
        for (int i = 0; i < CAPACITY; i++) {
            linkedList.set(i, i);
        }
        end = System.currentTimeMillis() - start;
        System.out.println("linkedList end = " + end);
    }

    private void delete() {
        long start = System.currentTimeMillis();
        Iterator<Integer> iterator = arrayList.iterator();
        while (iterator.hasNext()) {
            iterator.next();
            iterator.remove();
        }
        long end = System.currentTimeMillis() - start;
        System.out.println("arrayList end = " + end);

        start = System.currentTimeMillis();

        iterator = linkedList.iterator();
        while (iterator.hasNext()) {
            iterator.next();
            iterator.remove();
        }
        end = System.currentTimeMillis() - start;
        System.out.println("linkedList end = " + end);
    }

    private void add() {
        long start = System.currentTimeMillis();
        for (int i = 0; i < CAPACITY; i++) {
            arrayList.add(i);
        }
        long end = System.currentTimeMillis() - start;
//        System.out.println("arrayList end = " + end);

        start = System.currentTimeMillis();
        for (int i = 0; i < CAPACITY; i++) {
            linkedList.add(i);
        }
        end = System.currentTimeMillis() - start;
//        System.out.println("linkedList end = " + end);
    }

    private void get() {
        long start = System.currentTimeMillis();
        for (int i = 0; i < CAPACITY; i++) {
            arrayList.get(i);
        }
        long end = System.currentTimeMillis() - start;
        System.out.println("arrayList end = " + end);

        start = System.currentTimeMillis();
        for (int i = 0; i < CAPACITY; i++) {
            linkedList.get(i);
        }
        end = System.currentTimeMillis() - start;
        System.out.println("linkedList end = " + end);
    }
}
