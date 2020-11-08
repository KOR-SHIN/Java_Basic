package kr.or.ddit.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

/**
 * Servlet Filter implementation class EncodingFilter
 */
@WebFilter("/EncodingFilter")
public class EncodingFilter implements Filter {


	public void destroy() {
		System.out.println("Encoding Filter destroy");
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		request.setCharacterEncoding("UTF-8");
		System.out.println("Request Encoding is UTF-8");
		
		chain.doFilter(request, response);
		
		response.setCharacterEncoding("UTF-8");
		System.out.println("Response Encoding is UTf-8");
	}

	public void init(FilterConfig fConfig) throws ServletException {
		System.out.println("Encoding Filter start");
	}

}
