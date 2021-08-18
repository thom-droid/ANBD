package com.ktx.ddep.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.ktx.ddep.config.ApplicationConfig;
import com.ktx.ddep.dto.member.Member;
import com.ktx.ddep.service.MembersService;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {ApplicationConfig.class})
public class MemberControllerTest {

	@InjectMocks
	MemberController controller;
	
	@Mock
	private MembersService service;
	
	// TestRestTemplate template;

	private MockMvc mockMvc;
	
	@Before
	public void createMockInstance() {
		MockitoAnnotations.openMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}
	
	@Test
	public void testMemberInfo() throws Exception {

		//given
		int no = 12;
		
		Member testDto = new Member();
		testDto.setName("bang");
		testDto.setNickname("고추잡채");
		testDto.setNo(no);
		
		//when
		doReturn(testDto).when(service).memberInfo(no);
		
		
		//then
		//api validation check
		mockMvc.perform(get("/testjson/{no}", no)).andExpect(status().isOk())
				.andExpect(jsonPath("$.no", is(12)))
				.andExpect(jsonPath("$.name", is("bang")))
				.andExpect(jsonPath("$.nickname", is("고추잡채")))
				.andDo(print());
		
	}
	
	@Test
	public void givenRequest_whenRequestIsExecuted_thenIsContentTypeJson() throws Exception {
		
		//given
		String expectedMimeType = "application/json";
		
		String email ="wlgypark97%40naver.com";
		
		HttpUriRequest request = new HttpGet("http://localhost:8090/signup/ajax/email_duplication_check?email="+email);
		
		//when
		HttpResponse response = HttpClientBuilder.create().build().execute(request);
		
		//then
		//assert if content-type is application/json 
		String mimeType = ContentType.getOrDefault(response.getEntity()).getMimeType();
		assertThat(expectedMimeType).isEqualTo(mimeType);
		
		//assert if returned String is 1 (the case where the email already exists)
		HttpEntity responseEntity = response.getEntity();
		
		String expectedResult = EntityUtils.toString(responseEntity);
		assertThat(expectedResult).isEqualTo("1");
	}
	
}
