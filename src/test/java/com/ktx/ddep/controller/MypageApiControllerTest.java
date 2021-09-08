package com.ktx.ddep.controller;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ktx.ddep.config.test.ApiTestConfig;
import com.ktx.ddep.dto.member.Address;
import com.ktx.ddep.dto.member.SessionUser;
import com.ktx.ddep.dto.recipe.Rcp;
import com.ktx.ddep.service.RecipesService;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {ApiTestConfig.class})
@Transactional
public class MypageApiControllerTest {

	@Mock
	private MypageApiController apiController;
	
	@Mock
	private RecipesService rcpService;
	
	@Mock
	private List<Rcp> rcpList;
	
	// private MockHttpSession mockedSession;
	
	private MockMvc mockMvc;
	
	@Before
	public void instantiate() {
		MockitoAnnotations.openMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(apiController).build();
		
		// mockedSession = (MockHttpSession) req.getSession();
	}
	
	@Test
	public void givenUserInfo_whenGetRequest_thenIsStatusOk() throws Exception {
		
		//given
		
		SessionUser user2 = mock(SessionUser.class);
		
		//when
		when(apiController.getMyRecipes(user2)).thenReturn(rcpList);
		
		//then
		mockMvc.perform(get("/mypage/ajax/myrecipes")).andExpect(status().isOk()).andDo(print());
		
	}
	
	@Test
	public void givenMultipartFile_whenPostRequest_thenIsFileNameturned() throws Exception {
		//given 
		
		// multipartFile
		MockMultipartFile mockMultipart = new MockMultipartFile("MockReviewImage", "jihyo.jpg".getBytes());
		
		// when 
		when(apiController.uploadReviewImage(Mockito.any(MultipartFile.class), Mockito.any(HttpServletRequest.class))).thenReturn("String");
		
		assertNotNull(mockMultipart);
		
		// then
		
		mockMvc.perform(MockMvcRequestBuilders.multipart("/mypage/ajax/upload_review_image").file(mockMultipart)
				.characterEncoding("UTF-8")).andExpect(status().isOk())
				.andDo(print());
		
	}
	
	@Test
	public void givenAddress_whenPutRequest_thenIsTestPassed() throws Exception {
		
		//given
		Address addrObj = Address.builder()
		.sido("서울")
		.gugun("마포구")
		.dong("마장동")
		.lat(123.00)
		.lng(123.00)
		.build();
		
		addrObj.setNo(12);
		
		//then
		mockMvc.perform(put("/mypage/ajax/update_address").contentType(MediaType.APPLICATION_JSON).content(asJsonString(addrObj)))
		.andExpect(status().isOk()).andDo(print());
		
	}
	
	
	public static String asJsonString(Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}
}
