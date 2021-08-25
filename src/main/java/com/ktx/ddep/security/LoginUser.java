package com.ktx.ddep.security;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation to get user information in handy. 
 * When used in method parameter with SessionUser class, user info will be stored in HttpSession  
 * 
 * @author bang
 *
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface LoginUser {
	
}
