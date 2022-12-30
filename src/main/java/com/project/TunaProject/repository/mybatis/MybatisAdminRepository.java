package com.project.TunaProject.repository.mybatis;

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
}
