package org.example.mvc;

import java.util.Collections;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.example.mvc.controller.RequestMethod;
import org.example.mvc.view.JspViewResolver;
import org.example.mvc.view.ModelAndView;
import org.example.mvc.view.View;
import org.example.mvc.view.ViewResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet("/")
public class DispatcherServlet extends HttpServlet {

    public static final Logger logger = LoggerFactory.getLogger(DispatcherServlet.class);

    private List<HandlerMapping> handlerMappings;
    private List<HandlerAdapter> handlerAdapters;
    private List<ViewResolver> viewResolvers;

    @Override
    public void init() throws ServletException {
        final RequestMappingHandlerMapping requestMappingHandlerMapping = new RequestMappingHandlerMapping();
        requestMappingHandlerMapping.init();

        final AnnotationHandlerMapping annotationHandlerMapping = new AnnotationHandlerMapping("org.example");
        annotationHandlerMapping.init();

        handlerMappings = List.of(requestMappingHandlerMapping, annotationHandlerMapping);

        handlerAdapters = List.of(new SimpleControllerHandlerAdapter(), new AnnotationHandlerAdapter());
        viewResolvers = Collections.singletonList(new JspViewResolver());
    }

    @Override
    protected void service(final HttpServletRequest request, final HttpServletResponse response) {
        logger.info("DispatcherServlet#service started.");
        final RequestMethod requestMethod = RequestMethod.valueOf(request.getMethod());
        final String requestURI = request.getRequestURI();

        try {
            final Object handler = handlerMappings.stream()
                .filter(hm -> hm.findHandler(new HandlerKey(requestMethod, requestURI)) != null)
                .map(hm -> hm.findHandler(new HandlerKey(requestMethod, requestURI)))
                .findFirst()
                .orElseThrow(() -> new ServletException("No handler for [" + requestMethod + ", " + requestURI + "]"));

            final HandlerAdapter handlerAdapter = handlerAdapters.stream()
                .filter(adapter -> adapter.supports(handler))
                .findFirst()
                .orElseThrow(() -> new ServletException("No adapter for handler[" + handler + "]"));

            final ModelAndView mav = handlerAdapter.handle(handler, request, response);

            for (final ViewResolver viewResolver : viewResolvers) {
                final View view = viewResolver.resolvedView(mav.getView());
                view.render(mav.getModel(), request, response);
            }
        } catch (Exception e) {
            logger.error("exception occurred: [{}]", e);
        }

    }
}
