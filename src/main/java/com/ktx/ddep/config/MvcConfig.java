package com.ktx.ddep.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.ktx.ddep.argumentresolver.LoginUserArgResolver;
import com.ktx.ddep.interceptor.HandlerMappingInterceptor;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.ktx.ddep.controller", "com.ktx.ddep.argumentresolver"})
public class MvcConfig implements WebMvcConfigurer {

	@Autowired
	private LoginUserArgResolver loginUserArgResolver;
	
	// enable default servlet when requests other than that made for spring is made
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

	// jsp view resolver
	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		registry.jsp("/WEB-INF/view/", ".jsp");
	}

	// redirect request "/" to "/main"
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addRedirectViewController("/", "/main");
	}
	
	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
		
		resolvers.add(loginUserArgResolver);
	}
	
	
	// adds interceptor
//	@Override
//	public void addInterceptors(InterceptorRegistry registry) {
//		registry.addInterceptor(new HandlerMappingInterceptor());
//	}

	// resolver for resources
//	@Override public void addResourceHandlers(final ResourceHandlerRegistry registry) {
//		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/"); 
//		
//	}
	
	
}
