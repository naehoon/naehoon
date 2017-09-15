import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class FilterTest01 implements Filter{

	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest h_req = (HttpServletRequest)req;
		String method = h_req.getMethod();
		
		if(method.equalsIgnoreCase("POST")){
			req.setCharacterEncoding("utf-8");
		}
		
		//chain 이란 연계 시킨다는 뜻이다. 
		//만약 필터가 여러개일경우 다른 필터도 처리하라는 뜻이다.
		//dofilter 의 마지막은 항상 이렇게 끝나야 한다
		chain.doFilter(req, resp);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
	}

}
