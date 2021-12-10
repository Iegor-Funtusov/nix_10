package ua.com.alevel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.com.alevel.DbInMemory;
import ua.com.alevel.entity.Category;
import ua.com.alevel.entity.Transaction;

import java.util.List;

@Controller
@RequestMapping("/transactions")
public class TransactionController {

    private final DbInMemory db = DbInMemory.getInstance();

    @GetMapping
    public String all(Model model) {
        List<Transaction> transactions = db.getTransactions();
        model.addAttribute("transactions", transactions);
        return "pages/transaction/transaction_all";
    }

    @GetMapping("/{id}")
    public String one(@PathVariable String id, Model model) {
        Transaction transaction = db.findById(Transaction.class, id);
        model.addAttribute("transaction", transaction);
        return "pages/transaction/transaction_details";
    }

    @GetMapping("/new/{id}")
    public String redirectToNew(@PathVariable String id, Model model) {
        Transaction transaction = new Transaction();
        transaction.setAccountId(id);
        List<Category> categories = db.getCategories();
        model.addAttribute("accountId", id);
        model.addAttribute("transaction", transaction);
        model.addAttribute("categories", categories);
        return "pages/transaction/transaction_new";
    }

    @PostMapping("/new")
    public String newTransaction(@ModelAttribute(name = "transaction") Transaction transaction) {
        db.create(Transaction.class, transaction);
        return "redirect:/accounts/" + transaction.getAccountId();
    }
}
