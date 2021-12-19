package ua.com.alevel.data;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import ua.com.alevel.User;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class JsonTest {

    public void test() throws IOException {
//        List<User> users = new ArrayList<>();
//        for (int i = 0; i < 3; i++) {
//            User user = new User();
//            user.setId(UUID.randomUUID().toString());
//            user.setFirstName("first"+i);
//            user.setLastName("last"+i);
//            user.setFullName(user.getFirstName() + " " + user.getLastName());
//            users.add(user);
//        }
//        UserItems items = new UserItems();
//        items.setItems(users);
//        items.setCount(users.size());
//
//        Container<UserItems> container = new Container<>();
//        container.setData(items);
//
//        Gson gson = new Gson();
//        String json = gson.toJson(container);
//
//        System.out.println("json = " + json);

//        FileWriter writer = new FileWriter("users.json");
//        writer.write(json);
//        writer.flush();

//        User user = new User();
//        user.setId(UUID.randomUUID().toString());
//        user.setFirstName("first");
//        user.setLastName("last");
//        user.setFullName(user.getFirstName() + " " + user.getLastName());
//
//        json = gson.toJson(user);
//        System.out.println("json = " + json);
//
//        user = gson.fromJson(json, User.class);
//        System.out.println("user = " + user);
//
//        System.out.println("LIST");
//
//        json = gson.toJson(users);
//        System.out.println("json = " + json);
//
//        users = gson.fromJson(json, new TypeReference<List<User>>() {}.getType());
//        System.out.println("users = " + users);
//
//        BufferedReader bufferedReader = new BufferedReader(new FileReader("users.json"));
//        StringBuilder stringBuilder = new StringBuilder();
//        while (bufferedReader.ready()) {
//            stringBuilder.append(bufferedReader.readLine());
//        }
//        container = gson.fromJson(stringBuilder.toString(), new TypeReference<Container<UserItems>>() {}.getType());
//        System.out.println("container = " + container);
//
//        container = gson.fromJson(new FileReader("users.json"), new TypeReference<Container<UserItems>>() {}.getType());
//        System.out.println("container = " + container);

//        ObjectMapper objectMapper = new ObjectMapper();
//        container = objectMapper.readValue(stringBuilder.toString(), new TypeReference<Container<UserItems>>() {});
//        System.out.println("container = " + container);

        Gson gson = new Gson();
        JsonElement jsonElement = gson.fromJson(new FileReader("users.json"), JsonElement.class);
        parse(jsonElement);
    }

    private void parse(JsonElement jsonElement) {
        System.out.println("jsonElement = " + jsonElement);
        if (jsonElement.isJsonObject()) {
            JsonObject jsonObject = jsonElement.getAsJsonObject();
            for(Map.Entry<String, JsonElement> entry : jsonObject.entrySet()) {
                if (entry.getValue().isJsonArray()) {
                    for (JsonElement element : entry.getValue().getAsJsonArray()) {
                        parse(element);
                    }
                }
                if (entry.getValue().isJsonPrimitive()) {
                    String asString = entry.getValue().getAsString();
                    System.out.println("asString = " + asString);
                }
                if (entry.getValue().isJsonObject()) {
                    parse(entry.getValue().getAsJsonObject());
                }
            }
        } else if (jsonElement.isJsonArray()) {
            JsonArray jsonArray = jsonElement.getAsJsonArray();
            for (JsonElement element : jsonArray) {
                parse(element);
            }
        } else {
            System.out.println("jsonElement primitive = " + jsonElement);
        }
    }
}
