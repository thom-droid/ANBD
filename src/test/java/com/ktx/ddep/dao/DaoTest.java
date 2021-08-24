package com.ktx.ddep.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.anything;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.anyInt;

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
import com.ktx.ddep.dao.member.MemberRoleDAO;
import com.ktx.ddep.dao.member.MembersDAO;
import com.ktx.ddep.dto.member.Address;
import com.ktx.ddep.dto.member.Member;
import com.ktx.ddep.dto.member.MemberRole;
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
	
	@Mock
	MemberRoleDAO memberRoleDao;
	
	@Before
	public void instantiateMock() {
		MockitoAnnotations.openMocks(this);
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
		Member mockedMember = mock(Member.class);
		MemberRole mockedMemberRole = mock(MemberRole.class);
		
		//when
		when(service.addMember(mockedMember, mockedAddr)).thenReturn(1);
		when(addressDao.insertAddress(mockedAddr)).thenReturn(1);
		when(membersDao.insertJoinMember(mockedMember)).thenReturn(1);
		when(memberRoleDao.insertMemberRole(mockedMemberRole)).thenReturn(1);
		
		//then 
		// check when each method is invoked it returns 1
		assertThat(service.addMember(mockedMember, mockedAddr)).isEqualTo(1);
		assertThat(addressDao.insertAddress(mockedAddr)).isEqualTo(1);
		assertThat(membersDao.insertJoinMember(mockedMember)).isEqualTo(1);
		assertThat(memberRoleDao.insertMemberRole(mockedMemberRole)).isEqualTo(1);
		
		// verify each method is invoked 
		verify(service, times(1)).addMember(mockedMember, mockedAddr);
		verify(addressDao, times(1)).insertAddress(mockedAddr);
		verify(membersDao).insertJoinMember(mockedMember);
		verify(memberRoleDao).insertMemberRole(mockedMemberRole);
		
	}
	
	
	
}
