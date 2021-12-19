package ua.com.alevel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.com.alevel.DbInMemory;
import ua.com.alevel.entity.Account;

import java.util.List;

@Controller
@RequestMapping("/accounts")
public class AccountController {

    private final DbInMemory db = DbInMemory.getInstance();

    @GetMapping
    public String all(Model model) {
        List<Account> accounts = db.getAccounts();
        model.addAttribute("accounts", accounts);
        return "pages/account/account_all";
    }

    @GetMapping("/user/{id}")
    public String allByUserId(@PathVariable String id, Model model) {
        List<Account> accounts = db.byUserId(id);
        model.addAttribute("accounts", accounts);
        return "pages/account/account_all";
    }

    @GetMapping("/{id}")
    public String one(@PathVariable String id, Model model) {
        Account account = db.findById(Account.class, id);
        model.addAttribute("account", account);
        return "pages/account/account_details";
    }
}
