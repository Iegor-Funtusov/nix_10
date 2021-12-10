package ua.com.alevel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.com.alevel.DbInMemory;
import ua.com.alevel.entity.Category;
import ua.com.alevel.entity.Transaction;

import java.util.List;

@Controller
@RequestMapping("/categories")
public class CategoryController {

    private final DbInMemory db = DbInMemory.getInstance();

    @GetMapping
    public String all(Model model) {
        List<Category> categories = db.getCategories();
        model.addAttribute("categories", categories);
        return "pages/category/category_all";
    }

    @GetMapping("/{id}")
    public String one(@PathVariable String id, Model model) {
        Category category = db.findById(Category.class, id);
        model.addAttribute("category", category);
        return "pages/category/category_details";
    }
}
