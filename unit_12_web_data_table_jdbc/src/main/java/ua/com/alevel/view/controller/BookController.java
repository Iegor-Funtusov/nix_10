package ua.com.alevel.view.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.facade.AuthorFacade;
import ua.com.alevel.facade.BookFacade;
import ua.com.alevel.facade.PublisherFacade;
import ua.com.alevel.view.dto.response.BookResponseDto;
import ua.com.alevel.view.dto.response.PageData;

@Controller
@RequestMapping("/books")
public class BookController {

    private final BookFacade bookFacade;
    private final AuthorFacade authorFacade;
    private final PublisherFacade publisherFacade;

    public BookController(BookFacade bookFacade, AuthorFacade authorFacade, PublisherFacade publisherFacade) {
        this.bookFacade = bookFacade;
        this.authorFacade = authorFacade;
        this.publisherFacade = publisherFacade;
    }

    @GetMapping
    public String findAll(Model model, WebRequest request) {
        PageData<BookResponseDto> response = bookFacade.findAll(request);
        model.addAttribute("pageData", response);
        return "pages/book/book_all";
    }

    @GetMapping("/details/{id}")
    public String findById(@PathVariable Long id, Model model) {
        model.addAttribute("book", bookFacade.findById(id));
        model.addAttribute("authors", authorFacade.findAllByBookId(id));
        model.addAttribute("publishers", publisherFacade.findAllByBookId(id));
        return "pages/book/book_details";
    }
}
