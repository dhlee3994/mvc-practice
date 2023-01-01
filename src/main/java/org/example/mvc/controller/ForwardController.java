package org.example.mvc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ForwardController implements Controller {

    private final String forwardUri;

    public ForwardController(final String forwardUri) {
        this.forwardUri = forwardUri;
    }

    @Override
    public String handleRequest(final HttpServletRequest request, final HttpServletResponse response) throws Exception {
        return forwardUri;
    }
}
