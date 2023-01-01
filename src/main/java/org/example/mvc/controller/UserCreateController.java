package org.example.mvc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.example.mvc.model.User;
import org.example.mvc.repository.UserRepository;

public class UserCreateController implements Controller {

    @Override
    public String handleRequest(final HttpServletRequest request, final HttpServletResponse response) throws Exception {
        UserRepository.save(new User(request.getParameter("id"), request.getParameter("name")));

        return "redirect:/users";
    }
}
