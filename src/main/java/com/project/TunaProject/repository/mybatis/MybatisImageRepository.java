package com.project.TunaProject.repository.mybatis;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.project.TunaProject.domain.Image;
import com.project.TunaProject.repository.ImageRepository;

import lombok.RequiredArgsConstructor;


@Repository
@RequiredArgsConstructor
public class MybatisImageRepository implements ImageRepository{

	private final ImageItemMapper imageItemMapper;
	 
	@Override
	public Image insert(Image image) {
		// TODO Auto-generated method stub
		Integer result = imageItemMapper.insert(image);
		return image;
	}

	@Override
	public Image selectByImageCode(String ImageCode) {
		// TODO Auto-generated method stub
		Image image = imageItemMapper.selectByImageCode(ImageCode);
		return image;
	}

	@Override
	public List<Image> selectAll() {
		// TODO Auto-generated method stub
		List<Image> imageList = imageItemMapper.selectAll();
		return imageList;
	}

	@Override
	@Transactional
	public boolean update(String ImageCode, Image image) {
		// TODO Auto-generated method stub
		boolean result = false;
		imageItemMapper.update(ImageCode, image);
		result = true;
		return result;
	}

	@Override
	public void deleteByImageCode(String ImageCode) {
		// TODO Auto-generated method stub
		imageItemMapper.deleteByImageCode(ImageCode);
	}

}
