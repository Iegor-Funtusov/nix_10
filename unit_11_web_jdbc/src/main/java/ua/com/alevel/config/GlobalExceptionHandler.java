package ua.com.alevel.config;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = { EntityNotFoundException.class })
    public ModelAndView defaultEntityNotFoundHandler(EntityNotFoundException exception) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("showMessage", true);
        mav.addObject("errorMessage", exception.getMessage());
        mav.setViewName("error");
        return mav;
    }

    @ExceptionHandler(value = { PageNotFoundException.class })
    public ModelAndView defaultPageNotFoundHandler(PageNotFoundException exception) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("showMessage", true);
        mav.addObject("errorMessage", exception.getMessage());
        mav.setViewName("error");
        return mav;
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ModelAndView defaultErrorHandler(ConstraintViolationException exception) {
        System.out.println("GlobalExceptionHandler.defaultErrorHandler");
        ModelAndView mav = new ModelAndView();
        mav.addObject("showMessage", true);
        mav.addObject("errorMessage", exception.getMessage());
        mav.setViewName("error");
        return mav;
    }
}
