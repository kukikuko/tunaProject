package com.project.TunaProject.eg.repository;

import java.util.List;

import com.project.TunaProject.eg.domain.Image;

public interface ImageRepository {
	public Image insert(Image image);
	
	public Image selectByImageCode(String ImageCode);
	
	public List<Image> selectAll();
	
	public boolean update(String ImageCode,Image image);
	
	public void deleteByImageCode(String ImageCode);
}
