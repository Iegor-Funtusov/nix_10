package ua.com.alevel.view.controller;

import org.apache.commons.collections4.MapUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import ua.com.alevel.facade.PublisherFacade;
import ua.com.alevel.view.dto.response.PageData;
import ua.com.alevel.view.dto.response.PublisherResponseDto;

import java.util.Map;

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
        Map<String, String[]> parameterMap = request.getParameterMap();
        if (MapUtils.isNotEmpty(parameterMap)) {
            parameterMap.forEach(model::addAttribute);
        }
        return new ModelAndView("redirect:/publishers", model);
    }
}
