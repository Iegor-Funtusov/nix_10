package ua.com.alevel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.com.alevel.DbInMemory;
import ua.com.alevel.entity.User;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    private final DbInMemory db = DbInMemory.getInstance();

    @GetMapping
    public String all(Model model) {
        List<User> users = db.getUsers();
        model.addAttribute("users", users);
        return "pages/user/user_all";
    }

    @GetMapping("/{id}")
    public String one(@PathVariable String id, Model model) {
        User user = db.findById(User.class, id);
        model.addAttribute("user", user);
        return "pages/user/user_details";
    }
}
