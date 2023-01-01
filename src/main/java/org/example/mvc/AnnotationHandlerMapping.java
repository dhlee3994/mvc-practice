package org.example.mvc;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.example.mvc.annotation.Controller;
import org.example.mvc.annotation.RequestMapping;
import org.example.mvc.controller.RequestMethod;
import org.reflections.Reflections;

public class AnnotationHandlerMapping implements HandlerMapping {

    private final Object[] basePackages;
    private Map<HandlerKey, AnnotationHandler> handlers = new HashMap<>();

    public AnnotationHandlerMapping(Object... basePackages) {
        this.basePackages = basePackages;
    }

    @Override
    public void init() {
        final Reflections reflections = new Reflections(basePackages);

        final Set<Class<?>> clazzesWithControllerAnnotation = reflections.getTypesAnnotatedWith(Controller.class);

        clazzesWithControllerAnnotation.forEach(clazz -> {
            Arrays.stream(clazz.getDeclaredMethods()).forEach(declaredMethod -> {
                final RequestMapping requestMapping = declaredMethod.getDeclaredAnnotation(RequestMapping.class);

                Arrays.stream(getRequestMethod(requestMapping))
                    .forEach(requestMethod -> {
                        handlers.put(new HandlerKey(requestMethod, requestMapping.value()),
                            new AnnotationHandler(clazz, declaredMethod));
                    });
            });
        });
    }

    private RequestMethod[] getRequestMethod(final RequestMapping requestMapping) {
        return requestMapping.method();
    }

    @Override
    public Object findHandler(final HandlerKey handlerKey) {
        return handlers.get(handlerKey);
    }
}
