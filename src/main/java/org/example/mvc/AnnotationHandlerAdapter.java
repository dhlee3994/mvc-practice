package org.example.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.example.mvc.view.ModelAndView;

public class AnnotationHandlerAdapter implements HandlerAdapter {

    @Override
    public boolean supports(final Object handler) {
        return (handler instanceof AnnotationHandler);
    }

    @Override
    public ModelAndView handle(
        final Object handler,
        final HttpServletRequest request,
        final HttpServletResponse response) throws Exception {
        final AnnotationHandler annotationHandler = (AnnotationHandler) handler;
        final String viewName = annotationHandler.handle(request, response);

        return new ModelAndView(viewName);
    }
}
