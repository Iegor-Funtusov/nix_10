package ua.com.alevel.data;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;
import ua.com.alevel.User;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CsvTest {

    public void test() {
        List<String[]> users = new ArrayList<>();
        String[] header = { "id", "firstName", "lastName" };
        users.add(header);

        List<User> userList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            User user = new User();
            user.setId(String.valueOf(i));
            user.setFirstName("first" + i);
            user.setLastName("last" + i);
            userList.add(user);
        }

        for (int i = 0; i < userList.size(); i++) {
            String[] usersString = new String[3];
            usersString[0] = userList.get(i).getId();
            usersString[1] = userList.get(i).getFirstName();
            usersString[2] = userList.get(i).getLastName();
            users.add(usersString);
        }

        try(CSVWriter csvWriter = new CSVWriter(new FileWriter("users.csv"))) {
            csvWriter.writeAll(users);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try(CSVReader csvReader = new CSVReader(new FileReader("users.csv"))) {
            List<String[]> list = csvReader.readAll();
            for (String[] strings : list) {
                System.out.print(strings[0] + " " + strings[1] + " " + strings[2]);
                System.out.println();
            }
        } catch (CsvException | IOException e) {
            e.printStackTrace();
        }
    }
}
