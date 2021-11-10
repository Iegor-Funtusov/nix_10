package ua.com.alevel.data;

import ua.com.alevel.User;

import java.util.List;

public class UserItems {

    private long count;
    private List<User> items;

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public List<User> getItems() {
        return items;
    }

    public void setItems(List<User> items) {
        this.items = items;
    }
}
