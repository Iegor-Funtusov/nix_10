package ua.com.alevel.service;

import org.junit.jupiter.api.*;
import ua.com.alevel.entity.User;

import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserServiceTest {

    private static final UserService userService = new UserService();

    private static final String NAME = "name";
    private static final String NAME_UPDATE = "name_update";
    private static final String EMAIL = "test@mail.com";
    private static final int AGE = 20;
    private static final int AGE_UPDATE = 25;
    private static final int DEFAULT_SIZE = 10;

    @BeforeAll
    public static void setUp() {
        for (int i = 0; i < DEFAULT_SIZE; i++) {
            User user = generateUser(NAME + i, i, i + EMAIL);
            userService.create(user);
        }
        Assertions.assertEquals(userService.findAll().size(), DEFAULT_SIZE);
    }

    @Test
    @Order(1)
    public void shouldBeCreateUserWhenEmailIsNotDuplicate() {
        User user = generateRandomUser();

        userService.create(user);

        List<User> users = userService.findAll();
        Assertions.assertEquals(users.size(), DEFAULT_SIZE + 1);
    }

    @Test
    @Order(2)
    public void shouldBeCreateUserWhenEmailIsDuplicate() {
        User user = generateRandomUser();

        userService.create(user);

        List<User> users = userService.findAll();
        Assertions.assertEquals(users.size(), DEFAULT_SIZE + 1);
    }

    @Test
    @Order(3)
    public void shouldBeUpdateUserById() {
        User user = getRandomUser();
        user.setName(NAME_UPDATE);
        user.setAge(AGE_UPDATE);

        userService.update(user);

        user = userService.findById(user.getId());

        Assertions.assertEquals(AGE_UPDATE, user.getAge());
        Assertions.assertEquals(NAME_UPDATE, user.getName());
    }

    @Test
    @Order(4)
    public void shouldBeDeleteUserById() {
        User user = getRandomUser();

        userService.delete(user.getId());

        Assertions.assertEquals(userService.findAll().size(), DEFAULT_SIZE);
    }

    private static User generateRandomUser() {
        User user = new User();
        user.setName(NAME);
        user.setAge(AGE);
        user.setEmail(EMAIL);
        return user;
    }

    private static User generateUser(String name, int age, String email) {
        User user = new User();
        user.setName(name);
        user.setAge(age);
        user.setEmail(email);
        return user;
    }

    private static User getRandomUser() {
        return userService.findAll().stream().findFirst().get();
    }
}
