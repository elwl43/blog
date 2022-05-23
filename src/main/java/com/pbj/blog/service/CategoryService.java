package com.pbj.blog.service;

import com.pbj.blog.dao.CategoryRepository;
import com.pbj.blog.domain.Article;
import com.pbj.blog.domain.Category;
import com.pbj.blog.domain.Member;
import com.pbj.blog.dto.category.CategoryDTO;
import com.pbj.blog.dto.category.CategoryListDTO;
import com.pbj.blog.dto.category.CategoryModifyForm;
import com.pbj.blog.dto.category.CategorySaveForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

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

    public List<CategoryListDTO> findAll() {
        List<Category> categoryList = categoryRepository.findAll();

        List<CategoryListDTO> categoryListDTOS = new ArrayList<>();

        for(Category category : categoryList){
            CategoryListDTO categoryListDTO = new CategoryListDTO(category);
            categoryListDTOS.add(categoryListDTO);
        }

        return categoryListDTOS;
    }

    public Category findById(Long id){

        return categoryRepository.findById(id).orElseThrow(
                () -> {throw new NoSuchElementException("해당 카테고리는 존재하지 않습니다.");
                }
        );

    }

    @Transactional
    public void modifyCategory(CategoryModifyForm categoryModifyForm, Long id) {

        Category findCategory = findById(id);

        findCategory.modifyCategory(
                categoryModifyForm.getId(),
                categoryModifyForm.getName()
        );

    }

    public CategoryDTO getCategory(Long id) {

        Category findCategory = findById(id);

        CategoryDTO categoryDTO = new CategoryDTO(findCategory);

        return categoryDTO;

    }

    @Transactional
    public void delete(Long id) {

        Category findCategory = findById(id);

        categoryRepository.delete(findCategory);
    }

    public CategoryDTO getCategory(String name){

        Category findCategory = findByName(name);

        CategoryDTO categoryDTO = new CategoryDTO(findCategory);

        return categoryDTO;

    }

    public Category findByName(String name){

        Optional<Category> optionalCategory = categoryRepository.findByName(name);

        return optionalCategory.orElseThrow(
                () -> new NoSuchElementException("해당 카테고리는 존재하지 않습니다.")
        );

    }
}