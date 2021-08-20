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
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ktx.ddep.config.ApplicationConfig;
import com.ktx.ddep.dto.member.Address;
import com.ktx.ddep.dto.member.Member;
import com.ktx.ddep.service.MembersService;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {ApplicationConfig.class})
public class MemberControllerTest {

	@Autowired
	private MembersService service;
	
	@Autowired
	private PasswordEncoder encoder;
	
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
	public void givenRequest_whenRequestIsExecuted_thenIsContentTypeAndContentJson() throws Exception {
		
		//given
		String expectedMimeType = "application/json";
		
		String email ="wlgypark97%40naver.com";
		
		HttpUriRequest request = new HttpGet("http://localhost:8090/signup/ajax/email_duplication_check?email="+email);
		
		//when
		HttpResponse response = HttpClientBuilder.create().build().execute(request);
		
		
		//then
		HttpEntity responseEntity = response.getEntity();
		String mimeType = ContentType.getOrDefault(responseEntity).getMimeType();
		String expectedResult = EntityUtils.toString(responseEntity);
		
		//assert if content-type is application/json 
		assertThat(expectedMimeType).isEqualTo(mimeType);
		
		//assert if returned String is 1 (the case where the email already exists)
		
		assertThat(expectedResult).isEqualTo("1");
	}
	
	// test for ajax at signup page
	@Test
	public void givenRequestWithNickname_whenGetRequest_thenIsContentTypeAndContentJson() throws Exception {
		
		//given
		String nickname ="짜릿한슬라임";
		
		String contentType = "application/json";
		
		HttpUriRequest request = new HttpGet(defaultUrl+"/signup/ajax/nickname_duplication_check?nickname="+nickname);
		
		//when
		HttpResponse response = HttpClientBuilder.create().build().execute(request);
		 
		
		//then
		int status = response.getStatusLine().getStatusCode();
		
		if(status==200) {
			HttpEntity responseEntity = response.getEntity();
			log.debug("responseEntity {}", responseEntity);
			
			String resultContentType = ContentType.get(responseEntity).getMimeType();
			String expectedResult = EntityUtils.toString(responseEntity);
			
			assertThat(resultContentType).isEqualTo(contentType);
			assertThat(expectedResult).isEqualTo("1");
		}
		
	}
	
	@Test
	// with this annotation a transaction in test context is run and any data access expect to be rolled back 
	@Transactional
	public void givenJson_whenPostRequest_thenIsContentJson() throws ClientProtocolException, IOException {
		
		// given 
		CloseableHttpClient client = HttpClients.createDefault();
		
		String requestJson = "{\"Member\":{\"email\":\"qkrwlgy@gmail.com\",\"password\":\"asdasd12!Q\",\"name\":\"박지효\",\"nickname\":\"송지효아님\",\"phoneNumber\":\"01012341234\"},\"Address\":{\"sido\":\"서울\",\"sigungu\":\"동작구\",\"dong\":\"대방동\",\"lat\":\"37.4990779348649\",\"lng\":\"126.928764031881\"}}";
		

		HttpPost request = new HttpPost(defaultUrl+"/signup");
		
		// set json to entity but with ContentType.Text.Plain
		
		StringEntity entity = new StringEntity(requestJson);
		request.setEntity(entity);
		request.setHeader("Accept", "application/json");
		request.setHeader("Content-type","application/json");
		
		// when
		// request is sent with json
		CloseableHttpResponse response = client.execute(request);
		log.debug("response {}", response );
		
		// then
		
		// status
		int responseStauts = response.getStatusLine().getStatusCode();
		assertThat(responseStauts).isEqualTo(200);
		
		if(responseStauts == 200) {
		
			HttpEntity responseEntity = response.getEntity();
			log.debug("responseEntity {}", responseEntity);
			
			
			// check content type
			String responseContentType = ContentType.get(responseEntity).getMimeType();
			assertThat(responseContentType).isEqualTo("application/json");
			
			// check returned value
			String expectedContent = EntityUtils.toString(responseEntity);
			assertThat(expectedContent).isEqualTo(1);
		}
		
		client.close();
	
	}
	
	@Test
	public void extractJsonIntoObject() throws JsonMappingException, JsonProcessingException {
		
		Member expectedMember = new Member();
		expectedMember.setEmail("qkrwlgy@gmail.com");
		expectedMember.setPassword("asdasd12!Q");
		expectedMember.setName("박지효");
		expectedMember.setNickname("송지효아님");
		
		String requestJson = "{\"Member\":{\"email\":\"qkrwlgy@gmail.com\",\"password\":\"asdasd12!Q\",\"name\":\"박지효\",\"nickname\":\"송지효아님\",\"phoneNumber\":\"01012341234\"},\"Address\":{\"sido\":\"서울\",\"sigungu\":\"동작구\",\"dong\":\"대방동\",\"lat\":\"37.4990779348649\",\"lng\":\"126.928764031881\"}}";
		
		ObjectMapper mapper = new ObjectMapper();
		
		Map<String, Object> map = mapper.readValue(requestJson, new TypeReference<Map<String, Object>>() {});
		
		Member member = mapper.convertValue(map.get("Member"), Member.class);
		
		// assert if values from both objects are the same
		assertThat(member.getEmail()).isEqualTo(expectedMember.getEmail());
		assertThat(member.getName()).isEqualTo(expectedMember.getName());
		assertThat(member.getNickname()).isEqualTo(expectedMember.getNickname());
		assertThat(member.getPassword()).isEqualTo(expectedMember.getPassword());
		
	}

}
