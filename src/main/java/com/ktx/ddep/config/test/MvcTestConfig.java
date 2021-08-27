package com.ktx.ddep.config.test;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.ktx.ddep.argumentresolver.LoginUserArgResolver;

@Configuration
public class MvcTestConfig implements WebMvcConfigurer{

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
}
