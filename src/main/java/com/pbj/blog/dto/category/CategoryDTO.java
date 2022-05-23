package com.pbj.blog.dto.category;

import com.pbj.blog.domain.Article;
import com.pbj.blog.domain.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO {

    private Long id;
    private String name;
    private List<Article> articles;
    private int articleAmount;


    public CategoryDTO(Category category){

        this.id = category.getId();
        this.name = category.getName();

        if(category.getArticles() != null){
            this.articles = category.getArticles();
            this.articleAmount = category.getArticles().size();
        }else {
            this.articles = new ArrayList<>();
            this.articleAmount = 0;
        }

    }


}