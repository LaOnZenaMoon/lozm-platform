package lozm.config;

import lozm.vo.sign.SignVo;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static lozm.code.SessionType.*;

@Component
public class InterceptorConfig extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession httpSession = request.getSession();

        String reqUri = request.getRequestURI();
        if (
                reqUri.contains("/sign")
                || reqUri.contains("/error")
                || reqUri.contains("/libs")
                || reqUri.contains("/script")
                || reqUri.contains("/favicon")
                || reqUri.contains("/asset")
        ) {
            return super.preHandle(request, response, handler);
        }

        if (!reqUri.contains("/api")) {
            SignVo userSession = (SignVo) httpSession.getAttribute(USER.name());
            if (userSession == null) {
                httpSession.setAttribute(PREV_PAGE.name(), reqUri);
                response.sendRedirect("/signOut");

                return false;
            }
        }

        return super.preHandle(request, response, handler);
    }

}

