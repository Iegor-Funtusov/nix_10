package ua.com.alevel;

public class ChildRecordClass {

    AA aa = new AA();
    int a;

    public void test() {
        final int a;
        final Object o;
        this.a = 0;
    }

    private sealed class AA permits BB {

    }

    private final class BB extends AA{

    }
}
