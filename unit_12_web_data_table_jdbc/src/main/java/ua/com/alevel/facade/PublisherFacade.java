package ua.com.alevel.facade;

import ua.com.alevel.view.dto.request.PublisherRequestDto;
import ua.com.alevel.view.dto.response.PublisherResponseDto;

import java.util.Map;

public interface PublisherFacade extends BaseFacade<PublisherRequestDto, PublisherResponseDto> {

    Map<Long, String> findAllByBookId(Long bookId);
}
