package com.interceptor;

import com.comm.Defs;
import com.wangpos.wcomp.util.ConfUtil;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SessionInterceptor implements HandlerInterceptor {
    private static Logger logger = Logger.getLogger(SessionInterceptor.class);

    @Resource(name = "nameJdbcTpl")
    private NamedParameterJdbcTemplate nameJdbcTpl;
    

    public boolean preHandle(HttpServletRequest req, HttpServletResponse response, Object o) throws Exception {
        HttpSession session = req.getSession();

        // 请求的uri
        String uri = req.getRequestURI();
        String ctxpath = req.getContextPath();

        if (!ctxpath.endsWith("/")) {
            ctxpath += "/";
        }

        if (ctxpath.equalsIgnoreCase(uri)) {
            return true;
        }

        //要过滤的配置url
        for (String s : Defs.notFilter) {
            if (uri.indexOf(s) != -1) {
                return true;
            }
        }

        //从session中拿，看看有没有
        if (session != null && session.getAttribute(Defs.Login_User) != null) {
            return true;
        }

        response.sendRedirect(ConfUtil.getConf("LOCAL_URL")+"index/loginPage");

        return false;
    }

    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
    }

    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
    }

}
