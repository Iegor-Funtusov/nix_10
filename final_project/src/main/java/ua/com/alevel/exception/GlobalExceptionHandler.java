package ua.com.alevel.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = { EntityNotFoundException.class })
    public ModelAndView handlerEntityNotFound(EntityNotFoundException e) {
        return generateModelAndView(e.getMessage());
    }

    @ExceptionHandler(value = { BadRequestException.class })
    public ModelAndView handlerBadRequest(BadRequestException e) {
        return generateModelAndView(e.getMessage());
    }

    private ModelAndView generateModelAndView(String msg) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("showMessage", true);
        mav.addObject("errorMessage", msg);
        mav.setViewName("error");
        return mav;
    }
}
