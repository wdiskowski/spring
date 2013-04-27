package de.modulware.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class TraegerRequestWrapper extends HttpServletRequestWrapper {

	private String traegerName;
	
	public TraegerRequestWrapper(HttpServletRequest request, String traegerName) {
		super(request);
		this.traegerName = traegerName;
	}
	
	@Override
	public String getServletPath(){
		String servletPath = super.getServletPath();
		int start = servletPath.indexOf(traegerName);
		if(start >= 0){
			int end = start + traegerName.length();
			servletPath = servletPath.substring(end);
		}
		return servletPath;
	}
	
	@Override
	public String getContextPath(){
		String contextPath = super.getContextPath();
		if(super.getServletPath().contains(traegerName)){
			contextPath = contextPath + "/" + traegerName;
		}
		return contextPath;
	}

}
