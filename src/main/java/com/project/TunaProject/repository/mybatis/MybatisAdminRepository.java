package com.project.TunaProject.repository.mybatis;

import com.project.TunaProject.domain.Category;
import com.project.TunaProject.domain.MemberVO;
import com.project.TunaProject.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MybatisAdminRepository implements AdminRepository {

    private final AdminMapper adminMapper;

    @Override
    public List<MemberVO> selectAll() {

        List<MemberVO> memberList = adminMapper.selectAll();
        return memberList;
    }

    @Override
    public void memberStatus(int memberCode, String status) {
        adminMapper.memberStatus(memberCode, status);
    }

    @Override
    public void postStatus(String postCode, String status) {
        adminMapper.postStatus(postCode, status);
    }

    @Override
    public List<Category> categoryAll() {
        return adminMapper.categoryAll();
    }
}
