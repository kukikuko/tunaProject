package com.project.TunaProject.repository;

import com.project.TunaProject.domain.Heart;

public interface HeartRepository {

	public void insertHeart(Heart heart);
	
	public Integer countHeart(Heart heart);
	
	public void deleteHeart(Heart heart);
}
