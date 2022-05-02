package com.pbj.blog.service;

import com.pbj.blog.dao.CategoryRepository;
import com.pbj.blog.domain.Member;
import com.pbj.blog.dto.category.CategorySaveForm;
import groovy.lang.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    @Transactional
    public void save(CategorySaveForm categorySaveForm, Member findMember) {

        Category category = Category.createCategory(
                categorySaveForm.getId(),
                categorySaveForm.getName()
        );

        category.setMember(findMember);

        categoryRepository.save(category);

    }
}