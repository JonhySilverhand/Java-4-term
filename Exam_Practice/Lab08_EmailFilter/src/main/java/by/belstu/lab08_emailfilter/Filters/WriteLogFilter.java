package by.belstu.lab08_emailfilter.Filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.time.LocalDateTime;


@WebFilter(filterName = "WriteLogFilter", urlPatterns = {"/Servlet"})
public class WriteLogFilter implements Filter {
    Logger LOGGER;

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        LOGGER.info("Current time is: " + LocalDateTime.now());
        LOGGER.info(req.getLocalAddr());
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {
        LOGGER = Logger.getLogger(WriteLogFilter.class.getName());
    }
}
