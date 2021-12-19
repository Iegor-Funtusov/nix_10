package ua.com.alevel.facade.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.facade.PublisherFacade;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Publisher;
import ua.com.alevel.service.PublisherService;
import ua.com.alevel.util.WebRequestUtil;
import ua.com.alevel.util.WebResponseUtil;
import ua.com.alevel.view.dto.request.PublisherRequestDto;
import ua.com.alevel.view.dto.response.PageData;
import ua.com.alevel.view.dto.response.PublisherResponseDto;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PublisherFacadeImpl implements PublisherFacade {

    private final PublisherService publisherService;

    public PublisherFacadeImpl(PublisherService publisherService) {
        this.publisherService = publisherService;
    }

    @Override
    public void create(PublisherRequestDto publisherRequestDto) {

    }

    @Override
    public void update(PublisherRequestDto publisherRequestDto, Long id) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public PublisherResponseDto findById(Long id) {
        return null;
    }

    @Override
    public PageData<PublisherResponseDto> findAll(WebRequest request) {
        DataTableRequest dataTableRequest = WebRequestUtil.initDataTableRequest(request);

        DataTableResponse<Publisher> tableResponse = publisherService.findAll(dataTableRequest);
        List<PublisherResponseDto> list = tableResponse.getItems()
                .stream()
                .map(PublisherResponseDto::new).
                peek(authorResponseDto -> authorResponseDto.setBookCount((Integer) tableResponse.
                getOtherParamMap().get(authorResponseDto.getId())))
                .collect(Collectors.toList());
        PageData<PublisherResponseDto> pageData = (PageData<PublisherResponseDto>) WebResponseUtil.initPageData(tableResponse);
        pageData.setItems(list);

        return pageData;
    }

    @Override
    public Map<Long, String> findAllByBookId(Long bookId) {
        return publisherService.findAllByBookId(bookId);
    }
}
