package com.project.TunaProject.repository;

import java.util.List;

import com.project.TunaProject.domain.Category;
import com.project.TunaProject.domain.Post;


public interface CategoryRepository {

	public List<Category> selectAll();

}
