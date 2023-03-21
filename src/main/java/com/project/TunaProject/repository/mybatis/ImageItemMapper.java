package com.project.TunaProject.repository.mybatis;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.project.TunaProject.domain.Image;


@Mapper
public interface ImageItemMapper {
	public Integer insert(Image image);
	
	public Image selectByImageCode(String ImageCode);
	
	public List<Image> selectAll(String code);
	
	public void update(@Param("imageCode")String postCode, @Param("updatePost")Image image);
	
	public void deleteByImageCode(int ImageCode);

	public void deleteByPostCode(String postCode);
}
