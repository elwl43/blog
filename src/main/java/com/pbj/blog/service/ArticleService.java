package com.pbj.blog.service;

import com.pbj.blog.dao.ArticleRepository;
import com.pbj.blog.domain.Article;
import com.pbj.blog.domain.Member;
import com.pbj.blog.dto.article.ArticleSaveForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;

    @Transactional
    public void save(ArticleSaveForm articleSaveForm, Member member) {

        Article article = Article.createArticle(
                articleSaveForm.getTitle(),
                articleSaveForm.getBody()
        );

        article.setMember(member);

        articleRepository.save(article);

    }
}