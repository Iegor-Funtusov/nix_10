package ua.com.alevel.data;

import com.google.gson.Gson;
import ua.com.alevel.User;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class JsonTest {

    public void test() throws IOException {
        List<User> users = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            User user = new User();
            user.setId(UUID.randomUUID().toString());
            user.setFirstName("first"+i);
            user.setLastName("last"+i);
            user.setFullName(user.getFirstName() + " " + user.getLastName());
            users.add(user);
        }
        UserItems items = new UserItems();
        items.setItems(users);
        items.setCount(users.size());

        Container<UserItems> container = new Container<>();
        container.setData(items);

        Gson gson = new Gson();
        String json = gson.toJson(container);

        System.out.println("json = " + json);

        FileWriter writer = new FileWriter("users.json");
        writer.write(json);
        writer.flush();
    }
}
