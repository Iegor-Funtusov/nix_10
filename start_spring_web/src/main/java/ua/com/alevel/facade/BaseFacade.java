package ua.com.alevel.facade;

import ua.com.alevel.dto.RequestDto;
import ua.com.alevel.dto.ResponseDto;

import java.util.List;
import java.util.Optional;

public interface BaseFacade<REQ extends RequestDto, RES extends ResponseDto> {

    void create(REQ req);
    void update(REQ req, Long id);
    void delete(Long id);
    RES findById(Long id);
    List<RES> findAll();
}
