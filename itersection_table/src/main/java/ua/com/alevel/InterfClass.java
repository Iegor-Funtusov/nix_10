package ua.com.alevel;

public sealed interface InterfClass permits InterfClassImpl {

    default void test() {

    }
}
