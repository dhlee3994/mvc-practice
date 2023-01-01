package org.example.mvc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.example.mvc.repository.UserRepository;

public class UserListController implements Controller {

    @Override
    public String handleRequest(final HttpServletRequest request, final HttpServletResponse response) throws Exception {
        request.setAttribute("users", UserRepository.findAll());
        return "/user/list";
    }
}
