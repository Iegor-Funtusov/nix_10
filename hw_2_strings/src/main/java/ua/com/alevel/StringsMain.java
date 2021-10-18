package ua.com.alevel;

public class StringsMain {

    public static void main(String[] args) {
        System.out.println("StringsMain.main");
        String text = "flkhdglasdf";
        text = StringHelperUtil.reverse(text, false);
        System.out.println("text = " + text);
    }
}
