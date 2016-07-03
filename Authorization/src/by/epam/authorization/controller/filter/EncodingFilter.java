package by.epam.authorization.controller.filter;

import javax.servlet.*;
import java.io.IOException;


/**
 * EncodingFilter.java
 * Class implements interface Filter
 * set character encoding to utf-8
 * @author MasSword
 */

public class EncodingFilter implements Filter {
    private String code;

	/**
     * Method initialize variable code from web.xml
     * @param FilterConfig fConfig
     */
    
    public void init(FilterConfig fConfig) throws ServletException {
        code = fConfig.getInitParameter("encoding");
    }

	/**
     * Method sets character encoding
     * @param ServletRequest request, ServletResponse response,FilterChain chain
     */
    
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        String codeRequest = request.getCharacterEncoding();
        if (code != null && !code.equalsIgnoreCase(codeRequest)) {
            request.setCharacterEncoding(code);
            response.setCharacterEncoding(code);
        }
        chain.doFilter(request, response);
    }

	/**
     * Method sets value of variable code to null
     */
    
    public void destroy() {
        code = null;
    }
}
