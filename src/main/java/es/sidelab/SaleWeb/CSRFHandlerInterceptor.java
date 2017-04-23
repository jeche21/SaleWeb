package es.sidelab.SaleWeb;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class CSRFHandlerInterceptor extends HandlerInterceptorAdapter{
	
	@Override
	 public void postHandle(final HttpServletRequest request,
	 final HttpServletResponse response, final Object handler,
	 final ModelAndView modelAndView) throws Exception {
		
		CsrfToken token = (CsrfToken) request.getAttribute("_csrf");
		modelAndView.addObject("token", token.getToken());
	 }

}
