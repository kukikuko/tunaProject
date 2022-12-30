package com.project.TunaProject.repository;

import com.project.TunaProject.domain.Category;
import com.project.TunaProject.domain.MemberVO;

import java.util.List;

public interface AdminRepository {

    public List<MemberVO> selectAll();

}
