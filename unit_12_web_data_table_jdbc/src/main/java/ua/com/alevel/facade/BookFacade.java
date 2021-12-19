package ua.com.alevel.facade;

import ua.com.alevel.view.dto.request.BookRequestDto;
import ua.com.alevel.view.dto.response.BookResponseDto;

import java.util.Map;

public interface BookFacade extends BaseFacade<BookRequestDto, BookResponseDto> {

    Map<Long, String> findByAuthorId(Long id);
}
