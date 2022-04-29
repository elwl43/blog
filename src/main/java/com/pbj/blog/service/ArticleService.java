package com.pbj.blog.service;

import com.pbj.blog.dao.ArticleRepository;
import com.pbj.blog.domain.Article;
import com.pbj.blog.domain.Member;
import com.pbj.blog.dto.article.ArticleDTO;
import com.pbj.blog.dto.article.ArticleListDTO;
import com.pbj.blog.dto.article.ArticleModifyForm;
import com.pbj.blog.dto.article.ArticleSaveForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


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

    public Article findById(Long id) {

        Optional<Article> articleOptional = articleRepository.findById(id);

        articleOptional.orElseThrow(
                () -> new IllegalStateException("존재하지 않는 게시글 입니다.")
        );

        return articleOptional.get();
    }

    @Transactional
    public void modifyArticle(ArticleModifyForm articleModifyForm, Long id) {

        Article findArticle = findById(id);

        findArticle.modifyArticle(
                articleModifyForm.getTitle(),
                articleModifyForm.getBody()
        );

    }

    public List<ArticleListDTO> getArticleList() {

        List<Article> articleList = articleRepository.findAll();

        List<ArticleListDTO> articleDTOList = new ArrayList<>();

        for (Article article : articleList){

            ArticleListDTO articleDTO = new ArticleListDTO(article);
            articleDTOList.add(articleDTO);

        }
        return articleDTOList;

    }

    public ArticleDTO getArticle(Long id) {

        Article findArticle = findById(id);

        ArticleDTO articleDTO = new ArticleDTO(findArticle);

        return articleDTO;

    }
}