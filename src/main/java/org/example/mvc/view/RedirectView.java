package org.example.mvc.view;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RedirectView implements View {

    public static final String DEFAULT_REDIRECT_PREFIX = "redirect:";
    private static final int DEFAULT_REDIRECT_PREFIX_LENGTH = DEFAULT_REDIRECT_PREFIX.length();

    public final String name;

    public RedirectView(final String name) {
        this.name = name;
    }

    @Override
    public void render(final Map<String, ?> model, final HttpServletRequest request, final HttpServletResponse response)
        throws Exception {
        response.sendRedirect(name.substring(DEFAULT_REDIRECT_PREFIX_LENGTH));
    }
}
