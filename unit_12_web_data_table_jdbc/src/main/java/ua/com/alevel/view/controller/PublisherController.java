package ua.com.alevel.view.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import ua.com.alevel.facade.PublisherFacade;
import ua.com.alevel.view.dto.request.PublisherRequestDto;
import ua.com.alevel.view.dto.response.PageData;
import ua.com.alevel.view.dto.response.PublisherResponseDto;

@Controller
@RequestMapping("/publishers")
public class PublisherController extends BaseController {

    private final PublisherFacade publisherFacade;
    private final HeaderName[] columnNames = new HeaderName[] {
            new HeaderName("#", null, null),
            new HeaderName("name", "name", "name"),
            new HeaderName("country", "country", "country"),
            new HeaderName("book count", "bookCount", "book_count"),
            new HeaderName("details", null, null),
            new HeaderName("delete", null, null)
    };

    public PublisherController(PublisherFacade publisherFacade) {
        this.publisherFacade = publisherFacade;
    }

    @GetMapping
    public String findAll(Model model, WebRequest request) {
        PageData<PublisherResponseDto> response = publisherFacade.findAll(request);
        initDataTable(response, columnNames, model);
        model.addAttribute("createUrl", "/publishers/all");
        model.addAttribute("createNew", "/publishers/new");
        model.addAttribute("cardHeader", "All Publishers");
        return "pages/publisher/publisher_all";
    }

    @PostMapping("/all")
    public ModelAndView findAllRedirect(WebRequest request, ModelMap model) {
        return findAllRedirect(request, model, "publishers");
    }

    @GetMapping("/new")
    public String redirectToNewAuthorPage(Model model) {
        model.addAttribute("publisher", new PublisherRequestDto());
        return "pages/publisher/publisher_new";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("publisher") PublisherRequestDto dto) {
        publisherFacade.create(dto);
        return "redirect:/publishers";
    }

    @GetMapping("/details/{id}")
    public String delete(@PathVariable Long id, Model model) {
        model.addAttribute("publisher", publisherFacade.findById(id));
        return "pages/publisher/publisher_details";
    }
}
