package ua.com.alevel.facade;

import ua.com.alevel.view.dto.request.AuthorRequestDto;
import ua.com.alevel.view.dto.response.AuthorResponseDto;

import java.util.Map;

public interface AuthorFacade extends BaseFacade<AuthorRequestDto, AuthorResponseDto> {

    Map<Long, String> findAllByBookId(Long bookId);
}
