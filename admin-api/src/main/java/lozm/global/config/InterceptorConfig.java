package lozm.global.config;

import lozm.object.vo.sign.SignVo;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static lozm.object.code.SessionType.*;

@Component
public class InterceptorConfig extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession httpSession = request.getSession();

        String reqUri = request.getRequestURI();
        if (
                reqUri.contains("/api")
                || reqUri.contains("/images")
                || reqUri.contains("/libs")
                || reqUri.contains("/script")
                || reqUri.contains("/layout")
                || reqUri.contains("/error")
                || reqUri.contains("/sign")
                || reqUri.contains("/download")
        ) {
            return super.preHandle(request, response, handler);
        }

        SignVo userSession = (SignVo) httpSession.getAttribute(USER.name());
        if (userSession == null) {
            httpSession.setAttribute(PREV_PAGE.name(), reqUri);
            response.sendRedirect("/sign/out");

            return false;
        }

        return super.preHandle(request, response, handler);
    }

}

