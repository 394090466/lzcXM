package filter;

import javax.servlet.*;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.http.HttpServletRequest;

public class MyFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest hsr = (HttpServletRequest) servletRequest;
       String userAgent = hsr.getHeader("USER-AGENT");
       System.out.print(userAgent);
        if (userAgent.contains("Android")) {
            // 安卓
            String defaultFailureUrl = "/login_moblie.jsp";
            System.out.println("Android访问！！！" + "没有登录,返回的页面===" + defaultFailureUrl);

        } else if (userAgent.indexOf("iPhone") != -1 || userAgent.indexOf("iPad") != -1) {
            // 苹果

            String defaultFailureUrl = "/login_moblie.jsp";
            System.out.println("iPhone/iPad访问！！！" + "没有登录,返回的页面===" + defaultFailureUrl);

        } else {   // 电脑
            String defaultFailureUrl = "/login.jsp";
            System.out.println("电脑访问！！！" + "没有登录,返回的页面===" + defaultFailureUrl);

            //逻辑处理
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
