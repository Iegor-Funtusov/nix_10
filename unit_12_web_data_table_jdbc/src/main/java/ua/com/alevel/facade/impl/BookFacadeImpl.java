package ua.com.alevel.facade.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.facade.BookFacade;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Book;
import ua.com.alevel.service.BookService;
import ua.com.alevel.util.WebRequestUtil;
import ua.com.alevel.util.WebResponseUtil;
import ua.com.alevel.view.dto.request.BookRequestDto;
import ua.com.alevel.view.dto.response.BookResponseDto;
import ua.com.alevel.view.dto.response.PageData;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class BookFacadeImpl implements BookFacade {

    private final BookService bookService;

    public BookFacadeImpl(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public void create(BookRequestDto bookRequestDto) {

    }

    @Override
    public void update(BookRequestDto bookRequestDto, Long id) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public BookResponseDto findById(Long id) {
        return new BookResponseDto(bookService.findById(id));
    }

    @Override
    public PageData<BookResponseDto> findAll(WebRequest request) {
        DataTableRequest dataTableRequest = WebRequestUtil.initDataTableRequest(request);
        String authorId = request.getParameter("authorId");
        if (StringUtils.isNotBlank(authorId)) {
            dataTableRequest.getQueryParam().put("authorId", authorId);
        }
        DataTableResponse<Book> tableResponse = bookService.findAll(dataTableRequest);
        List<BookResponseDto> books = tableResponse.getItems().stream().
                map(BookResponseDto::new).
                collect(Collectors.toList());

        PageData<BookResponseDto> pageData = (PageData<BookResponseDto>) WebResponseUtil.initPageData(tableResponse);
        pageData.setItems(books);
        return pageData;
    }

    @Override
    public Map<Long, String> findByAuthorId(Long id) {
        return bookService.findByAuthorId(id);
    }
}
