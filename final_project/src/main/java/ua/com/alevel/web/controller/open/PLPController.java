package ua.com.alevel.web.controller.open;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ua.com.alevel.facade.PLPFacade;
import ua.com.alevel.util.WebUtil;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/books")
public class PLPController {

    private final PLPFacade plpFacade;

    public PLPController(PLPFacade plpFacade) {
        this.plpFacade = plpFacade;
    }

    @GetMapping
    public String allBooks(Model model, WebRequest webRequest) {
        model.addAttribute("date", new Input());
        model.addAttribute("bookList", plpFacade.search(webRequest));
        return "pages/open/plp";
    }

    @PostMapping("date")
    public String date(@ModelAttribute("date") Input date) {
        System.out.println("date = " + date);
        return "redirect:/books";
    }

    @GetMapping("/suggestions")
    private @ResponseBody List<String> allSearchBooks(@RequestParam String query) {
        return plpFacade.searchBookName(query);
    }

    @PostMapping("/search")
    private String searchBooks(@RequestParam String query, RedirectAttributes ra) {
        ra.addAttribute(WebUtil.SEARCH_BOOK_PARAM, query);
        return "redirect:/books";
    }

    public class Input {
        private String date;

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        @Override
        public String toString() {
            return "Input{" +
                    "date=" + date +
                    '}';
        }
    }
}
