package de.modulware.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class TraegerFilter extends OncePerRequestFilter implements TraegerContext {

	private static ThreadLocal<String> traegerNameContainer = new ThreadLocal<String>();
	
	private static String TRAEGER_NAME_KEY = "traeger.name";

	@Override
	protected void doFilterInternal(HttpServletRequest request,
			HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
	
		String traegerName = getTraegerName(request);
		traegerNameContainer.set(traegerName);
		
		HttpSession session = request.getSession();
		String oldTraegerName = getOldTraegerName(session);
		session.setAttribute(TRAEGER_NAME_KEY, traegerName);
		if(oldTraegerName != null && !oldTraegerName.equals(traegerName)){
			session.invalidate();
		}
		try{
			filterChain.doFilter(new TraegerRequestWrapper(request, traegerName), response);
		}finally{
			traegerNameContainer.remove();
		}
	}

	private String getOldTraegerName(HttpSession session) {
		String oldTraegerName = (String)session.getAttribute(TRAEGER_NAME_KEY);
		return oldTraegerName;
	}
	
	private String getTraegerName(HttpServletRequest request) {
		String traegerName = "traeger1";
		if(request.getServletPath().contains("traeger2")){
			traegerName = "traeger2";
		}
		return traegerName;
	}

	public String getTraegerName(){
		return traegerNameContainer.get();
	}
}
