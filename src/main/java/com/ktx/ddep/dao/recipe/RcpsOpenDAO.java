package com.ktx.ddep.dao.recipe;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.ktx.ddep.dto.recipe.RcpRv;

@Mapper
@Repository
public interface RcpsOpenDAO {
	
	public List<RcpRv> selectOpenedRcps(int memberNo);
	public RcpRv selectOpenedRcpForRv(RcpRv openedRcp);

}
