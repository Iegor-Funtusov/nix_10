package ua.com.alevel.facade.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.facade.AuthorFacade;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Author;
import ua.com.alevel.service.AuthorService;
import ua.com.alevel.util.WebRequestUtil;
import ua.com.alevel.util.WebResponseUtil;
import ua.com.alevel.view.dto.request.AuthorRequestDto;
import ua.com.alevel.view.dto.request.PageAndSizeData;
import ua.com.alevel.view.dto.request.SortData;
import ua.com.alevel.view.dto.response.AuthorResponseDto;
import ua.com.alevel.view.dto.response.PageData;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AuthorFacadeImpl implements AuthorFacade {

    private final AuthorService authorService;

    public AuthorFacadeImpl(AuthorService authorService) {
        this.authorService = authorService;
    }

    @Override
    public void create(AuthorRequestDto authorRequestDto) {
        Author author = new Author();
        author.setFirstName(authorRequestDto.getFirstName());
        author.setLastName(authorRequestDto.getLastName());
        authorService.create(author);
    }

    @Override
    public void update(AuthorRequestDto authorRequestDto, Long id) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public AuthorResponseDto findById(Long id) {
        return new AuthorResponseDto(authorService.findById(id));
    }

    @Override
    public PageData<AuthorResponseDto> findAll(WebRequest request) {
        DataTableRequest dataTableRequest = WebRequestUtil.initDataTableRequest(request);
        DataTableResponse<Author> tableResponse = authorService.findAll(dataTableRequest);

        List<AuthorResponseDto> authors = tableResponse.getItems().stream().
                map(AuthorResponseDto::new).
                peek(authorResponseDto -> authorResponseDto.setBookCount((Integer) tableResponse.
                        getOtherParamMap().get(authorResponseDto.getId()))).
                collect(Collectors.toList());

        PageData<AuthorResponseDto> pageData = (PageData<AuthorResponseDto>) WebResponseUtil.initPageData(tableResponse);
        pageData.setItems(authors);

        return pageData;
    }

    @Override
    public Map<Long, String> findAllByBookId(Long bookId) {
        return authorService.findAllByBookId(bookId);
    }
}
