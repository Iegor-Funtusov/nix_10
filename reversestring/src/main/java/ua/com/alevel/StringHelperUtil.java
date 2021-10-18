package ua.com.alevel;

public final class StringHelperUtil {

    private StringHelperUtil() { }

    public static String reverse(String text, boolean full) {
        return new StringBuilder(text).reverse().toString();
    }
}
