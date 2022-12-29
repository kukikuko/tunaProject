package com.project.TunaProject.repository;

import java.util.List;

import com.project.TunaProject.domain.Image;

public interface ImageRepository {
	public Image insert(Image image);
	
	public Image selectByImageCode(String ImageCode);
	
	public List<Image> selectAll(String code);
	
	public boolean update(String ImageCode,Image image);
	
	public void deleteByImageCode(String ImageCode);
}
