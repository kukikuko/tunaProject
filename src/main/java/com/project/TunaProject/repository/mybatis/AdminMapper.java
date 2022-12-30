package com.project.TunaProject.repository.mybatis;

import com.project.TunaProject.domain.MemberVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminMapper {

    public List<MemberVO> selectAll();
}
