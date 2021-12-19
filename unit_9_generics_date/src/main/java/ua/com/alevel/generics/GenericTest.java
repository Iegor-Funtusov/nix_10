package ua.com.alevel.generics;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class GenericTest {

    public void test() {
        RequestContainer requestContainer = new RequestContainer();
        User user = new User();
        user.setName("super name");
        requestContainer.setData(new Date());

        // gateway logic

        Object o = requestContainer.getData();

        if (o instanceof Dto) {
            System.out.println("is dto");

            try {
                user = (User) o;
            } catch (ClassCastException e) {
                System.out.println("e = " + e.getMessage());
            }
        }


        ReqContainer<User> reqContainer = new ReqContainer<>(user);

        user = reqContainer.getData();

        System.out.println("user = " + user);

        Gson gson = new Gson();
        String json = gson.toJson(requestContainer);
        System.out.println("json = " + json);

        List<Number> ids = new ArrayList<>();
        ids.add(new Integer(10));
        ids.add(new Long(10L));

        List<Object> integers = new ArrayList<>();

        BaseDaoImpl baseDao = new BaseDaoImpl();
        baseDao.create(user);
        user = baseDao.findById(new Integer(10));
        Collection<User> users = baseDao.findAllByChildren(ids);
        users = baseDao.findAllByParent(integers);
    }
}
