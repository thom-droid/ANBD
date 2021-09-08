package com.ktx.ddep.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.LinkedList;

import static org.mockito.ArgumentMatchers.anyInt;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.ktx.ddep.config.test.DaoTestConfig;
import com.ktx.ddep.controller.MemberController;
import com.ktx.ddep.dao.market.MarketsDAO;
import com.ktx.ddep.dao.member.AddressDAO;
import com.ktx.ddep.dao.member.MemberRoleDAO;
import com.ktx.ddep.dao.member.MembersDAO;
import com.ktx.ddep.dao.recipe.RcpRvsDAO;
import com.ktx.ddep.dao.recipe.RcpsDAO;
import com.ktx.ddep.dto.member.Address;
import com.ktx.ddep.dto.member.Member;
import com.ktx.ddep.dto.member.MemberRole;
import com.ktx.ddep.dto.member.SessionUser;
import com.ktx.ddep.dto.recipe.Rcp;
import com.ktx.ddep.dto.recipe.RcpRv;

/**
 * test class for Dao classes to see if they work properly either in transactional context or alone.  
 * 
 * @author thom
 *
 */
@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes = DaoTestConfig.class)
@Transactional
public class MemberServiceDaoTest {

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
	
	@Mock
	MarketsDAO marketsDao;
	
	@Mock
	RcpsDAO rcpsDao;
	
	@Mock
	RcpRvsDAO rcpRvsDao;
	
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
	
	@Test
	public void testServiceMypage() {
		SessionUser user = mock(SessionUser.class);
		Member member = mock(Member.class);
		HashMap<String, Object> anyMap = mock(HashMap.class);
		
		when(service.getMypageInfoByMember(user)).thenReturn(anyMap);
		membersDao.selectOne(user.getNo());
		marketsDao.selectOne(user.getNo());
		when(membersDao.selectTotalRankerOne(anyInt())).thenReturn(member);
		
		assertThat(membersDao.selectTotalRankerOne(0)).isEqualTo(member);
		assertThat(service.getMypageInfoByMember(user)).isEqualTo(anyMap);
		
		verify(service).getMypageInfoByMember(user);
		verify(membersDao).selectOne(anyInt());
		verify(marketsDao).selectOne(user.getNo());
		
	}
	
	@Test
	public void testMyRecipes() {
		
		LinkedList<Rcp> list = new LinkedList<Rcp>();
		Rcp item1 = Rcp.builder()
				.img("img")
				.itemCount(1)
				.memberNo(8)
				.nickname("짜릿한슬라임")
				.no(2)
				.price(5600)
				.profileImg("profile.jpg")
				.rcpsAvg(100)
				.regdate(new Timestamp(2020))
				.ribbonItem("cheap")
				.scrapCount(10)
				.status('c')
				.title("김치찌개")
				.viewCount(1)
				.build();
		
		list.add(item1);
		
		when(rcpsDao.selectMyRcps(anyInt())).thenReturn(list);
		
		assertThat(rcpsDao.selectMyRcps(anyInt()).get(0)).isEqualTo(item1);
		
		
	}
	
	@Test
	public void tesGetTmpRecipes() {
		
		LinkedList<Rcp> list = new LinkedList<Rcp>();
		Rcp item1 = Rcp.builder()
				.img("img")
				.itemCount(1)
				.memberNo(8)
				.nickname("짜릿한슬라임")
				.no(2)
				.price(5600)
				.profileImg("profile.jpg")
				.rcpsAvg(100)
				.regdate(new Timestamp(2020))
				.ribbonItem("cheap")
				.scrapCount(10)
				.status('w')
				.title("김치찌개")
				.viewCount(1)
				.build();
		
		list.add(item1);
		
		when(rcpsDao.selectTmpRcps(anyInt())).thenReturn(list);
		
		assertThat(rcpsDao.selectTmpRcps(anyInt()).get(0)).isEqualTo(item1);
		
		verify(rcpsDao).selectTmpRcps(anyInt());
	}
	
	@Test
	public void testDeleteTmpRecipe() {
		
		when(rcpsDao.deleteTmpRcps(anyInt())).thenReturn(1);
		
		rcpsDao.deleteTmpRcps(1);
		
		verify(rcpsDao).deleteTmpRcps(anyInt());
		
	}
	
	@Test
	public void testInsertRcpRv() {
		
		RcpRv rv = new RcpRv();
		when(rcpRvsDao.insertRcpRv(rv)).thenReturn(1);
		
		assertThat(rcpRvsDao.insertRcpRv(rv)).isEqualTo(1);
		
		verify(rcpRvsDao).insertRcpRv(rv);
	}
	
	
}
