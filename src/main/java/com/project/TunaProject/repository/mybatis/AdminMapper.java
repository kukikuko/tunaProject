package com.project.TunaProject.repository.mybatis;

import com.project.TunaProject.domain.Category;
import com.project.TunaProject.domain.MemberVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AdminMapper {

    public List<MemberVO> selectAll();

    public void memberStatus(@Param("memberCode") int memberCode, @Param("status") String status);

    public void postStatus(@Param("postCode") String postCode, @Param("status") String status);

    public List<Category> categoryAll();
}
