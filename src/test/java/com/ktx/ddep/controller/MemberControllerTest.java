package com.ktx.ddep.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.ktx.ddep.config.ApplicationConfig;
import com.ktx.ddep.service.MembersService;
import com.ktx.ddep.vo.Member;

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
	
	
	
}
