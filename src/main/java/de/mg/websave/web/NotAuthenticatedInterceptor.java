package de.mg.websave.web;

import de.mg.websave.service.WebSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class NotAuthenticatedInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (!isLogin(handler)) {
            WebSession session = applicationContext.getBean(WebSession.class);
            if (session.isLoggedOut()) {
                response.sendRedirect("/login");
                return false;
            }
        }
        return true;
    }

    private boolean isLogin(Object handler) {
        if (handler instanceof HandlerMethod) {
            return ((HandlerMethod) handler).getBeanType() == LoginController.class;
        }
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }

}