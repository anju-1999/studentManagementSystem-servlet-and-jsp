package student.service;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebFilter("/scoredetails2")
public class StudentScoreDetailsFilter extends HttpFilter implements Filter {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public StudentScoreDetailsFilter() {
		super();
	}


	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		HttpServletRequest request2=(HttpServletRequest) request;
		HttpServletResponse response2=(HttpServletResponse) response;

		HttpSession session=request2.getSession(false);
		if(session==null || session.getAttribute("username")==null )
		{
			response2.sendRedirect("LoginPage2.jsp");
			return;
		}
		chain.doFilter(request, response);
	}


}
