package com.project.TunaProject.repository;

import com.project.TunaProject.domain.Category;
import com.project.TunaProject.domain.MemberVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdminRepository {

    public List<MemberVO> selectAll();

    public void memberStatus(int memberCode, String status);

    public void postStatus(String postCode, String status);

    public List<Category> categoryAll();
}
