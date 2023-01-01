package org.example.mvc;

public interface HandlerMapping {

    void init();

    Object findHandler(HandlerKey handlerKey);

}
