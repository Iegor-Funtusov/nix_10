package ua.com.alevel;

import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

public class StringMain {

    private static final String NAME_1 = "name1";
    private static final String NAME_2 = "name2";

    public static void main(String[] args) {

        int a = 15;
        int res1 = a * 2;
        int res2 = a << 1;


        String s = null;
        if (s != null) {

        }

        if (null != s) {

        }

        if (Objects.nonNull(s)) {

        }


        System.out.println("res1 = " + res1);
        System.out.println("res2 = " + res2);

        String s1 = "\n";
        String s2 = "\n";

        System.out.println("s2 = " + !s1.isEmpty());
        System.out.println("s2 = " + !s2.isBlank());

        System.out.println("s2 = " + StringUtils.isNotEmpty(s2));
        System.out.println("s2 = " + StringUtils.isNotBlank(s2));


//        String s1 = "test";
//        s1 = "blabla";
//
//        String s2 = "test";
//
//        System.out.println("s1 = " + s1);
//
//        final Person person1 = new Person();
//        person1.setName(NAME_1);
//
//        Person person2 = person1;
//        person2.setName(NAME_2);
//
//        System.out.println("person1 = " + person1.getName());
//
//        System.out.println("StringMain.main");
//        List<Person> persons = new ArrayList<>();
//        Random random = new Random();
//        for (int i = 0; i < 1_000; i++) {
//            int r = random.nextInt(2);
//            Person person = new Person();
//            if (r == 0) {
//                person.setName(NAME_1);
//            } else {
//                person.setName(NAME_2);
//            }
//            persons.add(person);
//        }
//        long count = persons
//                .stream()
//                .filter(person -> person.getName().equals(NAME_1))
//                .count();
//        System.out.println("count = " + count);
    }
}
