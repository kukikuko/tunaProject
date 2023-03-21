package com.project.TunaProject.validation;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.project.TunaProject.domain.MemberVO;


@Component
public class MemberValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return MemberVO.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		
		MemberVO memberVO = (MemberVO)target;
		
		if(!StringUtils.hasText(memberVO.getMemberMail())) {
			errors.rejectValue("eamil", null, "아이디는 필수 입력입니다.");			
		}
		if(!StringUtils.hasText(memberVO.getPassword())) {
			errors.rejectValue("password", null, "비밀번호는 필수 입력입니다.");
		}
	}

}
