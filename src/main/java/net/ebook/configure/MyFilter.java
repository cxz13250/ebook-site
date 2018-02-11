package net.ebook.configure;

import org.apache.http.protocol.HTTP;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @Author ROKG
 * @Description
 * @Date: Created in 下午9:03 2018/2/11
 * @Modified By:
 */
@WebFilter(filterName = "sessionCheck",urlPatterns = "/api/*")
public class MyFilter implements Filter{

    private static final Logger LOG= Logger.getLogger(MyFilter.class);

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException{
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        String url=request.getRequestURI().substring(request.getContextPath().length());
        if(url.indexOf("/api/user/login")>-1){
            chain.doFilter(request,response);
            return;
        }

        HttpSession session=request.getSession();
        if(session.getAttribute("id")!=null){
            chain.doFilter(request,response);
        }else {
            LOG.trace("a stupid man wants to inject us");
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            return;
        }
    }

    @Override
    public void destroy(){

    }

    @Override
    public void init(FilterConfig config){

    }
}
