package com.ktx.ddep.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.ktx.ddep.config.DaoTestConfig;
import com.ktx.ddep.controller.MemberController;
import com.ktx.ddep.dao.member.AddressDAO;
import com.ktx.ddep.dao.member.MembersDAO;
import com.ktx.ddep.dto.member.Address;
import com.ktx.ddep.dto.member.Member;
import com.ktx.ddep.service.MembersService;
import com.ktx.ddep.service.MembersServiceImpl;

/**
 * test class for Dao classes to see if they work properly either in transactional context or alone.  
 * 
 * @author thom
 *
 */
@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes = DaoTestConfig.class)
@Transactional
public class DaoTest {

	@InjectMocks
	private MemberController controller;
	
	@Mock
	private MembersServiceImpl service;
	
	@Mock
	AddressDAO addressDao;
	
	@Mock
	MembersDAO membersDao;
	
	@Before
	public void instantiateMock() {
		MockitoAnnotations.openMocks(controller);
	}
	
	@Test
	public void testInsertAddress() {
		
		Address mockedAddr = mock(Address.class);
		mockedAddr.setSido("서울");
		mockedAddr.setGugun("영등포구");
		mockedAddr.setDong("도림동");
		mockedAddr.setLat(124.000);
		mockedAddr.setLng(34.000);
		
		try {
			addressDao.insertAddress(mockedAddr);
			System.out.println(mockedAddr.getNo());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		verify(addressDao).insertAddress(mockedAddr);
		
	}
	
	@Test
	public void testAddMember() {
		
		//given 
		Address mockedAddr = mock(Address.class);
		mockedAddr.setSido("서울");
		mockedAddr.setGugun("영등포구");
		mockedAddr.setDong("도림동");
		mockedAddr.setLat(124.000);
		mockedAddr.setLng(34.000);
		
		Member mockedMember = mock(Member.class);
		mockedMember.setEmail("qkrwlgy@gmail.com");
		mockedMember.setName("박지효");
		mockedMember.setNickname("송지효아님");
		mockedMember.setPassword("asdasd12!Q");
		mockedMember.setPhoneNumber("01012341234");
		
		//when
		when(service.addMember(mockedMember, mockedAddr)).thenReturn(1);
		
		//then 
		// check if methods is invoked and return 1
		assertThat(service.addMember(mockedMember, mockedAddr)).isEqualTo(1);
		
		verify(service, times(1)).addMember(mockedMember, mockedAddr);
		
	}
	
	
}
