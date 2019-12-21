package filters;

import models.Talker;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "TalkerCreateFilter")
public class TalkerCreateFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        if (req instanceof HttpServletRequest) {
            HttpServletRequest httpReq = (HttpServletRequest) req;

            HttpSession session = httpReq.getSession();

            try {
                Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
                if (!Talker.isSessionIdRegistered(session.getId())) {
                    Talker.create(session.getId());
                }
            } catch (Exception e) {
                throw new ServletException("ユーザ登録に失敗しました。" + e.getLocalizedMessage());
            }
        }
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
