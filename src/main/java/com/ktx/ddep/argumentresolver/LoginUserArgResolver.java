package com.ktx.ddep.argumentresolver;

import javax.servlet.http.HttpSession;

import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.ktx.ddep.dto.member.SessionUser;
import com.ktx.ddep.security.LoginUser;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class LoginUserArgResolver implements HandlerMethodArgumentResolver{
	
	private final HttpSession session; 
	
	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		
		boolean isLoginUserAnnotated = parameter.getParameterAnnotation(LoginUser.class) != null;
		
		boolean isSessionUserClass = SessionUser.class.equals(parameter.getParameterType());
		
		return isLoginUserAnnotated && isSessionUserClass;
	}
	
	
	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
		
		return session.getAttribute("loggedInUser");
	}

	
}
