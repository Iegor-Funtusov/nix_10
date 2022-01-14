package ua.com.alevel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public final class MathUtil {

    private MathUtil() { }

    public static Long sum(Long[] arr) {
        Long sum = 0L;
        for (Long i : arr) {
            sum += i;
        }
        return sum;
    }

    public static Long[] generateRandomArray(int size) {
        Long[] longs = new Long[size];
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            longs[i] = random.nextLong(1, 1_000_000);
        }
        return longs;
    }

    public static List<Long[]> divideArray(Long[] longs) {
        int av = longs.length / 2;
        List<Long[]> listLongsArr = new ArrayList<>();
        Long[] left = Arrays.copyOfRange(longs, 0, av);
        Long[] right = Arrays.copyOfRange(longs, av, longs.length);
        listLongsArr.add(left);
        listLongsArr.add(right);
        return listLongsArr;
    }
}
