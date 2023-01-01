package org.example.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.example.mvc.view.ModelAndView;

public interface HandlerAdapter {

    boolean supports(Object handler);

    ModelAndView handle(Object handler, HttpServletRequest request, HttpServletResponse response) throws Exception;
}
