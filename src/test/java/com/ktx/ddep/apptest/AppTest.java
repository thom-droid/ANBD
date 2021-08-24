package com.ktx.ddep.apptest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.ktx.ddep.config.ApplicationConfig;
import com.ktx.ddep.config.SecurityConfig;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {ApplicationConfig.class, SecurityConfig.class})
public class AppTest {

	@Test
	public void givenDependency_whenBuild_thenItSuccess() {
		
	}
}
