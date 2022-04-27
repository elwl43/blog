package com.pbj.blog.dto.article;

import com.pbj.blog.domain.Article;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleModifyForm {

    private String title;

    private String body;

    public ArticleModifyForm(Article findArticle){
        this.title = findArticle.getTitle();
        this.body = findArticle.getBody();
    }

}
