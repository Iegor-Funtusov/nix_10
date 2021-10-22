package ua.com.alevel;

public class ImmutableClass {

    private final int age;
    private final String name;

    protected ImmutableClass(final int age, String name) {
        this.age = age;
        this.name = name;
    }

    private ImmutableClass() {
        this(10, "");
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }
}
