package ua.com.alevel.view.controller;

import org.apache.commons.collections4.MapUtils;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import ua.com.alevel.facade.AuthorFacade;
import ua.com.alevel.facade.BookFacade;
import ua.com.alevel.view.dto.request.AuthorRequestDto;
import ua.com.alevel.view.dto.response.AuthorResponseDto;
import ua.com.alevel.view.dto.response.PageData;

import java.util.Map;

@Controller
@RequestMapping("/authors")
public class AuthorController extends BaseController {

    private final AuthorFacade authorFacade;
    private final BookFacade bookFacade;
    private final HeaderName[] columnNames = new HeaderName[] {
            new HeaderName("#", null, null),
            new HeaderName("first name", "firstName", "first_name"),
            new HeaderName("last name", "lastName", "last_name"),
            new HeaderName("book count", "bookCount", "bookCount"),
            new HeaderName("details", null, null),
            new HeaderName("delete", null, null)
    };

    public AuthorController(AuthorFacade authorFacade, BookFacade bookFacade) {
        this.authorFacade = authorFacade;
        this.bookFacade = bookFacade;
    }

    @GetMapping
    public String findAll(Model model, WebRequest request) {
        PageData<AuthorResponseDto> response = authorFacade.findAll(request);
        initDataTable(response, columnNames, model);
        model.addAttribute("createUrl", "/authors/all");
        model.addAttribute("createNew", "/authors/new");
        model.addAttribute("cardHeader", "All Authors");
        return "pages/author/author_all";
    }

    @PostMapping("/all")
    public ModelAndView findAllRedirect(WebRequest request, ModelMap model) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        if (MapUtils.isNotEmpty(parameterMap)) {
            parameterMap.forEach(model::addAttribute);
        }
        return new ModelAndView("redirect:/authors", model);
    }

    @GetMapping("/new")
    public String redirectToNewAuthorPage(Model model) {
        model.addAttribute("author", new AuthorRequestDto());
        return "pages/author/author_new";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("author") AuthorRequestDto dto) {
        System.out.println("dto = " + dto);
        authorFacade.create(dto);
        return "redirect:/authors";
    }

    @GetMapping("/details/{id}")
    public String redirectToNewAuthorPage(@PathVariable Long id, Model model) {
        model.addAttribute("author", authorFacade.findById(id));
        model.addAttribute("books", bookFacade.findByAuthorId(id));
        return "pages/author/author_details";
    }
}
