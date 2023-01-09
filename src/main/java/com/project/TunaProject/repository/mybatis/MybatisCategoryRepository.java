package com.project.TunaProject.repository.mybatis;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.project.TunaProject.domain.Category;
import com.project.TunaProject.repository.CategoryRepository;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MybatisCategoryRepository implements CategoryRepository{

	private final CategoryItemMapper categoryMapper;

	@Override
	public List<Category> selectAll() {
		// TODO Auto-generated method stub
		List<Category> cateList = categoryMapper.selectAll();

		return cateList;
	}

	@Override
	public void updateCtName(Category category) {
		categoryMapper.updateCtName(category);
	}

	@Override
	public void insertCategory(String ctName) {
		categoryMapper.insertCategory(ctName);
	}


}
