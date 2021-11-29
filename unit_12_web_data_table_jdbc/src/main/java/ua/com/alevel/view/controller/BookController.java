package ua.com.alevel.view.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import ua.com.alevel.facade.AuthorFacade;
import ua.com.alevel.facade.BookFacade;
import ua.com.alevel.facade.PublisherFacade;
import ua.com.alevel.view.dto.request.BookRequestDto;
import ua.com.alevel.view.dto.response.BookResponseDto;
import ua.com.alevel.view.dto.response.PageData;

@Controller
@RequestMapping("/books")
public class BookController extends BaseController {

    private final BookFacade bookFacade;
    private final AuthorFacade authorFacade;
    private final PublisherFacade publisherFacade;
    private final HeaderName[] columnNames = new HeaderName[] {
            new HeaderName("#", null, null),
            new HeaderName("image", "image", null),
            new HeaderName("book name", "bookName", "book_name"),
            new HeaderName("page size", "pageSize", "page_size"),
            new HeaderName("publication", "publicationDate", "publication_date"),
            new HeaderName("details", null, null),
            new HeaderName("delete", null, null)
    };

    public BookController(BookFacade bookFacade, AuthorFacade authorFacade, PublisherFacade publisherFacade) {
        this.bookFacade = bookFacade;
        this.authorFacade = authorFacade;
        this.publisherFacade = publisherFacade;
    }

    @GetMapping
    public String findAll(Model model, WebRequest request) {
        PageData<BookResponseDto> response = bookFacade.findAll(request);
        initDataTable(response, columnNames, model);
        model.addAttribute("createUrl", "/books/all");
        model.addAttribute("createNew", "/books/new");
        model.addAttribute("cardHeader", "All Books");
        return "pages/book/book_all";
    }

    @PostMapping("/all")
    public ModelAndView findAllRedirect(WebRequest request, ModelMap model) {
        return findAllRedirect(request, model, "books");
    }

    @GetMapping("/new")
    public String redirectToNewAuthorPage(Model model) {
        model.addAttribute("book", new BookRequestDto());
        return "pages/book/book_new";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("book") BookRequestDto dto) {
        bookFacade.create(dto);
        return "redirect:/books";
    }

    @GetMapping("/details/{id}")
    public String findById(@PathVariable Long id, Model model) {
        model.addAttribute("book", bookFacade.findById(id));
        model.addAttribute("authors", authorFacade.findAllByBookId(id));
        model.addAttribute("publishers", publisherFacade.findAllByBookId(id));
        return "pages/book/book_details";
    }
}
