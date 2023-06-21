package by.belstu.lab09_filterlog;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.Date;
@WebFilter(filterName = "DateFilter", urlPatterns = {"/ServletFilter"})
public class DateFilter implements Filter {

    private static final Logger LOGGER = Logger.getLogger(Servlet.class);

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        Date date = new Date();
        HttpServletRequest request = (HttpServletRequest) servletRequest;

        LOGGER.info(date.toString() + ", URI: " + request.getRequestURI()+ ", host: " + request.getServerName() + ", port: " + request.getServerPort() + ", locale: " + request.getLocale()) ;
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
