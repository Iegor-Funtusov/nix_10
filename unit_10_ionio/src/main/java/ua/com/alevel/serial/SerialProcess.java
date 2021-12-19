package ua.com.alevel.serial;

import ua.com.alevel.User;

import java.io.*;
import java.util.UUID;

public class SerialProcess {

    private User user;

    public SerialProcess() {
        user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setFirstName("first");
        user.setLastName("last");
        user.setFullName("first last");
    }

    public void serial() throws IOException {
        ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("user.out"));
        outputStream.writeObject(user);
        outputStream.flush();
    }

    public void deserial() throws IOException, ClassNotFoundException {
        ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("user.out"));
        Object obj = inputStream.readObject();
        user = (User) obj;
        System.out.println("obj = " + obj);
    }
}
