package org.example.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.example.mvc.controller.Controller;
import org.example.mvc.view.ModelAndView;

public class SimpleControllerHandlerAdapter implements HandlerAdapter {

    @Override
    public boolean supports(final Object handler) {
        return (handler instanceof Controller);
    }

    @Override
    public ModelAndView handle(final Object handler, final HttpServletRequest request,
        final HttpServletResponse response) throws Exception {
        final Controller controller = (Controller) handler;
        final String viewName = controller.handleRequest(request, response);

        return new ModelAndView(viewName);
    }
}
