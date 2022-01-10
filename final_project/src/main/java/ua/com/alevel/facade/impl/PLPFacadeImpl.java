package ua.com.alevel.facade.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.exception.BadRequestException;
import ua.com.alevel.facade.PLPFacade;
import ua.com.alevel.logger.InjectLog;
import ua.com.alevel.logger.LoggerLevel;
import ua.com.alevel.logger.LoggerService;
import ua.com.alevel.persistence.entity.book.Book;
import ua.com.alevel.service.ElasticBookSearchService;
import ua.com.alevel.service.PLPService;
import ua.com.alevel.util.WebUtil;
import ua.com.alevel.web.dto.response.BookPLPDto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PLPFacadeImpl implements PLPFacade {

    @InjectLog
    private LoggerService loggerService;

    private final PLPService plpService;
    private final ElasticBookSearchService elasticBookSearchService;

    public PLPFacadeImpl(PLPService plpService, ElasticBookSearchService elasticBookSearchService) {
        this.plpService = plpService;
        this.elasticBookSearchService = elasticBookSearchService;
    }

    @Override
    public List<BookPLPDto> search(WebRequest webRequest) {
        Map<String, Object> queryMap = new HashMap<>();
        if (webRequest.getParameterMap().get(WebUtil.PUBLISHER_PARAM) != null) {
            String[] params = webRequest.getParameterMap().get(WebUtil.PUBLISHER_PARAM);
            if (StringUtils.isBlank(params[0])) {
                throw new BadRequestException("bad request");
            }
            Long publisherId = Long.parseLong(params[0]);
            queryMap.put(WebUtil.PUBLISHER_PARAM, publisherId);
            loggerService.commit(LoggerLevel.INFO, "add " + WebUtil.PUBLISHER_PARAM + ": " + publisherId);
        }
        if (webRequest.getParameterMap().get(WebUtil.SEARCH_BOOK_PARAM) != null) {
            String[] params = webRequest.getParameterMap().get(WebUtil.SEARCH_BOOK_PARAM);
            if (StringUtils.isBlank(params[0])) {
                throw new BadRequestException("bad request");
            }
            String searchBook = params[0];
            queryMap.put(WebUtil.SEARCH_BOOK_PARAM, searchBook);
            loggerService.commit(LoggerLevel.INFO, "add " + WebUtil.SEARCH_BOOK_PARAM + ": " + searchBook);
        }
        List<Book> books = plpService.search(queryMap);
        List<BookPLPDto> bookPLPDtos = books.stream().map(BookPLPDto::new).toList();
        return bookPLPDtos;
    }

    @Override
    public List<String> searchBookName(String query) {
        return elasticBookSearchService.searchBookName(query);
    }
}
