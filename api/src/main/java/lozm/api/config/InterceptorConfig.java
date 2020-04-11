package lozm.api.config;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component
public class InterceptorConfig extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession httpSession = request.getSession();

        String reqUri = request.getRequestURI();
        if (reqUri.contains("/login")) {
            return super.preHandle(request, response, handler);
        }

        if (reqUri.startsWith("/")) {
//            LoginVo userSession = (LoginVo) httpSession.getAttribute(CommonUtility.SESSION.USER.getCode());
//            if (ObjectUtility.isEmpty(userSession)) {
//                httpSession.setAttribute(CommonUtility.SESSION.PREV_PAGE.getCode(), reqUri);
//                response.sendRedirect("/web/login");
//
//                return false;
//            }
        }

        return super.preHandle(request, response, handler);
    }

}

